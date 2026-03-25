package com.epam.automation.base;

import com.epam.automation.utils.Constants;
import com.epam.automation.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver must not be null");
        }
        this.driver = driver;

        int timeout = Integer.parseInt(PropertyReader.getProperty("timeout"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        PageFactory.initElements(driver, this);
    }

    protected void highlightElement(WebElement element) {
        try {
            String originalStyle = element.getAttribute("style");
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

            Thread.sleep(200);

            js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
        } catch (Exception e) {
            logger.warn("No se pudo resaltar el elemento: " + e.getMessage());
        }
    }

    protected void click(WebElement element) {
        logger.debug("Esperando que el elemento sea clickable para hacer click.");
        wait.until(ExpectedConditions.elementToBeClickable(element));

        highlightElement(element);
        element.click();
    }

    protected void sendText(WebElement element, String text) {
        logger.debug("Esperando visibilidad del elemento para enviar texto: " + text);
        waitForElementToBeVisible(element);

        highlightElement(element);

        element.clear();
        element.sendKeys(text);
    }

    protected void scrollToElement(WebElement element) {
        logger.debug("Haciendo scroll hasta el elemento.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        highlightElement(element);
    }

    protected void refreshPageElements() {
        logger.debug("Refrescando referencias de PageFactory para " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}