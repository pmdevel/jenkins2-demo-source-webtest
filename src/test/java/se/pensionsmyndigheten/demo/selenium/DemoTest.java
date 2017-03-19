package se.pensionsmyndigheten.demo.selenium;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoTest {
    private static Logger logger = Logger.getLogger(DemoTest.class.getName());

    private static String baseUrl;
    private static String profile;
    private static WebDriver driver;


    static {

    }

    @BeforeClass
    public static void openBrowser() {
        String osName = System.getProperty("os.name");
        if ( osName.matches(".*Linux.*")) {
            logger.info("Running Linux...");
            System.setProperty("webdriver.chrome.driver", "driver/linux64/chromedriver");

        } else if ( osName.matches(".*Windows.*")) {
            logger.info("Running Window...");
            System.setProperty("webdriver.chrome.driver", "driver/win32/chromedriver");

        } else if ( osName.matches(".*Mac.*")) {
            logger.info("Running Linux...");
            System.setProperty("webdriver.chrome.driver", "driver/mac64/chromedriver");
        }

        baseUrl = System.getProperty("webdriver.base.url");
        profile = System.getProperty("spring.profiles.active");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        //screenshotHelper = new ScreenshotHelper();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public void checkPageTitleIsCodeNite_PROFILE() throws IOException {
        // Expected title: 'Code Nite <PROFILE>'
        String expectedTitle = String.format("Code Nite %s", profile.toUpperCase());
        assertEquals("The page title should equal 'Code Nite <profile>'", expectedTitle, driver.getTitle());
        logger.info("Title is: " + driver.getTitle() + " (OK)");

    }

    @Test
    public void checkHelloMessageIs_HelloFrom_PROFILE() throws IOException {
        // Expected helloMessage: 'Hello from <PROFILE>!!!'
        String expectedHelloMessage = String.format("Hello from %s!!!", profile.toUpperCase());
        WebElement searchField = driver.findElement(By.id("greeting"));
        String helloMessage = searchField.getText();
        assertEquals("The hello message should be 'Hello from <profile>!!!'", expectedHelloMessage, helloMessage);
        logger.info("Field: " + searchField.getText() + "(OK)");
    }

}