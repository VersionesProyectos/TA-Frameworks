package com.epam.automation.base;


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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        String url = System.getProperty("url");
        String browser = System.getProperty("browser", "chrome");
        String envName = System.getProperty("environment");
        int timeout = Integer.parseInt(System.getProperty("timeout", "10"));
        logger.info("Iniciando setup del test en: " + browser);

        driver = initializeDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
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
            // Llamamos a nuestra nueva utilidad de logging explícito
            TestUtils.reportScreenshot(driver, "Falla detectada en el test: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}