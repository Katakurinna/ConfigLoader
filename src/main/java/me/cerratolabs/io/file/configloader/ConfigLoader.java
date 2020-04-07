package me.cerratolabs.io.file.configloader;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.LinkedList;

public class ConfigLoader {
    @Getter private ConfigFileLoader loader;
    @Getter private Node node;

    public ConfigLoader(ConfigFileLoader loader, Node node) {
        this.loader = loader;
        this.node = node;
    }

    /**
     * This method save the configuration in file.
     *
     * @throws IOException If have some {@link IOException}.
     */
    public void save() throws IOException {
        loader.save();
    }

    /**
     * This method load the configuration
     *
     * @throws IOException
     */
    public void load() throws IOException {
        loader.load();
    }

    /**
     * Add new node to the configuration,
     * if the parents nodes dont exist, first create it and
     * next add the falue in the node.
     *
     * @param key   node key.
     * @param value Object value.
     */
    public void set(String key, Object value) {
        if (key == null || key.isEmpty()) throw new NullPointerException("Param 'key' is null or empty");
        if (value == null) throw new NullPointerException("Param 'value' is null");
        node.set(key, value);
    }

    /**
     * Returns the object that is on the node.
     *
     * @param key node key.
     * @return Object value.
     */
    public Object get(String key) {
        if (key == null || key.isEmpty()) throw new NullPointerException("Param 'key' is null or empty");
        return node.get(key);
    }

    /**
     * Append this config with another config.
     *
     * @param configFile {@link ConfigLoader} to add.
     */
    public void append(ConfigLoader configFile) {
        throw new NotImplementedException();
    }

    public Object getMap() {
        return node.getMap();
    }

    public LinkedList<Object> getKeys() {
        return node.getKeyList();
    }

    public boolean existKey(String key) {
        return node.existKey(key);
    }
}