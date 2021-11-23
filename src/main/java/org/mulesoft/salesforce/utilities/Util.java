package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Util {

    private static String PROPERTIES_FILE = "configuration.properties";
    public static Properties properties = new Properties();

    public static Properties loadProperties() {
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Unable to read from PROPERTIES file: ", e);
        }
        return properties;
    }
}
