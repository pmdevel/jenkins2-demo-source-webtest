package se.pensionsmyndigheten.demo.selenium;


import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.junit.*;

public class WebAppTest {
    private static Logger logger = Logger.getLogger(WebAppTest.class.getName());

    private static String sutUrl;
    private static String profile;
    private static String homePage;


    @BeforeClass
    public static void getHomePage() throws Exception {
        sutUrl = System.getProperty("sut.url");
        profile = System.getProperty("spring.profiles.active");
        assertTrue("Subject under test should be passed in via -Dsut.url=...", sutUrl!=null);
        assertTrue("System profile should be passed in via -Dspring.profiles.active=...", profile!=null);
        URL app = new URL(sutUrl);
        homePage = IOUtils.toString(app.openStream(), Charset.forName("UTF-8"));

    }

    @Test
    public void checkPageTitleIsCodeNite_PROFILE() throws IOException {
        // HTML in homepage: <html><head><title>Code Nite SYSTEMTEST</title></head><html><body><font color='blue'><h1>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected title: 'Code Nite <PROFILE>'
        String expectedTitle = String.format("Code Nite %s", profile.toUpperCase());
        assertTrue("Home page does NOT contain title '" + expectedTitle + "'", homePage.contains(expectedTitle));
        logger.info("Home page contains title: '" + expectedTitle + "'");

    }

    @Test
    public void checkHelloMessageIs_HelloFrom_PROFILE() throws IOException {
        // HTML in homepage: <html><head><title>Code Nite SYSTEMTEST</title></head><html><body><font color='blue'><h1>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected helloMessage: 'Hello from <PROFILE>!!!'
        String expectedHelloMessage = String.format("Hello from %s!!!", profile.toUpperCase());
        assertTrue("Home page does NOT contain hello message '" + expectedHelloMessage + "'", homePage.contains(expectedHelloMessage));
        logger.info("Home page contains hello message: '" + expectedHelloMessage + "'");

    }

}