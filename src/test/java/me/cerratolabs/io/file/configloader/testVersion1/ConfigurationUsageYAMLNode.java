package me.cerratolabs.io.file.configloader.testVersion1;

import me.cerratolabs.io.file.configloader.configuration.adapters.yaml.YAMLNode;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;


public class ConfigurationUsageYAMLNode {

    private LinkedHashMap<Object, Object> map = new Yaml().load(getResource("yaml.yml"));
    private YAMLNode node = new YAMLNode();

    private static FileReader getResource(String path) {
        try {
            return new FileReader(new File(ConfigurationUsageYAMLNode.class.getClassLoader().getResource(path).toURI()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    {
        node.setMap(map);
    }

    @Test
    public void testIfExistKey() {
        // Existing node 'creation-date'
        String key = "creation-date";
        assertTrue(node.existKey(key), key);

        // The next node dont exist.
        key = "creation-dat";
        assertFalse(node.existKey(key), key);

        // Existing node with children
        key = "chat.prefix";
        assertTrue(node.existKey(key), key);

        // Not existing node with children
        key = "chat.juan";
        assertFalse(node.existKey(key), key);


        // Existing node with much children
        key = "chat.messages.es.user.errors.commands.invalid";
        assertTrue(node.existKey(key), key);

        // Not existing node with much children
        key = "chat.messages.es.user.errors.commands.invalid.not-found";
        assertFalse(node.existKey(key), key);
    }

    @Test
    public void testGetNode() {
        String key = "chat.messages.es.user.errors.commands.invalid";
        assertEquals("Wrong params.", node.get(key));

        key = "chat.messages.es.user.errors.commands";
        assertEquals(LinkedHashMap.class, node.get(key).getClass());

        key = "chat.messages.es.user.errors.commands.not-exist-node";
        assertEquals(null, node.get(key));

    }
}