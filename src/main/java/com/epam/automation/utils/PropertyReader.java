package com.epam.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(PropertyReader.class);

    public static void loadProperties(String env) {

        properties = new Properties();
        String fileName = env.toLowerCase() + ".properties";

        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.error("ERROR: No se encontró el archivo de configuración en resources: " + fileName);
                throw new RuntimeException("Archivo de propiedades no encontrado: " + fileName);
            }
            properties.load(input);
            logger.info("Configuración de ambiente '" + env + "' cargada exitosamente.");
        } catch (IOException ex) {
            logger.error("Error crítico al leer el archivo de propiedades: " + fileName, ex);
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            logger.warn("Se intentó leer la propiedad '" + key + "' antes de inicializar PropertyReader. Cargando 'qa' por defecto.");
            loadProperties("qa");
        }

        String value = properties.getProperty(key);

        if (value == null) {
            logger.error("La propiedad '" + key + "' no existe en el archivo de configuración actual.");
        }

        return value;
    }
}