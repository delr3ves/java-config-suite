package io.delr3ves.config.provider;

import io.delr3ves.config.provider.exception.ConfigNotFoundException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class StreamLoader {

    public InputStream load(String id) throws ConfigNotFoundException {
        try {
            if (isUrl(id)) {
                return openUrl(id);
            }
            if (isResource(id)) {
                return openResource(id);
            }
            return openFile(id);
        } catch (Exception e) {
            throw new ConfigNotFoundException(id);
        }
    }

    public Boolean canLoad(String configId) {
        return isUrl(configId) || isFile(new File(configId)) || isResource(configId);
    }

    private InputStream openUrl(String url) throws IOException {
        return new URL(url).openStream();
    }

    private InputStream openResource(String name) throws IOException {
        return this.getClass().getClassLoader().getResourceAsStream(name);
    }

    private InputStream openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!isFile(file)) {
            throw new FileNotFoundException("File " + path + " not found");
        }
        return new FileInputStream(file);
    }

    private boolean isFile(File file) {
        return file.exists();
    }

    private Boolean isUrl(String path) {
        Boolean isUrl = false;
        try {
            new URL(path);
            isUrl = true;
        } catch (MalformedURLException e) {
        }
        return isUrl;
    }

    private Boolean isResource(String name) {
        Boolean isResource = false;
        try {
            isResource = this.getClass().getClassLoader().getResourceAsStream(name) != null;
        } catch (Exception e) {
        }
        return isResource;
    }
}
