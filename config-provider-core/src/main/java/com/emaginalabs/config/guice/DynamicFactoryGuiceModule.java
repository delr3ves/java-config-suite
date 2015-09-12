package com.emaginalabs.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.emaginalabs.config.loader.ReflectionsFactory;
import com.emaginalabs.config.provider.ConfigProvider;
import com.emaginalabs.config.provider.ConfigProviderComparator;
import com.emaginalabs.config.provider.ConfigProviderFactory;
import com.emaginalabs.config.provider.DynamicConfigProviderFactory;
import org.reflections.Reflections;

import java.util.Comparator;
import java.util.List;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class DynamicFactoryGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<List<ConfigProvider>>() {
        }).toProvider(new AllClassesProvider(ConfigProvider.class));
        try {
            bind(Reflections.class).toInstance(ReflectionsFactory.getInstance());
        } catch (Throwable e) {
        }

        bind(ConfigProviderFactory.class).to(DynamicConfigProviderFactory.class);
        bind(Comparator.class).annotatedWith(Names.named(ConfigProviderComparator.CONFIG_PROVIDER_COMPARATOR)).to(ConfigProviderComparator.class);
    }

}
