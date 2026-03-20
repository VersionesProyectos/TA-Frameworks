package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
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

    @BeforeMethod
    public void setUpPage() {
        logger.debug("Inicializando Page Objects para CompleteWebFormTest");
        completeWebFormPage = new CompleteWebFormPage(driver);
    }

    @Test(description = "Scenario 1: Complete the Formy registration form")
    public void testCompleteWebForm() {

        // Uso de INFO para marcar el inicio del escenario
        logger.info("Iniciando Escenario 1: Registro en el formulario Formy");

        try {
            logger.info("Navegando al formulario...");
            completeWebFormPage.clickLinkForm();

            logger.info("Ingresando datos personales: " + Constants.FIRST_NAME + " " + Constants.LAST_NAME);
            completeWebFormPage.enterFirstName(Constants.FIRST_NAME);
            completeWebFormPage.enterLastName(Constants.LAST_NAME);

            logger.debug("Configurando cargo: " + Constants.JOB_TITLE);
            completeWebFormPage.enterJobTitle(Constants.JOB_TITLE);

            logger.info("Seleccionando preferencias (Educación, Género y Experiencia)");
            completeWebFormPage.selectEducationLevel();
            completeWebFormPage.selectGender();
            completeWebFormPage.selectExperience();

            logger.info("Ingresando fecha: " + Constants.DATE);
            completeWebFormPage.enterDate(Constants.DATE);

            logger.info("Enviando formulario...");
            completeWebFormPage.clickSubmit();

            // Verificación
            String actualMessage = completeWebFormPage.getAlertText();
            logger.debug("Mensaje recibido del sistema: " + actualMessage);

            Assert.assertEquals(actualMessage, Constants.SUCCESS_MESSAGE);
            logger.info("Test completado exitosamente: Mensaje de éxito validado.");

        } catch (Exception e) {
            // Uso de ERROR si algo falla durante la ejecución de los pasos
            logger.error("Fallo durante la ejecución del test: " + e.getMessage());
            throw e; // Re-lanzamos para que TestNG lo marque como fallido y tome la captura
        }
    }

}


