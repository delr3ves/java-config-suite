package com.emaginalabs.config.provider;

import com.emaginalabs.config.provider.exception.InvalidConfigException;
import com.google.inject.Inject;
import com.emaginalabs.config.parser.ConfigParser;
import com.emaginalabs.config.parser.ConfigParserFactory;
import com.emaginalabs.config.provider.exception.ConfigNotFoundException;

import java.io.InputStream;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class ConfigFileProvider implements ConfigProvider {

    private StreamLoader streamLoader;
    private ConfigParser configParser;

    @Inject
    public ConfigFileProvider() {
        this(new StreamLoader(), ConfigParserFactory.getInstance());
    }

    public ConfigFileProvider(StreamLoader streamLoader, ConfigParser configParser) {
        this.streamLoader = streamLoader;
        this.configParser = configParser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T load(String configId, Class<T> configType) throws ConfigNotFoundException, InvalidConfigException {
        InputStream stream = streamLoader.load(configId);
        try {
            return configParser.parse(parseFileExtension(configId), stream, configType);
        } catch (Exception e) {
            throw new InvalidConfigException(e, configType);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean canLoad(String configId) {
        return streamLoader.canLoad(configId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer priority() {
        return 1;
    }

    private String parseFileExtension(String filePath) {
        return filePath.substring(filePath.indexOf(".") + 1);
    }
}
