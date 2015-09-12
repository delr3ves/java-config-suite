package com.emaginalabs.config.provider.exception;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class ConfigNotFoundException extends Exception {

    String configId;

    public ConfigNotFoundException(String configId) {
        this.configId = configId;
    }
}
