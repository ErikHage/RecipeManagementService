package com.tfr.rms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RecipeManagementServiceApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(RecipeManagementServiceApplication.class);

	public static void main(String[] args) {
        logger.debug("Starting application");

        try {
            SpringApplication.run(RecipeManagementServiceApplication.class, args);
        } catch(Exception ex) {
            logger.error("Error encountered while running application", ex);
            throw new RuntimeException(ex);
        }

	}


}
