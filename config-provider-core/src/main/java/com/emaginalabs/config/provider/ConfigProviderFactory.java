package com.emaginalabs.config.provider;

import com.emaginalabs.config.exception.NoConfigProviderFoundException;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Defines the interface for the dynamic factory in order to be able to select propper objects.
 */
public interface ConfigProviderFactory {
    ConfigProvider getInstance(String configId) throws NoConfigProviderFoundException;
}
