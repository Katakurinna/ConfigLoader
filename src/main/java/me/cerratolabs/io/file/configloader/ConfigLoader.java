package me.cerratolabs.io.file.configloader;

import lombok.Getter;
import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.List;

/**
 * This class is used to control the file,
 * regardless of how each loader or node does things.
 */
public class ConfigLoader {
    @Getter private ConfigFileLoader loader;
    @Getter private Node node;

    /**
     * Create new instance of ConfigLoader with ConfigFileLoader and Node
     *
     * @param loader loader to use
     * @param node   node to use
     * @see ConfigFileLoader
     * @see Node
     */
    public ConfigLoader(ConfigFileLoader loader, Node node) {
        this.loader = loader;
        this.node = node;
    }

    /**
     * Save the map of the node instance,
     * to the file instance.
     *
     * @throws IOException When it fails to save {@see IOException}.
     */
    public void save() throws IOException {
        loader.save();
    }

    /**
     * Load the map of the file instance,
     * in the node instance.
     *
     * @throws IOException When it fails to load {@see IOException}.
     */
    public void load() throws IOException {
        loader.load();
    }

    /**
     * Set the value of a key node.
     * To separate node from another node, you can use '.'.
     * This create all the key node block.
     * Example:
     * You want to create 'chat.en.messages.join' with the value
     * The method search if exists parents of join, if not,
     * create it and put join with value.
     * You can use the following type of value objects:
     * <p><ul>
     * <li>Integer</li>
     * <li>Long</li>
     * <li>Float</li>
     * <li>Double</li>
     * <li>Character</li>
     * <li>String</li>
     * <li>ArrayList{@literal <Object>}</li>
     * </ul><p>
     *
     * @param key   key node
     * @param value object value
     */
    public void set(String key, Object value) {
        if (key == null || key.isEmpty()) throw new NullPointerException("Param 'key' is null or empty");
        if (value == null) throw new NullPointerException("Param 'value' is null");
        node.set(key, value);
    }

    /**
     * Get the value of a key node.
     * The method can return the following objects types:
     * <p><ul>
     * <li>Integer</li>
     * <li>Long</li>
     * <li>Float</li>
     * <li>Double</li>
     * <li>Character</li>
     * <li>String</li>
     * <li>ArrayList{@literal <Object>}</li>
     * </ul><p>
     *
     * @param key key node
     * @return Object or null if not exist.
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

    /**
     * Returns the map you are working on.
     * It does not return a copy, so any
     * changes to it will be a direct change
     * to the map.
     *
     * @return the map you use to work.
     */
    public Object getMap() {
        return node.getMap();
    }

    /**
     * Returns a list containing all the keys
     * contained in the map used by the node
     *
     * @return List with all keys.
     */
    public List<Object> getKeys() {
        return node.getKeyList();
    }

    /**
     * It checks for the existence of a key,
     * returning true if found, or false otherwise.
     *
     * @param key key to check
     * @return true if it exists, false otherwise.
     */
    public boolean existKey(String key) {
        return node.existKey(key);
    }
}