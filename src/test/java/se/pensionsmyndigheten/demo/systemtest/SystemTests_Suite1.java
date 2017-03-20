package se.pensionsmyndigheten.demo.systemtest;

import org.junit.BeforeClass;
import org.junit.Test;
import se.pensionsmyndigheten.demo.WebAppTest;

import java.io.IOException;

public class SystemTests_Suite1 extends WebAppTest {




    @BeforeClass
    public static void init() throws Exception {
        WebAppTest.init();
    }

    @Test
    public void checkApplicationVersionIsCorrect() throws IOException {
        super.checkApplicationVersionIsCorrect();
    }

    @Test
    public void checkPageTitleIsCodeNite_PROFILE() throws IOException {
        super.checkPageTitleIsCodeNite_PROFILE();
    }

    @Test
    public void checkHelloMessageIs_HelloFrom_PROFILE() throws IOException {
        super.checkHelloMessageIs_HelloFrom_PROFILE();
    }
}