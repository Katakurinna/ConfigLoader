package me.cerratolabs.configloader.configuration.adapters.yaml.loaders;

import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YAMLLoader implements ConfigLoader {

    private Yaml yaml;
    private File file;
    Map<String, Object> map;

    public YAMLLoader(String file) {
        this(new File(file));
    }

    public YAMLLoader(File file) {
        yaml = new Yaml();
        this.file = file;
    }

    public void save() throws IOException {
        System.out.println("pepe jaja");
        yaml.dump(map, new FileWriter("yaml2.yml"));
    }

    public void load() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        map = yaml.load(bufferedReader);
    }

}