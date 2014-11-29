package io.delr3ves.config.provider.exception;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class InvalidConfigException extends Exception {
    Class objectType;

    public InvalidConfigException(Throwable e, Class objectType) {
        super(e);
        this.objectType = objectType;
    }

    public InvalidConfigException(Class objectType) {
        this.objectType = objectType;
    }
}
