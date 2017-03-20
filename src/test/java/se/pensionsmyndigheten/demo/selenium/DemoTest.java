package se.pensionsmyndigheten.demo.selenium;


import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;

public class DemoTest {
    private static Logger logger = Logger.getLogger(DemoTest.class.getName());

    private static String baseUrl;
    private static String profile;
    private static String homePage;


    @BeforeClass
    public static void getHomePage() {
        baseUrl = System.getProperty("webdriver.base.url");
        profile = System.getProperty("spring.profiles.active");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new URL(baseUrl)
                                    .openConnection()
                                    .getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            homePage = sb.toString();
        } catch (IOException ioe) {

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ignore) {}
        }

    }

    @Test
    public void checkPageTitleIsCodeNite_PROFILE() throws IOException {
        //<html><head><title>Code Nite SYSTEMTEST</title></head><html><body><font color='blue'><h1 id='greeting'>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected title: 'Code Nite <PROFILE>'
        String expectedTitle = String.format("Code Nite %s", profile.toUpperCase());
        Pattern p = Pattern.compile(".*" + expectedTitle + ".*");
        Matcher m = p.matcher(homePage);
        assertTrue("Home page contains title '" + expectedTitle + "'", m.find());
        logger.info("Home page contains title: '" + expectedTitle + "'");

    }

    @Test
    public void checkHelloMessageIs_HelloFrom_PROFILE() throws IOException {
        //<html><head><title>Code Nite SYSTEMTEST</title></head><html><body><font color='blue'><h1 id='greeting'>Hello from SYSTEMTEST!!!</h1></font></body></html>
        // Expected helloMessage: 'Hello from <PROFILE>!!!'
        String expectedHelloMessage = String.format("Hello from %s!!!", profile.toUpperCase());
        Pattern p = Pattern.compile(".*" + expectedHelloMessage + ".*");
        Matcher m = p.matcher(homePage);
        assertTrue("Home contains hello message '" + expectedHelloMessage + "'", m.find());
        logger.info("Home page contains hello message: '" + expectedHelloMessage + "'");

    }

}