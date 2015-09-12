package com.emaginalabs.config.provider;

import java.util.Comparator;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Compare loaders bassed on its priorities.
 */
public class ConfigProviderComparator implements Comparator<ConfigProvider> {

    public static final String CONFIG_PROVIDER_COMPARATOR = "configProviderComparator";

    @Override
    public int compare(ConfigProvider o1, ConfigProvider o2) {
        return o2.priority().compareTo(o1.priority());
    }

}
