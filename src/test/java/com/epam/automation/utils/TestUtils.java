package com.epam.automation.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    public static String takeScreenshot(WebDriver driver, String testName) {

        logger.debug("Preparando captura de pantalla para el test: " + testName);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String safeTestName = testName.replaceAll("[^a-zA-Z0-9]", "_");
        String fileName = safeTestName + "_" + timestamp + ".png";
        String filePath = "screenshots/" + fileName;

        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                if (screenshotDir.mkdirs()) {
                    logger.debug("Directorio de screenshots creado correctamente.");
                }
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            FileUtils.copyFile(srcFile, destFile);

            logger.info("Evidencia guardada exitosamente. Archivo: " + fileName);

            return filePath;
        } catch (IOException e) {
            logger.error("No se pudo guardar la captura de pantalla para " + testName + ". Causa: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.fatal("Error inesperado en el sistema de capturas: " + e.getMessage());
            return null;
        }
    }
}