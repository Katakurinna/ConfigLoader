package me.cerratolabs.io.file.configloader.configuration.adapters.yaml;

import lombok.RequiredArgsConstructor;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import static me.cerratolabs.io.file.configloader.utils.KeyManager.*;

@RequiredArgsConstructor
public class YAMLNode implements Node {

    private LinkedHashMap<Object, Object> map;

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

    private Object get(String key, Object lastObject) {
        if (key == null || key.isEmpty() || lastObject == null) return null;
        // If dont have more child nodes, return the wanted value of the key.
        if (isLastNode(key)) return convertToLinkedHashMap(lastObject).get(key);

        // Return the call to this method with the next key block and the map of first key node.
        return get(getNextNodeBlock(key), convertToLinkedHashMap(lastObject).get(getFirstKeyNode(key)));
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

        LinkedHashMap<Object, Object> map = addChild(getKeyNodeWithoutLastNode(key));
        map.put(getLastKeyNode(key), object);
    }

    @Override
    public void setMap(Object object) {
        map = convertToLinkedHashMap(object);
    }

    @Override
    public Object getMap() {
        return map;
    }

    public LinkedHashMap<Object, Object> convertToLinkedHashMap(Object object) {
        if (!(object instanceof LinkedHashMap)) return null;
        return (LinkedHashMap<Object, Object>) object;
    }

    @Override
    public LinkedList<Object> getKeyList() {
        return new LinkedList<>(map.keySet());
    }

    @Override
    public boolean existKey(String key) {
        return existKey(key, map);
    }

    private boolean existKey(String key, LinkedHashMap<Object, Object> object) {
        // Comprobamos que el objeto pasado por parametro tenga valor
        if (object == null) return false;

        // Comprobar si es el ultimo, si es el último, devolver si existe.
        if (isLastNode(key)) return (object.get(key) != null);

        // Comprobar que existe el bloque actual, si no existe, devolver falso.
        if (object.get(getFirstKeyNode(key)) == null) return false;

        // Comprobar el bloque siguiente.
        return existKey(getNextNodeBlock(key), convertToLinkedHashMap(object.get(getFirstKeyNode(key))));
    }

    private LinkedHashMap<Object, Object> addChild(String key) {
        return addChild(key, map);
    }

    private LinkedHashMap<Object, Object> addChild(String key, LinkedHashMap<Object, Object> parent) {
        // If key is null or empty, or parent is null, returned null.
        if (key == null || key.isEmpty() || parent == null) return null;


        // If is the last key node and the key node exist, return the LinkedHashMap.
        if (isLastNode(key) && parent.get(getFirstKeyNode(key)) != null) return convertToLinkedHashMap(parent.get(key));

        // If is the last key node and the key node dont exist, return new LinkedHashMap.

        if (isLastNode(key) && parent.get(getFirstKeyNode(key)) == null) {
            return addNewLinkedHashMapChild(key, parent);
        }

        // If the key node exist, return the call of this method creating next key node
        if (parent.get(getFirstKeyNode(key)) != null) return addChild(getNextNodeBlock(key), convertToLinkedHashMap(parent.get(getFirstKeyNode(key))));

        // If the key node dont exist, create new LinkedHashMap and call to the method addChillWithoutAskingIfExist
        return addChillWithoutAskingIfExist(key, parent);

    }

    private LinkedHashMap<Object, Object> addChillWithoutAskingIfExist(String key, LinkedHashMap<Object, Object> parent) {
        return (isLastNode(key)) ? addNewLinkedHashMapChild(key, parent) : addChillWithoutAskingIfExist(getNextNodeBlock(key), addNewLinkedHashMapChild(getFirstKeyNode(key), parent));
    }

    private LinkedHashMap<Object, Object> addNewLinkedHashMapChild(String key, LinkedHashMap<Object, Object> parent) {
        LinkedHashMap<Object, Object> newMap = new LinkedHashMap<>();
        parent.put(getFirstKeyNode(key), newMap);
        return newMap;
    }
}