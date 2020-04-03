package me.cerratolabs.configloader.configuration.adapters.yaml.managers;

import lombok.Getter;
import me.cerratolabs.configloader.ConfigFile;
import me.cerratolabs.configloader.configuration.adapters.yaml.loaders.YAMLLoader;
import me.cerratolabs.configloader.configuration.adapters.yaml.nodes.YAMLNode;
import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigManager;

public class YamlManager implements ConfigManager {

    @Getter private String name = "YAML";

    @Override
    public YAMLLoader parse(String path){
        return new YAMLLoader(path);
    }

    @Override
    public boolean matches(String path) {
        return path.endsWith(".yaml") || path.endsWith(".yml");
    }

    @Override
    public ConfigFile getConfig(String path) {
        return new ConfigFile(new YAMLLoader(path), new YAMLNode(null));
    }

}