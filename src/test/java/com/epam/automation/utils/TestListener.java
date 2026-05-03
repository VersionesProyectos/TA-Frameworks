package com.epam.automation.utils;
import com.epam.automation.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.sql.DriverManager;

public class TestListener extends TestListenerAdapter {

    // ... dentro de tu clase Listener
    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        // 1. Obtienes la instancia del test actual (que extiende de BaseTest)
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            // 2. Ahora sí, envías a ReportPortal y Allure
            TestUtils.saveScreenshotToAllure(driver);
            TestUtils.reportScreenshot(driver, "Falla en: " + result.getName());
        }
    }
}