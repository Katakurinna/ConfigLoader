package me.cerratolabs.io.file.configloader.factory;

import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.adapters.properties.PropertiesManager;
import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.YAMLManager;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigComparator;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to register comparators
 * and use them to get ConfigLoaders.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 * @see ConfigLoader
 */
public class ConfigFactory {

    private static List<ConfigComparator> configComparators = new ArrayList<>();

    /**
     * Register a new comparator
     *
     * @param comparator ConfigComparator instance to register.
     * @see ConfigComparator
     * Examples:
     * @see YAMLManager
     * @see PropertiesManager
     */
    public static void registerComparator(ConfigComparator comparator) {
        configComparators.add(comparator);
    }

    /* Register new comparators automatically. */
    static {
        registerComparator(new YAMLManager());
        registerComparator(new PropertiesManager());
    }

    /**
     * This method uses the file extension passed by parameter
     * to check if it allows the indicated file format. If you
     * accept it, it returns a ready-to-use instance of ConfigFile.
     *
     * @param path path with the path of the file you want to use or create.
     * @return Ready-to-use ConfigFile instance.
     * @throws IllegalArgumentException Throw the exception if you don't allow the file format passed by parameter.
     * @throws NullPointerException     If path is null or empty.
     */
    public static ConfigLoader getConfigLoader(String path) throws NullPointerException, IllegalArgumentException {
        return searchConfigLoader(path);
    }

    /**
     * Search if there is a ConfigLoader compatible with the file,
     * based on the criteria of each ConfigComparator.
     *
     * @param path file you want to work with
     * @return Ready-to-use ConfigFile instance.
     * @throws IllegalArgumentException Throw the exception if you don't allow the file format passed by parameter.
     * @throws NullPointerException     If path is null or empty.
     * @see ConfigLoader
     * @see ConfigManager
     */
    public static ConfigLoader searchConfigLoader(String path) throws NullPointerException, IllegalArgumentException {
        if (path == null || path.isEmpty()) throw new NullPointerException("path parameter is null.");

        ConfigManager manager = (ConfigManager) configComparators.stream().filter(comparator -> comparator.matches(path)).findFirst().orElse(null);
        if (manager == null) throw new IllegalArgumentException("ConfigFactory dont have the file extension you want (" + getExtension(path) +").");

        return manager.getConfig(path);
    }

    /**
     * Get file extension.
     * @param path file path
     * @return extension.
     */
    private static String getExtension(String path){
        return path.substring(path.lastIndexOf("."));
    }
}