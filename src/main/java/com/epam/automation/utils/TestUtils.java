package com.epam.automation.utils;

import com.epam.reportportal.service.ReportPortal;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String safeTestName = testName.replaceAll("[^a-zA-Z0-9]", "_");
        String fileName = safeTestName + "_" + timestamp + ".png";
        String filePath = "screenshots/" + fileName;

        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists() && screenshotDir.mkdirs()) {
                logger.debug("Directorio de screenshots creado.");
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filePath));

            logger.info("Evidencia local guardada: " + fileName);
            return filePath;

        } catch (IOException e) {
            logger.error("Error al guardar captura local: " + e.getMessage());
            return null;
        }
    }

    public static void saveScreenshotToAllure(WebDriver driver) {
        try {

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Captura de Pantalla", new ByteArrayInputStream(screenshot));

        } catch (Exception e) {
            logger.error("Error al adjuntar a Allure: " + e.getMessage());
        }
    }

    //    public static void saveScreenshotToReportPortal(WebDriver driver) {
//        try {
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // ReportPortal necesita saber que el archivo es una imagen
//            // Usamos el método emitLog asegurando que pasamos el objeto File
//            ReportPortal.emitLog(
//                    "Evidencia de la falla en UI",
//                    "ERROR",
//                    new Date(),
//                    screenshot
//            );
//
//            logger.info("Comando emitLog enviado a Report Portal para el archivo: " + screenshot.getName());
//
//        } catch (Exception e) {
//            logger.error("Error en TestUtils.saveScreenshotToReportPortal: " + e.getMessage());
//        }
//    }


    // Cambiamos el nombre para que coincida con lo que busca tu TestListener
    public static void saveScreenshotToReportPortal(WebDriver driver) {
        if (driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Uso del método estático de Report Portal
                ReportPortal.emitLog(
                        "Falla detectada: Captura de pantalla adjunta",
                        "ERROR",
                        Calendar.getInstance().getTime(),
                        screenshot
                );
            } catch (Exception e) {
                System.err.println("Error al enviar captura: " + e.getMessage());
            }
        }
    }


    public static void reportScreenshot(WebDriver driver, String message) {
        if (driver != null) {
            try {
                // 1. Capturamos la pantalla como un archivo temporal
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // 2. Logging Explícito: Enviamos el archivo directamente al agente
                // Usamos "ERROR" para que resalte y Calendar para la estampa de tiempo
                ReportPortal.emitLog(
                        message,
                        "ERROR",
                        Calendar.getInstance().getTime(),
                        screenshot
                );

            } catch (Exception e) {
                System.err.println("Error al enviar captura a ReportPortal: " + e.getMessage());
            }
        }
    }

}