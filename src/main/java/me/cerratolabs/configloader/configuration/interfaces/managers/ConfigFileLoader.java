package me.cerratolabs.configloader.configuration.interfaces.managers;

import java.io.IOException;

public interface ConfigFileLoader {

    void save() throws IOException;

    void load() throws IOException;


}