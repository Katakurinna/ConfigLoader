package me.cerratolabs.io.file.configloader.configuration.interfaces.managers;

import java.io.File;
import java.io.IOException;

public interface ConfigFileLoader {

    void save() throws IOException;

    void load() throws IOException;

    void setNewFile(File file);

}