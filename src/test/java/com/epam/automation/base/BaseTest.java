package com.epam.automation.base;

import com.beust.jcommander.Parameter;
import com.epam.automation.utils.Constants;
import com.epam.automation.utils.PropertyReader;
import com.epam.automation.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    @Parameters({"env"})
    public void beforeSuite(@Optional("qa") String env) {
        PropertyReader.loadProperties(env);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        String url = PropertyReader.getProperty("url");
        logger.info("Iniciando setup del test en: " + browser);


        driver = initializeDriver(browser);
        driver.manage().window().maximize();
        driver.get(url);
    }

    private WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                return new ChromeDriver(options);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            logger.error("Test FALLIDO: " + result.getName());

            TestUtils.saveScreenshotToAllure(driver);
            TestUtils.takeScreenshot(driver, result.getName());
        } else {
            logger.info("Test EXITOSO: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
            logger.info("Navegador cerrado.");
        }
    }
}