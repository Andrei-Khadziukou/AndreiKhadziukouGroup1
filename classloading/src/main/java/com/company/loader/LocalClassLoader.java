package com.company.loader;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sanko on 10/4/14.
 */
public class LocalClassLoader extends ClassLoader {

    private static Logger LOGGER = Logger.getLogger(LocalClassLoader.class);

    private Map<String, Class<?>> loaderCache = new HashMap<>();
    private Map<String, File> fileMap;
    private String destinationPath;

    public LocalClassLoader(String destinationPath, Map<String, File> fileMap) {
        this.destinationPath = destinationPath;
        this.fileMap = fileMap;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = loaderCache.get(name);
        if (clazz != null) {
            return clazz;
        }
        try {
            clazz = getParent().loadClass(name);
            return clazz;
        } catch (ClassNotFoundException e) {
            LOGGER.debug(String.format("Parent classloader can't load [%s]", name));
        }
        File file = fileMap.get(name);
        if (file != null) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                new ClassNotFoundException(name);
            }
        }
        throw new ClassNotFoundException(name);
    }
}
