package me.cerratolabs.configloader.test.version200;

import me.cerratolabs.configloader.ConfigLoader;
import me.cerratolabs.configloader.factory.ConfigFactory;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoadSaveCSV {
    private static ConfigLoader loader;

    @BeforeAll
    public static void getConfigLoaderAndLoad() throws IOException {
        loader = ConfigFactory.getConfigLoader("testFiles\\file.csv");
        assertEquals(ConfigLoader.class, loader.getClass());
        loader.load();
        assertEquals(HashMap.class, loader.getMap().getClass());
    }

    @Test
    public void saveLastChanges() throws IOException {
        loader.getLoader().setNewFile(new File("testFiles\\copy.csv"));
        loader.save();
    }
}