package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
import com.epam.automation.model.PageState;
import com.epam.automation.pages.SwitchWindowPage;
import com.epam.automation.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwitchWindowTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SwitchWindowTest.class);
    private SwitchWindowPage switchWindowPage;

    @BeforeMethod
    public void setUpPage() {
        switchWindowPage = new SwitchWindowPage(driver);
    }

    @Test(groups = {"regression", "navigation"},
            description = "Scenario 2: Window Navigation and Handling")

    public void navigationAndWindowHandlingTest() {
        logger.info("Iniciando Escenario 2: Manejo de Ventanas y Alertas");

        PageState state = new PageState(Constants.MAIN_TITLE_TEXT);

        try {
            switchWindowPage.goToSwitchWindowSection();

            switchWindowPage.openNewTabAndSwitch();
            switchWindowPage.closeAndReturnToMain();
            switchWindowPage.handleAlert();
            switchWindowPage.clickLogoFormy();

            String actualTitle = switchWindowPage.getPageTitle();
            logger.info("Validando título final. Esperado: " + state.getExpectedTitle());

            Assert.assertEquals(actualTitle, state.getExpectedTitle(), "El título de la página principal es incorrecto.");

        } catch (Exception e) {
            logger.error("Fallo en el flujo de Switch Window: " + e.getMessage());
            throw e;
        }
    }
}
