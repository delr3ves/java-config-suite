package io.delr3ves.config.provider;

import io.delr3ves.config.provider.exception.ConfigNotFoundException;
import io.delr3ves.config.provider.exception.InvalidConfigException;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public interface ConfigProvider {

    /**
     * Load the config from a datasource and map it into the given object type.
     * @param configId the config identifier (could be a filename, namestape in
     * @param configType
     * @param <T>
     * @return
     * @throws ConfigNotFoundException
     * @throws io.delr3ves.config.provider.exception.InvalidConfigException
     */
    <T> T load(String configId, Class<T> configType) throws ConfigNotFoundException, InvalidConfigException;

    /**
     * Decides whether the config provider is able to load the given configuration.
     * @param configId
     * @return
     */
    Boolean canLoad(String configId);

    /**
     * Decides the priority of the loader in cases two loaders are able to process the request.
     *
     * @return the priority of the given loader.
     */
    Integer priority();
}
