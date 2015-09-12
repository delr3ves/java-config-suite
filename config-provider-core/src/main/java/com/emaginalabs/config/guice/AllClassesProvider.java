package com.emaginalabs.config.guice;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.emaginalabs.config.loader.AllClassesLoader;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class AllClassesProvider<T> implements Provider<List<T>> {

    private final Class<T> type;

    @Inject
    private AllClassesLoader loader;

    @Inject
    private Injector injector;

    public AllClassesProvider(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> get() {
        List<Class<T>> classes = loader.findClassesOf(type);
        List<T> services = new ArrayList<T>();
        for (Class<T> clazz : classes) {
            services.add(injector.getInstance(clazz));
        }
        return services;
    }
}
