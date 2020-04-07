package me.cerratolabs.io.file.configloader.utils;

public class KeyManager {

    public static String getNextNodeBlock(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        // If key dont contains '.', dont have more nodes.
        if (!key.contains(".")) return null;

        // Returns the key starting with the node following the first point
        return key.substring(key.indexOf(".") + 1);
    }

    public static String getFirstKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        if (!key.contains(".")) return key;

        return key.substring(0, key.indexOf("."));
    }

    public static String getKeyNodeWithoutLastNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;
        return key.substring(0, key.lastIndexOf("."));
    }

    public static String getLastKeyNode(String key) {
        // If key is null or empty return null
        if (key == null || key.isEmpty()) return null;

        return key.substring(key.lastIndexOf(".") + 1);
    }

    public static boolean isLastNode(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return !key.contains(".");
    }

    public static boolean containsTwoNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        return key.split(".").length == 2;
    }

    public static boolean containsMultipleNodes(String key) {
        // If key is null or empty return false
        if (key == null || key.isEmpty()) return false;
        if (!key.contains(".")) return false;
        return key.split(".").length > 1;
    }
}