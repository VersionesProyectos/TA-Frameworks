package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import com.epam.automation.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SwitchWindowPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SwitchWindowPage.class);
    private String mainWindowHandle;

    @FindBy(id = "logo") private WebElement logoHome;
    @FindBy(css = "a.btn-lg[href='/switch-window']") private WebElement switchWindowLink;
    @FindBy(id = "new-tab-button") private WebElement openNewTabButton;
    @FindBy(id = "alert-button") private WebElement openAlertButton;
    @FindBy(css = ".navbar-brand") private WebElement logoFormy;
    @FindBy(css = ".display-3") private WebElement mainTitle;

    public SwitchWindowPage(WebDriver driver) {
        super(driver);
    }

    public void goToSwitchWindowSection() {
        logger.info("Navegando a la sección de Switch Window...");
        driver.get(PropertyReader.getProperty("url"));
        waitForElementToBeVisible(switchWindowLink);
        scrollToElement(switchWindowLink);
        click(switchWindowLink);
    }

    public void openNewTabAndSwitch() {
        mainWindowHandle = driver.getWindowHandle();
        logger.info("Abriendo nueva pestaña. Ventana original: " + mainWindowHandle);
        click(openNewTabButton);

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                logger.debug("Cambiado a la nueva ventana: " + handle);
            }
        }
    }

    public void closeAndReturnToMain() {
        logger.info("Cerrando pestaña secundaria y regresando a la principal.");
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    public void handleAlert() {
        logger.info("Abriendo y aceptando alerta JS.");
        click(openAlertButton);

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        logger.debug("Texto de la alerta: " + alert.getText());
        alert.accept();
    }

    public String getPageTitle() {
        logger.debug("Obteniendo título principal de la página.");
        return mainTitle.getText();
    }

    public void clickLogoFormy() {
        logger.info("Regresando al Home mediante el logo de Formy.");
        click(logoFormy);
    }
}
