package me.cerratolabs.configloader.configuration.adapters.json;

import me.cerratolabs.configloader.configuration.adapters.generic.GenericNode;

import java.util.HashMap;

/**
 * Class used to handle the JSON file map.
 * This class extends from 'GenericNode' since the
 * operation is exactly the same as in the other class.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
public class JSONNode extends GenericNode {

    /**
     * JSONNode constructor.
     * Initialize the parent map to HashMap.
     */
    public JSONNode() {
        map = new HashMap<>();
    }
}