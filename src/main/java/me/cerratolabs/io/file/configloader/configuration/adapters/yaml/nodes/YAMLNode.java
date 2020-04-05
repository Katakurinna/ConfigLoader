package me.cerratolabs.io.file.configloader.configuration.adapters.yaml.nodes;

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

    public Object get(String key, Object lastObject) {
        return (!isTheLastNode(key)) ? get(getNextNodeBlock(key), convertToLinkedHashMap(lastObject).get(getFirstKeyNode(key))) : convertToLinkedHashMap(lastObject).get(key);
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
    public boolean existKey(String key) {
        return existKey(key, map);
    }

    @Override
    public LinkedList<Object> getKeyList() {
        return new LinkedList<>(map.keySet());
    }

    public boolean existKey(String key, LinkedHashMap<Object, Object> object) {

        // Comprobamos que el objeto pasado por parametro tenga valor
        if (object == null) return false;

        // Comprobar si es el ultimo, si es el último, devolver si existe.
        if (isTheLastNode(key)) return (object.get(key) != null);

        // Comprobar que existe el bloque actual, si no existe, devolver falso.
        if (object.get(getFirstKeyNode(key)) == null) return false;

        // Comprobar el bloque siguiente.
        return existKey(getNextNodeBlock(key), convertToLinkedHashMap(object.get(getFirstKeyNode(key))));
    }

    public String getNextNodeBlock(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        // If key dont contains '.', dont have more nodes.
        if (!key.contains(".")) return null;

        // Returns the key starting with the node following the first point
        return key.substring(key.indexOf(".") + 1);
    }

    public String getFirstKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        if (!key.contains(".")) return key;

        return key.substring(0, key.indexOf("."));
    }

    public boolean isTheLastNode(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return !key.contains(".");
    }

    public boolean containsTwoNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return key.split(".").length == 2;
    }

    public boolean containsMultipleNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        if (!key.contains(".")) return false;
        return key.split(".").length > 1;
    }

    public void addChild(String key) {
        if (key == null || key.isEmpty()) return;

        if (key.split(".").length > 1) {

        }
        Object obj = get(key);
        if (!(obj instanceof LinkedHashMap)) {
            obj = get(key.substring(0, key.lastIndexOf(".")));
        }
        convertToLinkedHashMap(obj).put(key.lastIndexOf("."), new LinkedHashMap());
    }

}