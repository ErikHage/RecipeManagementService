package com.tfr.rms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Erik on 9/1/2016.
 */
public class ServiceProperties {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProperties.class);
    private static final String FILENAME = "service.properties";

    private static Properties props;

    public String getProperty(String key) {
        if(props == null) {
            loadProperties();
        }
        String value = props.getProperty(key);
        logger.debug("Loading property: [key:" + key + ", value:" + value + "]");
        return value;
    }

    private void loadProperties() {

        logger.debug("Attempting to load properties file: ");

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(FILENAME))
        {
            props = new Properties();
            props.load(in);
            logger.debug("Properties file loaded successfully");
        } catch(IOException ex) {
            logger.error("Error loading Properties file", ex);
            throw new RuntimeException("Error reading properties file", ex);
        }
    }

}
