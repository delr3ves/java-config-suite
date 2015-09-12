package com.emaginalabs.config.guice;

import com.emaginalabs.config.provider.ConfigProviderFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.emaginalabs.config.provider.ConfigProviderComparator;
import com.emaginalabs.config.provider.DynamicConfigProviderFactory;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Sergio Arroyo - @delr3ves
 */
@RunWith(Parameterized.class)
public class DynamicFactoryGuiceModuleIntegrationTest {

    private Injector injector;
    private Key key;
    private Class expectedClass;

    public DynamicFactoryGuiceModuleIntegrationTest(Key key, Class expectedClass) {
        this.key = key;
        this.expectedClass = expectedClass;
    }

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new DynamicFactoryGuiceModule());
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Key.get(Reflections.class), Reflections.class},
                {Key.get(ConfigProviderFactory.class), DynamicConfigProviderFactory.class},
                {Key.get(Comparator.class, Names.named(ConfigProviderComparator.CONFIG_PROVIDER_COMPARATOR)),
                        ConfigProviderComparator.class},
        });
    }

    @Test
    public void testGetInstance() {
        MatcherAssert.assertThat(injector.getInstance(key).getClass(), CoreMatchers.equalTo(expectedClass));
    }
}
