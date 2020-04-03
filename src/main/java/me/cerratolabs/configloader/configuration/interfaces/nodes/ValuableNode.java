package me.cerratolabs.configloader.configuration.interfaces.nodes;

import java.util.List;

public interface ValuableNode extends Node {

    int getInt(String key);

    long getLong(String key);

    String getString(String key);

    <T> List<T> getList(String key);
}