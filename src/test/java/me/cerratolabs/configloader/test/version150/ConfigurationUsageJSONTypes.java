package me.cerratolabs.configloader.test.version150;

import me.cerratolabs.configloader.ConfigLoader;
import me.cerratolabs.configloader.factory.ConfigFactory;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConfigurationUsageJSONTypes {
    private static ConfigLoader loader;

    @Test
    @BeforeAll
    public static void getConfigLoaderAndLoad() throws IOException {
        loader = ConfigFactory.getConfigLoader("testFiles\\file.json");
        assertEquals(ConfigLoader.class, loader.getClass());
        loader.load();
        assertEquals(HashMap.class, loader.getMap().getClass());
    }

    @Test
    @Order(2)
    public void checkIfExistNodes() {
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

    @Test
    @Order(3)
    public void getSomeValuesFromNodes() {
        String key = "chat.messages.es.user.errors.commands.invalid";
        assertEquals("Wrong params.", loader.get(key));

        key = "chat.messages.es.user.errors.commands";
        assertEquals(HashMap.class, loader.get(key).getClass());

        key = "chat.messages.es.user.errors.commands.not-exist-node";
        assertNull(loader.get(key));

        key = "players.Nurio.id";
        assertEquals(1, loader.get(key));


        key = "chat.messages.es.user.join.game";
        assertEquals("%s joined the game", loader.get(key));
    }

    private Date date;

    @Test
    @Order(4)
    public void setSomeValues() {
        String key = "players.Akane.id";
        loader.set(key, 5);

        key = "players.Akane.last-login";
        date = new Date();
        date.setTime(1586289320486L);
        loader.set(key, date);

        key = "players.Akane.login-counts";
        Integer i = (Integer) loader.get(key);
        i = (i == null) ? 1 : i + 1;
        loader.set(key, i);

    }

    @Test
    @Order(5)
    public void getLastNewValues() {
        String key = "players.Akane.id";
        assertEquals(5, loader.get(key));

        key = "players.Akane.last-login";
        date = new Date();
        date.setTime(1586289320486L);
        assertEquals(date.getTime(), ((Date) loader.get(key)).getTime());

        key = "players.Akane.login-counts";
        assertEquals(7, loader.get(key));
    }

    @Test
    @Order(6)
    public void saveLastChanges() throws IOException {
        loader.save();
    }
}