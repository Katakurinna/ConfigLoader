package me.cerratolabs.io.file.configloader;

import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class ConfigLoader {
    private ConfigFileLoader loader;
    private Node node;

    public ConfigLoader(ConfigFileLoader loader, Node node) {
        this.loader = loader;
        this.node = node;
    }

    public void save() throws IOException {
        loader.save();
    }

    public void load() throws IOException {
        loader.load();
    }

    public void set(String key, String value) {
        if (key == null || key.isEmpty()) throw new NullPointerException("Param 'key' is null or empty");
        if (value == null) throw new NullPointerException("Param 'value' is null");
        node.set(key, value);

    }

    public Object get(String key) {
        if (key == null || key.isEmpty()) throw new NullPointerException("Param 'key' is null or empty");
        return node.get(key);
    }

    public void append(ConfigLoader configFile) {
    }

    public Map<String, String> getMap() {
        return null;
    }

    public LinkedList<String> getKeys() {
        return null;
    }

}