package me.cerratolabs.configloader.configuration.interfaces.nodes;

public interface Node {

    /**
     * Get an Node, parent of this node.
     *
     * @return Node parent of this node.
     */
    <T> T getParent();

    /**
     * Get an object containing a part of map (Map, List, String, etc).
     *
     * @param key the key of the part of map you want.
     * @return object with the part of map you want.
     */
    <T> T get(String key);

    void addChildren(Object children);

    String getKey();

    void setKey(String key);

    String getAbsoluteKey();
}