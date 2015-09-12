package com.emaginalabs.config.loader;

import org.reflections.Reflections;

import javax.inject.Inject;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Find classes for which are subtype of the parent class in the classpath.
 */
public class AllClassesLoader<T> {

    private Reflections reflections;

    @Inject
    public AllClassesLoader(Reflections reflections) {
        this.reflections = reflections;
    }

    /**
     * Find classes for the given class.
     * @param clazz the parent class to find elements.
     *
     * @return the list of class which are instantiable.
     */
    public List<Class<? extends T>> findClassesOf(Class<T> clazz) {
        return findClassesOf(clazz, false);
    }

    /**
     * Find classes for the given class.
     * @param clazz the parent class to find elements.
     * @param includeNonInstantiable decides whether to include non-instantiable classes
     *
     * @return the list of class instantiable or not depending on includeNonInstantiable param.
     */
    public List<Class<? extends T>> findClassesOf(Class<T> clazz, Boolean includeNonInstantiable) {
        final Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
        final List<Class<? extends T>> candidates = new ArrayList<Class<? extends T>>();

        for (Class candidate : classes) {
            if (includeNonInstantiable || !(Modifier.isAbstract(candidate.getModifiers()) || Modifier.isInterface(candidate.getModifiers()))) {
                candidates.add(candidate);
            }
        }
        return candidates;
    }
}
