package me.cerratolabs.io.file.configloader.configuration.interfaces.managers;

import java.io.File;
import java.io.IOException;

/**
 * This interface is used to define loaders.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public interface ConfigFileLoader {

    /**
     * Save the map of the node instance,
     * to the file instance.
     *
     * @throws IOException When it fails to save {@see IOException}.
     */
    void save() throws IOException;

    /**
     * Load the map of the file instance,
     * in the node instance.
     *
     * @throws IOException When it fails to load {@see IOException}.
     */
    void load() throws IOException;

    /**
     * Change the file instance to new file instance.
     *
     * @param file new file instance.
     */
    void setNewFile(File file);

}