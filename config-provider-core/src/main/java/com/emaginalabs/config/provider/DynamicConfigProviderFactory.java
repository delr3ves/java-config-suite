package com.emaginalabs.config.provider;

import com.emaginalabs.config.exception.NoConfigProviderFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Default implementation for dinamic factory.
 */
public class DynamicConfigProviderFactory implements ConfigProviderFactory {

    private List<ConfigProvider> providers;
    private Comparator comparator;

    @Inject
    public DynamicConfigProviderFactory(List<ConfigProvider> providers, @Named(ConfigProviderComparator.CONFIG_PROVIDER_COMPARATOR) Comparator comparator) {
        this.providers = providers;
        this.comparator = comparator;
    }

    /**
     * Find, in all candidates, for the one with highest priority which is able to process the given entity.
     *
     * @param configId the config to be loaded
     * @return the highest priority candidate.
     * @throws com.emaginalabs.config.exception.NoConfigProviderFoundException if there is no candidates to load the config
     */
    public ConfigProvider getInstance(String configId) throws NoConfigProviderFoundException {
        SortedSet<ConfigProvider> candidates = new TreeSet<ConfigProvider>(comparator);
        for (ConfigProvider candidate : providers) {
            if (candidate.canLoad(configId)) {
                candidates.add(candidate);
            }
        }
        if (candidates.isEmpty()) {
            throw new NoConfigProviderFoundException(configId);
        }
        return candidates.first();
    }
}
