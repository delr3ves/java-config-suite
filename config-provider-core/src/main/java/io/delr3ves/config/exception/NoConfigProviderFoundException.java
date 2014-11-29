package io.delr3ves.config.exception;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Exception thrown when there is no config provider for the given id.
 */
public class NoConfigProviderFoundException extends Exception {

    private String configId;

    public NoConfigProviderFoundException(String configId) {
        this.configId = configId;
    }
}
