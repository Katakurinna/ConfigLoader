package me.cerratolabs.io.file.configloader.configuration.adapters.yaml;

import me.cerratolabs.io.file.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.io.file.configloader.configuration.interfaces.nodes.Node;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

/**
 * Class to save and load yaml files.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public class YAMLFileLoader implements ConfigFileLoader {

    /* Class variables to use yaml */
    private Yaml yaml; // From snakeyaml
    private File file; // File source
    private Node node; // Node map manager

    /**
     * Create a YAMLFileLoader from path and Node.
     *
     * @param path file path.
     * @param node node manager.
     */
    public YAMLFileLoader(String path, Node node) {
        this(new File(path), node);
    }

    /**
     * Create a YAMLFileLoader from path and Node.
     *
     * @param file file to save/load.
     * @param node node manager.
     */
    public YAMLFileLoader(File file, Node node) {
        yaml = new Yaml();
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
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        yaml.dump(node.getMap(), bufferedWriter);
    }

    /**
     * Load the map of the file instance,
     * in the node instance.
     *
     * @throws IOException When it fails to load {@see IOException}.
     */
    @Override
    public void load() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        node.setMap(yaml.load(bufferedReader));
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

    /**
     * Parse the node instance map to String,
     * and return it.
     *
     * @return map parsed to String.
     */
    public String dumpMap() {
        return yaml.dump(node.getMap());
    }
}