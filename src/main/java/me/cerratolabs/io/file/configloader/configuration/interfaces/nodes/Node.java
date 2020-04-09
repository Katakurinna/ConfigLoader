package me.cerratolabs.io.file.configloader.configuration.interfaces.nodes;

import java.util.List;

/**
 * This interface is used to define the logic of nodes.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public interface Node {

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
    <T> T get(String key);

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
     * @param key    key node
     * @param object object value
     */
    void set(String key, Object object);

    /**
     * Set a new map to work on, this action
     * will delete the previous map stored in
     * memory (if there is no other variable
     * in another area pointing to it).
     *
     * @param object the new map you want
     *               to work.
     */
    void setMap(Object object);

    /**
     * Returns the map you are working on.
     * It does not return a copy, so any
     * changes to it will be a direct change
     * to the map.
     *
     * @return the map you use to work.
     */
    Object getMap();

    /**
     * It checks for the existence of a key,
     * returning true if found, or false otherwise.
     *
     * @param key key to check
     * @return true if it exists, false otherwise.
     */
    boolean existKey(String key);

    /**
     * Returns a list containing all the keys
     * contained in the map used by the node
     *
     * @return List with all keys.
     */
    List<Object> getKeyList();
}