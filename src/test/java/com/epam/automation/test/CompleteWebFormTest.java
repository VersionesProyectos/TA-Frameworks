package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
import com.epam.automation.model.User;
import com.epam.automation.pages.CompleteWebFormPage;
import com.epam.automation.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompleteWebFormTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CompleteWebFormTest.class);
    private CompleteWebFormPage completeWebFormPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
        logger.debug("Inicializando Page Objects para CompleteWebFormTest");
        completeWebFormPage = new CompleteWebFormPage(driver);
    }

    @Test(groups = {"smoke", "regression"},
            description = "Scenario 1: Complete the Formy registration form")

    public void testCompleteWebForm() {
        logger.info("Ejecutando Test de Registro Completo");

        User testUser = new User(
                "Gerardo",
                "QA",
                "Automation Engineer",
                "03/16/2026"
        );

        completeWebFormPage.clickLinkForm();
        completeWebFormPage.fillForm(testUser);
        completeWebFormPage.clickSubmit();

        Assert.assertEquals(completeWebFormPage.getAlertText(), Constants.SUCCESS_MESSAGE);
        logger.info("Test de Registro finalizado con éxito.");
    }

}


