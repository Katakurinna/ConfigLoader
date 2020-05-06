package me.cerratolabs.configloader.test.version200;

import me.cerratolabs.configloader.ConfigLoader;
import me.cerratolabs.configloader.factory.ConfigFactory;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.5.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConfigurationUsageCSVTypes {
    private static ConfigLoader loader;

    @Test
    @BeforeAll
    public static void getConfigLoaderAndLoad() throws IOException {
        loader = ConfigFactory.getConfigLoader("testFiles\\file.csv");
        assertEquals(ConfigLoader.class, loader.getClass());
        loader.load();
        assertEquals(HashMap.class, loader.getMap().getClass());
    }

    @Test
    @Order(2)
    public void checkIfExistNodes() {
        // Existing node 'name'
        String key = "name";
        assertTrue(loader.existKey(key), key);

        // The next node dont exist.
        key = "nameasdasda";
        assertFalse(loader.existKey(key), key);

        // Existing node 'department'
        key = "department";
        assertTrue(loader.existKey(key), key);

        // Not existing node
        key = "resul";
        assertFalse(loader.existKey(key), key);


        // Existing node 'result'
        key = "result";
        assertTrue(loader.existKey(key), key);

        // Existing node 'result'
        key = "result";
        assertTrue(loader.existKey(key), key);
    }

    @Test
    @Order(3)
    public void getSomeValuesFromNodes() {
        String key = "name";
        List<String> obtainedList = (List<String>) loader.get(key);
        List<String> expectedList = new ArrayList<>(Arrays.asList("amar", "rohini", "aman", "rahul", "pratik", "raunak", "suvam"));
        assertEquals(expectedList.toString(), obtainedList.toString());
        key = "rollno";
        System.out.println("Key: " + key);
        System.out.println(((List<String>) loader.get(key)).toString());
        key = "department";
        System.out.println("Key: " + key);
        System.out.println(((List<String>) loader.get(key)).toString());
        key = "result";
        System.out.println("Key: " + key);
        System.out.println(((List<String>) loader.get(key)).toString());
        key = "cgpa";
        System.out.println("Key: " + key);
        System.out.println(((List<String>) loader.get(key)).toString());
    }


    @Test
    @Order(4)
    public void setSomeValues() {
        String key = "players";
        String value = "Akane";
        loader.set(key, value);

        value = "Katakurinna";
        loader.set(key, value);

        value = "Nurio";
        loader.set(key, value);


        key = "lastLogin";
        value = "1586289320486";
        loader.set(key, value);

        key = "lastLogin";
        value = "1587289320486";
        loader.set(key, value);

        key = "lastLogin";
        value = "1588289320486";
        loader.set(key, value);

    }

    @Test
    @Order(5)
    public void getLastNewValues() {

        String key = "players";
        loader.get(key);
        assertEquals("[Akane, Katakurinna, Nurio]", loader.get(key));


        key = "lastLogin";
        System.out.println("lastlogin: " + loader.get(key));
    }

    @Test
    @Order(6)
    public void saveLastChanges() throws IOException {
        loader.save();
    }
}