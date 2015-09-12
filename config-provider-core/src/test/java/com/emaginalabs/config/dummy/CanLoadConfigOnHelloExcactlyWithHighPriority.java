package com.emaginalabs.config.dummy;

import com.emaginalabs.config.provider.ConfigProvider;
import com.emaginalabs.config.provider.exception.InvalidConfigException;
import com.emaginalabs.config.provider.exception.ConfigNotFoundException;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class CanLoadConfigOnHelloExcactlyWithHighPriority implements ConfigProvider {

    public static final int HIGH_PRIORITY = 1;

    @Override
    public <T> T load(String configId, Class<T> configType) throws ConfigNotFoundException, InvalidConfigException {
        return null;
    }

    @Override
    public Boolean canLoad(String configId) {
        return configId.equals("Hello");
    }

    @Override
    public Integer priority() {
        return HIGH_PRIORITY;
    }
}
