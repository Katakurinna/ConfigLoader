package me.cerratolabs.io.file.configloader.configuration.adapters.yaml.managers;

import lombok.Getter;
import me.cerratolabs.configloader.ConfigFile;
import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.loaders.YAMLFileLoader;
import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.nodes.YAMLNode;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

public class YamlManager implements ConfigManager {

    @Getter private String name = "YAML";

    @Override
    public YAMLFileLoader parse(String path){
        return new YAMLFileLoader(path);
    }

    @Override
    public boolean matches(String path) {
        return path.endsWith(".yaml") || path.endsWith(".yml");
    }

    @Override
    public ConfigFile getConfig(String path) {
        return new ConfigFile(new YAMLFileLoader(path), new YAMLNode());
    }

}