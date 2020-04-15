package me.cerratolabs.configloader.configuration.adapters.yaml;

import me.cerratolabs.configloader.configuration.adapters.generic.GenericNode;

import java.util.LinkedHashMap;

/**
 * Class used to handle the yaml file map.
 * This class extends from 'GenericNode' since the
 * operation is exactly the same as in the other class.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public class YAMLNode extends GenericNode {

    /**
     * YAMLNode constructor.
     * Initialize the parent map to HashMap.
     */
    public YAMLNode() {
        map = new LinkedHashMap<>();
    }
}