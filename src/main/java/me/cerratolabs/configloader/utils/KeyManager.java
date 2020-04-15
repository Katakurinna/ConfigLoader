package me.cerratolabs.configloader.utils;

import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;

/**
 * This class is used to obtain different types of node,
 * or check their existence or use.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 * @see Node
 */
public class KeyManager {

    /**
     * Check that the key is not null or empty, if it is,
     * it returns null, if not, it checks if it has more
     * key blocks, if it doesn't have it it returns null,
     * if it has it, it returns the following key block.
     *
     * @param key key block.
     * @return next key block, or null.
     */
    public static String getNextNodeBlock(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        // If key dont contains '.', dont have more nodes.
        if (!key.contains(".")) return null;

        // Returns the key starting with the node following the first point
        return key.substring(key.indexOf(".") + 1);
    }

    /**
     * Returns the first key in the key block,
     * returns the same key if it has no more blocks,
     * and null if the key is null or empty.
     *
     * @param key key block.
     * @return the same key if it has no more blocks,
     * and null if the key is null or empty.
     */
    public static String getFirstKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        if (!key.contains(".")) return key;

        return key.substring(0, key.indexOf("."));
    }

    /**
     * Returns the key block, without the last key.
     *
     * @param key key block.
     * @return the key block, without the last key,
     * or null if key is null or empty.
     */
    public static String getKeyNodeWithoutLastNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;
        return key.substring(0, key.lastIndexOf("."));
    }

    /**
     * Returns the last key in the key block,
     * or null if the supplied key is null or empty
     *
     * @param key key block.
     * @return the last key in the key block,
     * or null if the supplied key is null or empty
     */
    public static String getLastKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        return key.substring(key.lastIndexOf(".") + 1);
    }

    /**
     * Check if the key is the last one in the block,
     * or if there are more.
     *
     * @param key key block.
     * @return if the key is the last one in the block,
     * or if there are more. Null if the supplied key
     * is null or empty
     */
    public static boolean isLastNode(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return !key.contains(".");
    }
}