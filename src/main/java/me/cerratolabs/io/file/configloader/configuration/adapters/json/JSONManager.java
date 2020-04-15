package me.cerratolabs.io.file.configloader.configuration.adapters.json;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

/**
 * {@code JSONManager} takes care of checking if
 * any extension in the ConfigFactory list belongs
 * to it, and of returning a new instance.
 * This class handles files of type JSON.
 * To recognize that it is a JSON file, it must end in {@code .json}.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
public class JSONManager implements ConfigManager {

    @Getter private String name = "JSON";

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
        return path.toLowerCase().endsWith(".json");
    }

    /**
     * Return new instance of ConfigLoader.
     *
     * @param path file path.
     * @return ConfigLoader instance.
     */
    @Override
    public ConfigLoader getConfig(String path) {
        JSONNode node = new JSONNode();
        return new ConfigLoader(new JSONFileLoader(path, node), node);
    }

}