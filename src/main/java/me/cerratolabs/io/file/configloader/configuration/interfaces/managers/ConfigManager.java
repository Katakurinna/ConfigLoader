package me.cerratolabs.io.file.configloader.configuration.interfaces.managers;

import me.cerratolabs.io.file.configloader.ConfigLoader;

/**
 * This interface is used to define managers.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public interface ConfigManager extends ConfigComparator {

    /**
     * Return new instance of ConfigLoader.
     *
     * @param path file path.
     * @return ConfigLoader instance.
     */
    ConfigLoader getConfig(String path);
}