package me.cerratolabs.io.file.configloader.configuration.interfaces.managers;

import me.cerratolabs.io.file.configloader.ConfigLoader;

public interface ConfigManager extends ConfigComparator {

    String getName();

    ConfigLoader getConfig(String path);
}