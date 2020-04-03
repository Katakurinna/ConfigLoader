package me.cerratolabs.configloader;

import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigLoader;
import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConfigFile {
    private ConfigLoader loader;
    private Node node;

    public ConfigFile(ConfigLoader loader, Node node) {
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

    public void setList(String key, List<Object> value) {

    }

    public Node get(String key) {
        return null;
    }

    public void append(ConfigFile configFile) {
    }

    public Map<String, String> getMap() {
        return null;
    }

    public LinkedList<String> getKeys() {
        return null;
    }

}