package com.mortgage.tool.config;

import com.google.inject.Singleton;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

@Singleton
public class ConfigResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigResolver.class);
    private static final String CONFIG_PATH = "application.properties";

    public Config resolve() {
        try {
            final var configurations = new Configurations();
            LOGGER.info("Reading in {} properties file", CONFIG_PATH);
            return toConfig(configurations.properties(CONFIG_PATH));
        } catch (ConfigurationException e) {
            LOGGER.error("Error reading {} configuration file", CONFIG_PATH);
            throw new RuntimeException("Failed to resolve configuration", e);
        } catch (NoSuchElementException e) {
            LOGGER.error("Failed to resolve a specific property", e);
            throw new RuntimeException("Failed to resolve configuration section", e);
        }
    }

    private Config toConfig(PropertiesConfiguration properties) {
        final var port = properties.getInt("port");
        LOGGER.info("Resolved port {} from configuration file {}", port, CONFIG_PATH);
        // TODO: Other properties below
        return new Config(port);
    }
}
