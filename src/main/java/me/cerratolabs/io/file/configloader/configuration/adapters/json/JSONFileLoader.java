package me.cerratolabs.io.file.configloader.configuration.adapters.json;

import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JSONFileLoader implements ConfigFileLoader {

    /* Class variables to use yaml */
    private File file; // File source
    private Node node; // Node map manager

    /**
     * Create a JSONFileLoader from path and Node.
     *
     * @param path file path.
     * @param node node manager.
     */
    public JSONFileLoader(String path, Node node) {
        this(new File(path), node);
    }

    /**
     * Create a JSONFileLoader from path and Node.
     *
     * @param file file to save/load.
     * @param node node manager.
     */
    public JSONFileLoader(File file, Node node) {
        this.file = file;
        this.node = node;
    }

    /**
     * Save the map of the node instance,
     * to the file instance.
     *
     * @throws IOException When it fails to save {@see IOException}.
     */
    @Override
    public void save() throws IOException {
        // Get the map and cast to JSONObject.
        JSONObject jsonObject = (JSONObject) JSONObject.wrap(node.getMap());
        // Initialize writer.
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        // Write the object and close the writer.
        writer.write(jsonObject.toString());
        writer.close();
    }

    /**
     * Load the map of the file instance,
     * in the node instance.
     */
    @Override
    public void load() throws IOException {
        String file = new String(Files.readAllBytes(Paths.get(this.file.toURI())));
        // Parse the JSON file to JSONObject.
        JSONObject obj = new JSONObject(file);
        // Set the map in the node instance.
        node.setMap(obj.toMap());

    }

    /**
     * Change the file instance to new file instance.
     *
     * @param file new file instance.
     */
    @Override
    public void setNewFile(File file) {
        this.file = file;
    }
}