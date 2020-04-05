package me.cerratolabs.configloader.configuration.adapters.properties;

import lombok.Getter;
import me.cerratolabs.configloader.ConfigFile;
import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigManager;
import me.cerratolabs.configloader.configuration.adapters.yaml.loaders.YAMLFileLoader;

public class PropertiesManager implements ConfigManager {

    @Getter private String name = "Properties";

    @Override
    public YAMLFileLoader parse(String path){
        return new YAMLFileLoader(path);
    }

    @Override
    public boolean matches(String path) {
        return path.endsWith(".props") || path.endsWith(".config");
    }

    @Override
    public ConfigFile getConfig(String path){
        throw new RuntimeException("Not implemented yet!");
    }

}