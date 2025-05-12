package com.dziem.weatherappchmura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;


@SpringBootApplication
public class WeatherAppChmuraApplication {

    private static final Logger logger = LoggerFactory.getLogger(WeatherAppChmuraApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WeatherAppChmuraApplication.class, args);
        logger.info("Czas startu: " + LocalDateTime.now());
        logger.info("Jakub Dziem");
        Environment env = context.getEnvironment();
        String port = env.getProperty("local.server.port");
        logger.info("Application is running on port: {}", port);
    }
}
