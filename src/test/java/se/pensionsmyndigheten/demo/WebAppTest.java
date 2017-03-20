package se.pensionsmyndigheten.demo;


import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.junit.*;

public class WebAppTest {

    private static String sutUrl;
    private static String sutVersion;
    private static String profile;
    private static String homePage;


    public static void init() throws Exception {
        sutUrl = System.getProperty("sut.url");
        sutVersion = System.getProperty("sut.version");
        profile = System.getProperty("spring.profiles.active");

        assertTrue("Subject under test should be passed in via -Dsut.url=...", sutUrl!=null);
        assertTrue("Subject under test version should be passed in via -Dsut.version=...", sutVersion!=null);
        assertTrue("System profile should be passed in via -Dspring.profiles.active=...", profile!=null);
        URL app = new URL(sutUrl);
        homePage = IOUtils.toString(app.openStream(), Charset.forName("UTF-8"));
    }

    public void checkApplicationVersionIsCorrect() throws IOException {
        // HTML in homepage: <html><head><title>Code Nite SYSTEMTEST - V1.2.3</title></head><html><body><font color='blue'><h1>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected title: 'Code Nite <PROFILE>'
        String expectedVersion = String.format("V%s", sutVersion);
        assertTrue("Application version is NOT correct '" + expectedVersion + "'", homePage.contains(expectedVersion));

    }

    public void checkPageTitleIsCodeNite_PROFILE() throws IOException {
        // HTML in homepage: <html><head><title>Code Nite SYSTEMTEST - V1.2.3</title></head><html><body><font color='blue'><h1>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected title: 'Code Nite <PROFILE>'
        String expectedTitle = String.format("Code Nite %s", profile.toUpperCase());
        assertTrue("Home page does NOT contain title '" + expectedTitle + "'", homePage.contains(expectedTitle));

    }

    public void checkHelloMessageIs_HelloFrom_PROFILE() throws IOException {
        // HTML in homepage: <html><head><title>Code Nite SYSTEMTEST - V1.2.3</title></head><html><body><font color='blue'><h1>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected helloMessage: 'Hello from <PROFILE>!!!'
        String expectedHelloMessage = String.format("Hello from %s!!!", profile.toUpperCase());
        assertTrue("Home page does NOT contain hello message '" + expectedHelloMessage + "'", homePage.contains(expectedHelloMessage));

    }

}