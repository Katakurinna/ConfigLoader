package me.cerratolabs.io.file.configloader.configuration.adapters.yaml;

import me.cerratolabs.io.file.configloader.configuration.adapters.generic.GenericNode;

import java.util.LinkedHashMap;

/**
 * Class used to handle the yaml file map.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public class YAMLNode extends GenericNode {
    public YAMLNode() {
        map = new LinkedHashMap<>();
    }
}