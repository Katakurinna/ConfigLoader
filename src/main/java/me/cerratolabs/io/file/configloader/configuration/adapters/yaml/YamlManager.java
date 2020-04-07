package me.cerratolabs.io.file.configloader.configuration.adapters.yaml;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigManager;

public class YamlManager implements ConfigManager {

    @Getter private String name = "YAML";

    /**
     * If path match with yaml extension
     * @param path file path.
     * @return if is yaml extension or not
     */
    @Override
    public boolean matches(String path) {
        return path.endsWith(".yaml") || path.endsWith(".yml");
    }

    /**
     * Return new instance of ConfigLoader.
     * @param path file path.
     * @return ConfigLoader instance.
     */
    @Override
    public ConfigLoader getConfig(String path) {
        YAMLNode node = new YAMLNode();
        return new ConfigLoader(new YAMLFileLoader(path, node), node);
    }

}