package me.cerratolabs.io.file.configloader.configuration.adapters.json;

import me.cerratolabs.io.file.configloader.configuration.adapters.generic.GenericNode;

import java.util.HashMap;

/**
 * Class used to handle the JSON file map.
 * This class extends from 'YAMLNode' since the operation is exactly the same as in the other class.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public class JSONNode extends GenericNode {

    public JSONNode() {
        map = new HashMap<>();
    }
}