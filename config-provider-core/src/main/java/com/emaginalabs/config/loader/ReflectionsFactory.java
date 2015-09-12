package com.emaginalabs.config.loader;

import org.reflections.Reflections;

/**
 * @author Sergio Arroyo - @delr3ves
 *
 * Initialize the refelections object to be able to find interfaces. We'll store it as static object because this operation
 * could be costly and we want to avoid doing it much times.
 */
public class ReflectionsFactory {

    public static Reflections getInstance() {
        Reflections reflections = Reflections.collect();
        if (reflections == null) {
            reflections = new Reflections();
        }
        return reflections;
    }

}
