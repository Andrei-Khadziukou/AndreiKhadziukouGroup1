package com.company.loader;

import com.company.api.Command;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

/**
 * Created by sanko on 10/4/14.
 */
public class CommandsLoader {

    private static Logger LOGGER = Logger.getLogger(CommandsLoader.class);
    private static String CLASS_SUFFIX = ".class";
    private List<Command> commandList = new ArrayList<>();
    private int count;

    public CommandsLoader() {
        String destPath = System.getProperty("destPath", "commands");
        File directory = new File(destPath);
        destPath = getNormalizedPath(destPath);
        if (directory.exists() && directory.isDirectory()) {
            Collection<File> files = FileUtils.listFiles(directory, new FileFilter(), TrueFileFilter.INSTANCE);
            Map<String, File> fileMap = new HashMap<>();
            for (File file : files) {
                StringBuilder builder = new StringBuilder(file.getPath());
                builder.delete(builder.lastIndexOf(CLASS_SUFFIX), builder.length());
                builder.delete(0, destPath.length());
                String className = (builder.toString()).replaceAll("[\\\\|/]+", ".");
                fileMap.put(className, file);
            }
            initCommands(new LocalClassLoader(destPath, fileMap), fileMap.keySet());
        } else {
            LOGGER.warn(String.format("Can't find commands in [%s]", destPath));
        }
    }

    private void initCommands(ClassLoader loader, Set<String> classSet) {
        for (String className: classSet) {
            try {
                Class<?> clazz = loader.loadClass(className);
                Command command = (Command) clazz.newInstance();
                commandList.add(command);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private String getNormalizedPath(String destPath) {
        destPath = destPath.replaceAll("\\\\", "/");
        return (destPath.endsWith("/")) ? destPath : destPath + '/';
    }

    public Command getCommand(int command) {
        return commandList.get(command);
    }

    public List<Command> getCommands() {
        return commandList;
    }

    public static class FileFilter implements IOFileFilter {

        @Override
        public boolean accept(File file) {
            return file.getPath().endsWith(CLASS_SUFFIX);
        }

        @Override
        public boolean accept(File dir, String name) {
            return dir.isDirectory() && name.endsWith(CLASS_SUFFIX);
        }
    }

}
