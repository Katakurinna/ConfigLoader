package me.cerratolabs.configloader;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigLoader {

    // This map contains the key, value of file
    private ConfigEntry map = new ConfigEntry();

    public ConfigLoader(File file, Loader loader) {
    }

    public void save() throws IOException {

    }

    public void load() throws IOException {

    }

    public void set(String key, Object value) {

    }

    public void set(String key, List<Object> value) {

    }

    public Object get(String key) {
        return null;
    }

}