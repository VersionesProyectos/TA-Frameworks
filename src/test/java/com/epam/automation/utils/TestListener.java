package com.epam.automation.utils;
import com.epam.automation.base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        // Obtenemos el driver de la instancia del test que falló
        Object testClass = result.getInstance();
        var driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            TestUtils.saveScreenshotToReportPortal(driver);
            TestUtils.saveScreenshotToAllure(driver);
        }
    }
}
