package io.delr3ves.config.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class ConfigParserFactory {

    private static Injector injector;

    public static ConfigParser getInstance() {
        if (injector == null) {
            injector = Guice.createInjector(new ParserGuiceModule());
        }
        return injector.getInstance(ConfigParser.class);
    }
}
