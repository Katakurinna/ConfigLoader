package me.cerratolabs.io.file.configloader.testVersion1;

import me.cerratolabs.io.file.configloader.ConfigLoader;
import me.cerratolabs.io.file.configloader.factory.ConfigFactory;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUsage {
    public static void main(String[] args) throws IOException {
        getConfigLoaderAndLoad();
        checkIfExistNodes();
        getSomeValuesFromNodes();
        setSomeValues();
        getLastNewValues();
        saveLastChanges();
    }

    private static ConfigLoader loader;

    private static String getResource(String path) {
        try {
            return ConfigurationUsageYAMLNode.class.getClassLoader().getResource(path).toString().substring(6);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getConfigLoaderAndLoad() throws IOException {
        loader = ConfigFactory.getConfigLoader(getResource("yaml.yml"));
        assertEquals(ConfigLoader.class, loader.getClass());
        assertEquals(null, loader.getMap());
        loader.load();
        assertEquals(LinkedHashMap.class, loader.getMap().getClass());
    }

    public static void checkIfExistNodes() {
        // Existing node 'creation-date'
        String key = "creation-date";
        assertTrue(loader.existKey(key), key);

        // The next node dont exist.
        key = "creation-dat";
        assertFalse(loader.existKey(key), key);

        // Existing node with children
        key = "chat.prefix";
        assertTrue(loader.existKey(key), key);

        // Not existing node with children
        key = "chat.juan";
        assertFalse(loader.existKey(key), key);


        // Existing node with much children
        key = "chat.messages.es.user.errors.commands.invalid";
        assertTrue(loader.existKey(key), key);

        // Not existing node with much children
        key = "chat.messages.es.user.errors.commands.invalid.not-found";
        assertFalse(loader.existKey(key), key);
    }

    public static void getSomeValuesFromNodes() {
        String key = "chat.messages.es.user.errors.commands.invalid";
        assertEquals("Wrong params.", loader.get(key));

        key = "chat.messages.es.user.errors.commands";
        assertEquals(LinkedHashMap.class, loader.get(key).getClass());

        key = "chat.messages.es.user.errors.commands.not-exist-node";
        assertEquals(null, loader.get(key));

        key = "chat.messages.es.user.errors.commands.not-exist-node";
        assertEquals(null, loader.get(key));

        key = "players.Nurio.id";
        assertEquals(1, loader.get(key));
    }

    private static Date date;

    public static void setSomeValues() {
        String key = "players.Katakurinna.id";
        int id = 5;
        loader.set(key, id);

        key = "players.Katakurinna.last-login";
        date = new Date();
        loader.set(key, date);

        key = "players.Katakurinna.login-counts";
        Object obj;
        try {
            obj = loader.get(key);
        } catch (NullPointerException n) {
            obj = null;
            System.out.println("null");
        }
        Integer i;
        if (obj == null) {
            i = 1;
        } else {
            i = (Integer) obj;
        }
        loader.set(key, i);
    }

    public static void getLastNewValues() {
        String key = "players.Katakurinna.id";
        try {
            assertEquals(5, loader.get(key));
        } catch (NullPointerException n) {
            System.out.println("otro null");
        }

        try {
            key = "players.Katakurinna.last-login";
            assertEquals(date, loader.get(key));
        } catch (NullPointerException n) {
            System.out.println("otro null");
        }

        try {
            key = "players.Katakurinna.login-counts";
            assertEquals(1, loader.get(key));
        } catch (NullPointerException n) {
            System.out.println("otro null");
        }
    }

    public static void saveLastChanges() throws IOException {
        loader.save();
    }
}