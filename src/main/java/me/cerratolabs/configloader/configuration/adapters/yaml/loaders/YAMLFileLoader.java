package me.cerratolabs.configloader.configuration.adapters.yaml.loaders;

import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigFileLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YAMLFileLoader implements ConfigFileLoader {

    private Yaml yaml;
    private File file;
    Map<String, Object> map;

    public YAMLFileLoader(String file) {
        this(new File(file));
    }

    public YAMLFileLoader(File file) {
        yaml = new Yaml();
        this.file = file;
    }

    public void save() throws IOException {
        yaml.dump(map, new FileWriter(file));
    }

    public void load() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        map = yaml.load(bufferedReader);
    }

}