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
    }

    public Object get(String key) {
        return null;
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