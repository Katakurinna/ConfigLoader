package me.cerratolabs.io.file.configloader.testVersion1;

import org.junit.jupiter.api.*;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetMethod {
    private static String getResource(String path) {
        try {
            return ConfigurationUsageYAMLNode.class.getClassLoader().getResource(path).toString().substring(6);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testYamlMethod() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader(getResource("yaml.yml")));
        HashMap<Object, Object> katakurinna = new HashMap<>();
        ((HashMap<Object, Object>) map.get("players")).put("katakurinna", katakurinna);
        int id = 5;
        katakurinna.put("id", id);

        Date date = new Date();
        katakurinna.put("last-login", date);

        Integer i = (katakurinna.get("login-counts") == null) ? 1 : (Integer) katakurinna.get("login-counts");
        katakurinna.put("login-counts", i);
        FileWriter writer = new FileWriter(getResource("yaml.yml"));
        String toSave = yaml.dump(map);
        writer.write(toSave);
        System.out.println(toSave);
        writer.close();
    }

}