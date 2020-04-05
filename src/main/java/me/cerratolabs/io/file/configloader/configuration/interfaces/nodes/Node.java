package me.cerratolabs.io.file.configloader.configuration.interfaces.nodes;

public interface Node {

    /**
     * Get an object containing a part of map (Map, List, String, etc).
     *
     * @param key the key of the part of map you want.
     * @return object with the part of map you want.
     */
    <T> T get(String key);

    void set(String key, Object object);

    void setMap(Object object);

    Object getMap();

    boolean existKey(String key);
}