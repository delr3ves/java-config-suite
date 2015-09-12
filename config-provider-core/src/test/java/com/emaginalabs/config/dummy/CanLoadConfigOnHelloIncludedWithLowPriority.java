package com.emaginalabs.config.dummy;

import com.emaginalabs.config.provider.ConfigProvider;
import com.emaginalabs.config.provider.exception.ConfigNotFoundException;
import com.emaginalabs.config.provider.exception.InvalidConfigException;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class CanLoadConfigOnHelloIncludedWithLowPriority implements ConfigProvider {

    public static final int LOW_PRIORITY = 0;
    public static final String INCLUDED_SEQUENCE = "Hello";

    @Override
    public <T> T load(String configId, Class<T> configType) throws ConfigNotFoundException, InvalidConfigException {
        return null;
    }

    @Override
    public Boolean canLoad(String configId) {
        return configId.contains(INCLUDED_SEQUENCE);
    }

    @Override
    public Integer priority() {
        return LOW_PRIORITY;
    }
}
