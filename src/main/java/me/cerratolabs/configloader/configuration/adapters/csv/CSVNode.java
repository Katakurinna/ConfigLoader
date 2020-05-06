package me.cerratolabs.configloader.configuration.adapters.csv;

import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVNode implements Node {
    private Map<String, List<String>> map = new HashMap();

    @Override
    public List<String> get(String key) {
        return map.get(key);
    }

    @Override
    public void set(String key, Object object) {
        if (!existKey(key)) addNewNode(key);
        if (object instanceof List) {
            setFromList(key, object);
            return;
        }
        if (object instanceof String) {
            setFromString(key, object);
            return;
        }
        throw new IllegalArgumentException("Expected " + List.class + " or " + String.class + ". Received: " + object.getClass());
    }

    private void setFromList(String key, Object object) {
        List<String> list = map.get(key);
        List<String> objectList = (List<String>) object;
        for (String item : objectList) {
            list.add(item);
        }
    }

    private void setFromString(String key, Object object) {
        List<String> list = map.get(key);
        String item = (String) object;
        list.add(item);
    }

    @Override
    public void setMap(Object object) {
        if (object instanceof Map) {
            map = (Map) object;
            return;
        }
        if (object instanceof List) {
            convertToMap(object);
            return;
        }
        ArrayList<String> lista = new ArrayList<String>();
        throw new IllegalArgumentException("Expected " + List.class + " or " + Map.class + ". Received: " + object.getClass());
    }

    @Override
    public Object getMap() {
        return map;
    }

    @Override
    public boolean existKey(String key) {
        System.out.println(key + " " + map.containsKey(key));
        return map.containsKey(key);
    }

    @Override
    public List<Object> getKeyList() {
        return new ArrayList<>(map.keySet());
    }

    private void convertToMap(Object object) {
        // Convert object to list.
        List<String[]> csvList = (List<String[]>) object;
        // If list dont have contents, stop method.
        if (csvList.isEmpty()) return;
        // Get first list of keys.
        String[] keys = csvList.get(0);

        for (int i = 0; i < keys.length; i++) {
            addNewNode(keys[i].trim());
        }

        keys = this.map.keySet().toArray(new String[keys.length]);

        // If dont have data, stop method.
        if (csvList.size() == 1) return;

        // For each row, add the data.
        for (int i = 1; i < csvList.size(); i++) {
            // Get current row
            String[] currentLine = csvList.get(i);
            for (int j = 0; j < keys.length; j++) {
                List<String> list = map.get(keys[j]);
                list.add(currentLine[j].trim());
            }
        }
    }

    private void addNewNode(String key) {
        map.put(key.trim(), new ArrayList<String>());
    }
}