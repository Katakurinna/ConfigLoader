package me.cerratolabs.io.file.configloader.configuration.adapters.generic;

import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static me.cerratolabs.io.file.configloader.utils.KeyManager.*;

/**
 * Class used to handle generic file map's.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
public class GenericNode implements Node {

    protected Map<Object, Object> map;

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
    @Override
    public Object get(String key) {
        if (key == null || key.isEmpty()) return null;
        return get(key, map);
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
     * @param map on which the search is performed
     * @return Object or null if not exist.
     * @see #get(String)
     */
    private Object get(String key, Object map) {
        if (key == null || key.isEmpty() || map == null) return null;
        // If dont have more child nodes, return the wanted value of the key.
        if (isLastNode(key)) return convertToMap(map).get(key);

        // Return the call to this method with the next key block and the map of first key node.
        return get(getNextNodeBlock(key), convertToMap(map).get(getFirstKeyNode(key)));
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
     * @param key    key node
     * @param object object value
     */
    @Override
    public void set(String key, Object object) {
        // If dont have any to add, dont do nothing.
        if (key == null || key.isEmpty() || object == null) return;

        Map<Object, Object> map = addChild(getKeyNodeWithoutLastNode(key));
        map.put(getLastKeyNode(key), object);
    }

    /**
     * Set a new map to work on, this action
     * will delete the previous map stored in
     * memory (if there is no other variable
     * in another area pointing to it).
     *
     * @param object the new map you want
     *               to work.
     */
    @Override
    public void setMap(Object object) {
        map = convertToMap(object);
    }

    /**
     * Returns the map you are working on.
     * It does not return a copy, so any
     * changes to it will be a direct change
     * to the map.
     *
     * @return the map you use to work.
     */
    @Override
    public Object getMap() {
        return map;
    }

    /**
     * Cast object to Map instance.
     * If it is not an instance of this,
     * it returns null.
     *
     * @param object object you want to cast
     * @return The same object casted to {@code Map},
     * or null if not an instance of it.
     * @see Map
     */
    public Map<Object, Object> convertToMap(Object object) {
        if (!(object instanceof Map)) return null;
        return (Map) object;
    }

    /**
     * Returns a list containing all the keys
     * contained in the map used by the node
     *
     * @return List with all keys.
     */
    @Override
    public List<Object> getKeyList() {
        return new LinkedList<>(map.keySet());
    }

    /**
     * It checks for the existence of a key,
     * returning true if found, or false otherwise.
     *
     * @param key key to check
     * @return true if it exists, false otherwise.
     */
    @Override
    public boolean existKey(String key) {
        return existKey(key, map);
    }

    /**
     * It checks for the existence of a key,
     * returning true if found, or false otherwise.
     *
     * @param key key to check
     * @param map map on which the search is performed
     * @return true if it exists, false otherwise.
     */
    private boolean existKey(String key, Map<Object, Object> map) {
        // Comprobamos que el objeto pasado por parametro tenga valor
        if (map == null) return false;

        // Comprobar si es el ultimo, si es el último, devolver si existe.
        if (isLastNode(key)) return (map.get(key) != null);

        // Comprobar que existe el bloque actual, si no existe, devolver falso.
        if (map.get(getFirstKeyNode(key)) == null) return false;

        // Comprobar el bloque siguiente.
        return existKey(getNextNodeBlock(key), convertToMap(map.get(getFirstKeyNode(key))));
    }

    /**
     * Add children checking if they exist or not,
     * to create them, from the first to the last.
     *
     * @param key key block to add
     * @return the last child.
     * @see #addChild(String, Map)
     */
    private Map<Object, Object> addChild(String key) {
        return addChild(key, map);
    }

    /**
     * Add children checking if they exist or not,
     * to create them, from first to last. Use the
     * map passed by parameter as a starting point.
     *
     * @param key key block to add
     * @param map map on which the method work
     * @return the last child.
     */
    private Map<Object, Object> addChild(String key, Map<Object, Object> map) {
        // If key is null or empty, or parent is null, returned null.
        if (key == null || key.isEmpty() || map == null) return null;


        // If is the last key node and the key node exist, return the Map.
        if (isLastNode(key) && map.get(getFirstKeyNode(key)) != null) return convertToMap(map.get(key));

        // If is the last key node and the key node dont exist, return new Map.

        if (isLastNode(key) && map.get(getFirstKeyNode(key)) == null) {
            return addNewMapChild(key, map);
        }

        // If the key node exist, return the call of this method creating next key node
        if (map.get(getFirstKeyNode(key)) != null) return addChild(getNextNodeBlock(key), convertToMap(map.get(getFirstKeyNode(key))));

        // If the key node dont exist, create new Map and call to the method addChillWithoutAskingIfExist
        return addChillWithoutAskingIfExist(key, map);

    }

    /**
     * Create all children assuming they don't exist.
     *
     * @param key    key block to add.
     * @param parent map on which the method work.
     * @return the last child.
     * @see #addChild(String)
     */
    private Map<Object, Object> addChillWithoutAskingIfExist(String key, Map<Object, Object> parent) {
        return (isLastNode(key)) ? addNewMapChild(key, parent) : addChillWithoutAskingIfExist(getNextNodeBlock(key), addNewMapChild(getFirstKeyNode(key), parent));
    }

    /**
     * Create a new child in the form of Map
     * on the map passed by parameter, and return
     * the created child.
     *
     * @param key    child key.
     * @param parent map parent.
     * @return the new child.
     */
    private Map<Object, Object> addNewMapChild(String key, Map<Object, Object> parent) {
        Map<Object, Object> newMap = new HashMap();
        parent.put(getFirstKeyNode(key), newMap);
        return newMap;
    }
}