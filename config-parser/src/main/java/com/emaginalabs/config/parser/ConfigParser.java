package com.emaginalabs.config.parser;

import com.emaginalabs.config.provider.exception.InvalidConfigException;

import java.io.InputStream;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public interface ConfigParser {

    /**
     * Dump the content of an input stream and dumps it into an object with type "objectType"
     * @param format the format of the resource to be parsed
     * @param content
     * @param objectType
     * @param <T>
     * @return
     * @throws com.emaginalabs.config.provider.exception.InvalidConfigException
     */
    <T> T parse(String format, InputStream content, Class<T> objectType) throws InvalidConfigException;
}
