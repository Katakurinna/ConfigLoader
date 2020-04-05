package me.cerratolabs.io.file.configloader.factory;

import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.adapters.properties.PropertiesManager;
import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.managers.YamlManager;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigComparator;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigFactory {

    private static List<ConfigComparator> configComparators = new ArrayList<>();

    public static void registerComparator(ConfigComparator comparator) {
        configComparators.add(comparator);
    }

    static {
        registerComparator(new YamlManager());
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
     * @throws NullPointerException     d
     */
    public static ConfigLoader getConfigLoader(String path) throws NullPointerException, IllegalArgumentException, IOException {
        ConfigLoader cfg = createConfigLoader(path);
        cfg.load();
        return cfg;
    }

    public static ConfigLoader createConfigLoader(String path) throws NullPointerException, IllegalArgumentException {
        if (path == null || path.isEmpty()) throw new NullPointerException("path parameter is null.");

        ConfigManager manager = (ConfigManager) configComparators.stream().filter(comparator -> comparator.matches(path)).findFirst().orElse(null);
        if (manager == null) throw new IllegalArgumentException("ConfigFactory dont have the file extension you want");

        return manager.getConfig(path);
    }

}