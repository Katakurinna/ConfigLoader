package me.cerratolabs.io.file.configloader.configuration.adapters.properties;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

/**
 * Example class for another ConfigManager
 */
public class PropertiesManager implements ConfigManager {

    @Getter private String name = "Properties";

    @Override
    public boolean matches(String path) {
        return path.endsWith(".props") || path.endsWith(".config");
    }

    @Override
    public ConfigLoader getConfig(String path) {
        throw new RuntimeException("Not implemented yet!");
    }
}