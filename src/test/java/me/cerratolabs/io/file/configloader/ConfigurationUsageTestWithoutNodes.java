package me.cerratolabs.io.file.configloader;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

public class ConfigurationUsageTestWithoutNodes {

    @Test
    public void knowCreationDateClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        Object obj = map.get("creation-date");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowParentClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        Object obj = yaml.load(new FileReader("yaml.yml"));
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        Object obj = map.get("chat");

        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatPrefixClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        Object obj = chat.get("prefix");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        Object obj = chat.get("messages");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesEsUserJoinClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        Object obj = user.get("join");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesEsUserJoinGameClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        LinkedHashMap join = (LinkedHashMap) user.get("join");
        Object obj = join.get("game");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesEsUserErrorsClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        Object obj = user.get("errors");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesEsUserErrorsInvalidClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        LinkedHashMap errors = (LinkedHashMap) user.get("errors");
        LinkedHashMap commands = (LinkedHashMap) errors.get("commands");
        Object obj = commands.get("invalid");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowChatMessagesEsUserErrorsNotFoundClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        LinkedHashMap errors = (LinkedHashMap) user.get("errors");
        LinkedHashMap commands = (LinkedHashMap) errors.get("commands");
        System.out.println(commands.get("not-found").getClass()); // Know class
        List<String> lista = (List<String>) commands.get("not-found"); // list
        System.out.println(lista.get(0));
        System.out.println(lista.get(1));
        System.out.println(lista);
    }

    @Test
    public void knowChatMessagesEsUserErrorsPepehoneClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap chat = (LinkedHashMap) map.get("chat");
        LinkedHashMap messages = (LinkedHashMap) chat.get("messages");
        LinkedHashMap es = (LinkedHashMap) messages.get("es");
        LinkedHashMap user = (LinkedHashMap) es.get("user");
        LinkedHashMap errors = (LinkedHashMap) user.get("errors");
        LinkedHashMap commands = (LinkedHashMap) errors.get("commands");
        System.out.println(commands.get("pepephone").getClass()); // Know class
        List<Integer> lista = (List<Integer>) commands.get("pepephone"); // list
        System.out.println(lista.get(0));
        System.out.println(lista.get(1));
        System.out.println(lista);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void knowPlayersClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        Object obj = map.get("players");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowPlayersNurioClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap players = (LinkedHashMap) map.get("players");
        Object obj = players.get("Nurio");
        System.out.println(obj.getClass());
    }

    @Test
    public void knowPlayersNurioIdClassYaml() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap players = (LinkedHashMap) map.get("players");
        LinkedHashMap nurio = (LinkedHashMap) players.get("Nurio");
        Object obj = nurio.get("id");
        System.out.println(obj.getClass());
    }

    @Test(expected = ClassCastException.class)
    public void castingNurioIdIntegerClassToString() throws Exception {
        Yaml yaml = new Yaml();
        LinkedHashMap map = yaml.load(new FileReader("yaml.yml"));
        LinkedHashMap players = (LinkedHashMap) map.get("players");
        LinkedHashMap nurio = (LinkedHashMap) players.get("Nurio");
        String id = (String) nurio.get("id");
        System.out.println(id);
    }


}