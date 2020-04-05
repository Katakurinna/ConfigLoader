package me.cerratolabs.io.file.configloader.configuration.adapters.properties;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.loaders.YAMLFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

/**
 * Example class for another ConfigLoaderManager
 */
public class PropertiesManager implements ConfigManager {

    @Getter private String name = "Properties";

    @Override
    public YAMLFileLoader parse(String path) {
        return new YAMLFileLoader(path);
    }

    @Override
    public boolean matches(String path) {
        return path.endsWith(".props") || path.endsWith(".config");
    }

    @Override
    public ConfigLoader getConfig(String path) {
        throw new RuntimeException("Not implemented yet!");
    }

}