package com.emaginalabs.config.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class ParserGuiceModule extends AbstractModule {

    @Override
    public void configure() {
        bind(ConfigParser.class).to(JacksonConfigParser.class);
        bindObjectMappers();
    }

    private void bindObjectMappers() {
        try {
            bind(ObjectMapper.class).annotatedWith(Names.named("json")).toInstance(new ObjectMapper());
            bind(ObjectMapper.class).annotatedWith(Names.named("yaml")).toInstance(new ObjectMapper(new YAMLFactory()));
            bind(ObjectMapper.class).annotatedWith(Names.named("xml")).toInstance(new ObjectMapper(new XmlFactory()));
        } catch (Throwable e) {
            //they're already bound
        }
    }
}
