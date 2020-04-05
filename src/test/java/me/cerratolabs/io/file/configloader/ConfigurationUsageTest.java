package me.cerratolabs.io.file.configloader;

import me.cerratolabs.io.file.configloader.factory.ConfigFactory;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConfigurationUsageTest {

    @Test
    public void pop() throws IOException {
       /* ConfigFile cfg = new ConfigFile(new YAMLFileLoader("yaml.yml"));
        cfg.load();
        for (String key : cfg.getKeys()) {
            System.out.println(cfg.get(key));
        }*/
    }

    @Test
    public void pip() throws Exception {
        Yaml yaml = new Yaml();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("yaml.yml"));
        Map<String, Object> map = yaml.load(bufferedReader);

        for (String key : map.keySet()) {
            Map<String, Object> testing = (Map<String, Object>) map.get(key);
            for (String keySecondMap : testing.keySet()) {
                System.out.println(testing.get(keySecondMap));
            }
        }
    }

    @Test
    public void test222() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("yaml2.yml"));
        Map<String, Object> map = yaml.load(bufferedReader);
        Object obj = get(map.get("chat")).get("messages");
        Object obj2 = get(obj).get("es");
        Object obj3 = get(obj2).get("user");
        Object obj4 = get(obj3).get("errors");
        Object obj5 = get(obj4).get("commands");
        Object obj6 = get(obj5).get("not-found");

        List<String> obj6List = (List<String>) obj6;
        System.out.println(Objects.toString(obj6));
        System.out.println(obj6List.get(0));
    }

    @Test
    public void test3333222() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("yaml2.yml"));
        Map<String, Object> map = yaml.load(bufferedReader);
        Object obj = get(map.get("chat")).get("messages");
        Object obj2 = get(obj).get("es");
        Object obj3 = get(obj2).get("user");
        Object obj4 = get(obj3).get("errors");
        Object obj5 = get(obj4).get("commands");
        Object obj6 = get(obj5).get("not-found");

        List<String> obj6List = (List<String>) obj6;
        System.out.println(Objects.toString(obj6));
        System.out.println(obj6List.get(0));
    }

    private Map<String, Object> get(Object map) {
        return (Map<String, Object>) ((Map) map);
    }

/*
    @Test
    public void poppp(){
        Config cfg = ConfigManager.getFile("path");
        //Node node = cfg.get("chat.messages.es");
        String gmaeJoinMessage = cfg.getList("chat.messages.es.game.join");
        List<Integer> ids = cfg.getList("users.nurio.ids");
        int nurioId = ids.get(0);
        int nurioID2 = cfg.getInt("users.nurio.id");
    }
*/

    @Test
    public void configComparator() throws IOException {
        ConfigFile cfg = ConfigFactory.getConfigFile("yaml.yml");
        System.out.println(cfg.get("chat"));


        ConfigFile cfgProp = ConfigFactory.getConfigFile("noseque.props");
        cfgProp.load();
        System.out.println(cfgProp.get("chat"));
    }

    @Test
    public void createFile() throws IOException {
        ConfigFile cfgProp = ConfigFactory.createConfigFile("yaml.yml");
        cfgProp.save();
    }

}