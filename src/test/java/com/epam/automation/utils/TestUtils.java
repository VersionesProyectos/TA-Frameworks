package com.epam.automation.utils;

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
import java.util.Date;

public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    /**
     * Guarda una captura de pantalla localmente en la carpeta /screenshots
     */
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
            // Esta línea es la que salva el reporte en Java 24:
            Allure.addAttachment("Captura de Pantalla", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            logger.error("Error al adjuntar a Allure: " + e.getMessage());
        }
    }
}