package io.delr3ves.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import io.delr3ves.config.guice.AllClassesProvider;
import io.delr3ves.config.loader.ReflectionsFactory;
import io.delr3ves.config.provider.ConfigProvider;
import io.delr3ves.config.provider.ConfigProviderComparator;
import io.delr3ves.config.provider.ConfigProviderFactory;
import io.delr3ves.config.provider.DynamicConfigProviderFactory;
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
