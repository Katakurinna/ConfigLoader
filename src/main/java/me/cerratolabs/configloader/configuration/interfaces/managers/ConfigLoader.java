package me.cerratolabs.configloader.configuration.interfaces.managers;

import java.io.IOException;

public interface ConfigLoader {

    void save() throws IOException;

    void load() throws IOException;


}