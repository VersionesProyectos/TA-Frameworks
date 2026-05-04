package com.epam.automation.utils;

import com.epam.automation.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("TEST FALLIDO: " + result.getName());
        capturarEvidencia(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("✅ TEST EXITOSO: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("⚠️ TEST OMITIDO: " + result.getName());
    }

    private void capturarEvidencia(ITestResult result) {
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) return;

        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) {
            LOGGER.warn("Driver null — no se puede capturar evidencia para: " + result.getName());
            return;
        }

        String mensaje = "Screenshot | " + result.getName();

        TestUtils.reportScreenshotToRP(driver, mensaje);

        TestUtils.saveScreenshotToAllure(driver);
    }
}