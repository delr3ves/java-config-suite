package com.emaginalabs.config.parser;

import com.emaginalabs.config.provider.exception.InvalidConfigException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class JacksonConfigParser implements ConfigParser {

    public static final String DEFAULT_TYPE = "json";
    public static final String JSON_EXTENSION = "json";
    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";
    public static final String XML_EXTENSION = "xml";

    private Map<String, ObjectMapper> mappersByType;

    @Inject
    public JacksonConfigParser(@Named(JSON_EXTENSION) ObjectMapper jsonMapper,
                               @Named(YAML_EXTENSION) ObjectMapper yamlMapper,
                               @Named(XML_EXTENSION) ObjectMapper xmlMapper) {
        mappersByType = new HashMap<String, ObjectMapper>();
        mappersByType.put(JSON_EXTENSION, jsonMapper);
        mappersByType.put(YAML_EXTENSION, yamlMapper);
        mappersByType.put(YML_EXTENSION, yamlMapper);
        mappersByType.put(XML_EXTENSION, xmlMapper);
    }

    @Override
    public <T> T parse(String format, InputStream content, Class<T> objectType) throws InvalidConfigException {
        ObjectMapper mapper = mappersByType.get(format);
        if (mapper == null) {
            mapper = mappersByType.get(DEFAULT_TYPE);
        }
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(content, objectType);
        } catch (Exception e) {
            throw new InvalidConfigException(objectType);
        }
    }
}
