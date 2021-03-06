package me.cerratolabs.configloader.configuration.adapters.yaml;

import lombok.Getter;
import me.cerratolabs.configloader.ConfigLoader;
import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigManager;

/**
 * {@code YAMLManager} takes care of checking if
 * any extension in the ConfigFactory list belongs
 * to it, and of returning a new instance.
 * This class handles files of type YAML.
 * To recognize that it is a yaml file, it must end in {@code .yaml} or {@code .yml}.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public class YAMLManager implements ConfigManager {

    @Getter private String name = "YAML";

    /**
     * If path match with yaml extension,
     * return true, else, false.
     * Ignore if it is uppercase / lowercase.
     *
     * @param path file path.
     * @return if is yaml extension or not (end with .yaml or .yml)
     */
    @Override
    public boolean matches(String path) {
        return path.toLowerCase().endsWith(".yaml") || path.toLowerCase().endsWith(".yml");
    }

    /**
     * Return new instance of ConfigLoader.
     *
     * @param path file path.
     * @return ConfigLoader instance.
     */
    @Override
    public ConfigLoader getConfig(String path) {
        YAMLNode node = new YAMLNode();
        return new ConfigLoader(new YAMLFileLoader(path, node), node);
    }
}