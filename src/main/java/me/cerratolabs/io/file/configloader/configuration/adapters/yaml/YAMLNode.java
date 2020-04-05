package me.cerratolabs.io.file.configloader.configuration.adapters.yaml;

import lombok.RequiredArgsConstructor;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedHashMap;
import java.util.LinkedList;

@RequiredArgsConstructor
public class YAMLNode implements Node {

    private LinkedHashMap<Object, Object> map;

    @Override
    public Object get(String key) {
        return get(key, map);
    }

    private Object get(String key, Object lastObject) {
        // If dont have more child nodes, return the wanted value of the key.
        if (isLastNode(key)) return convertToLinkedHashMap(lastObject).get(key);

        // Return the call to this method with the next key block and the map of first key node.
        return get(getNextNodeBlock(key), convertToLinkedHashMap(lastObject).get(getFirstKeyNode(key)));
    }

    @Override
    public void set(String key, Object object) {
        throw new NotImplementedException();
        /*
        if (key.split(".").length == 1) {
            map.put(key, object);
        }

        convertToLinkedHashMap(get(key, map)).put(key, object);
        */
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
        if (!(object instanceof LinkedHashMap)) {
            System.out.println(object);
            System.out.println(object.getClass().getName());
            return null;
        }
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

    private String getNextNodeBlock(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        // If key dont contains '.', dont have more nodes.
        if (!key.contains(".")) return null;

        // Returns the key starting with the node following the first point
        return key.substring(key.indexOf(".") + 1);
    }

    private String getFirstKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        if (!key.contains(".")) return key;

        return key.substring(0, key.indexOf("."));
    }

    private boolean isLastNode(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return !key.contains(".");
    }

    private boolean containsTwoNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return key.split(".").length == 2;
    }

    private boolean containsMultipleNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        if (!key.contains(".")) return false;
        return key.split(".").length > 1;
    }

    private LinkedHashMap<Object, Object> addChild(String key) {
        return addChild(key, map);
    }

    private LinkedHashMap<Object, Object> addChild(String key, LinkedHashMap<Object, Object> parent) {
        // If key is null or empty, or parent is null, returned null.
        if (key == null || key.isEmpty() || parent == null) return null;

        // If is the last key node and the key node exist, return the linkedhashmap.
        if (isLastNode(key) && parent.get(getFirstKeyNode(key)) != null) return convertToLinkedHashMap(parent.get(getFirstKeyNode(key)));

        // If is the last key node and the key node dont exist, return new LinkedHashMap.
        if (isLastNode(key) && parent.get(getFirstKeyNode(key)) == null) return new LinkedHashMap<>();

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