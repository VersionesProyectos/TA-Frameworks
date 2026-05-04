package com.epam.automation.utils;

import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    public static void reportScreenshotToRP(WebDriver driver, String message) {
        if (driver == null) return;
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);


            ReportPortal.emitLog(itemUuid -> {
                SaveLogRQ rq = new SaveLogRQ();
                rq.setMessage(message);
                rq.setLevel("ERROR");
                rq.setLogTime(Calendar.getInstance().getTime());
                rq.setItemUuid(itemUuid);

                SaveLogRQ.File file = new SaveLogRQ.File();
                file.setName("screenshot.png");
                file.setContent(screenshotBytes);
                file.setContentType("image/png");
                rq.setFile(file);

                return rq;
            });

            logger.info("Screenshot enviado a RP: " + message);

        } catch (Exception e) {
            logger.error("Error enviando screenshot a RP: " + e.getMessage());
        }
    }

    public static void saveScreenshotToAllure(WebDriver driver) {
        if (driver == null) return;
        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Captura de Pantalla", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            logger.error("Error adjuntando a Allure: " + e.getMessage());
        }
    }

    private static File crearArchivoTemporal(byte[] data) throws Exception {
        File temp = File.createTempFile("rp_screenshot_", ".png");
        temp.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(temp)) {
            fos.write(data);
        }
        return temp;
    }
}