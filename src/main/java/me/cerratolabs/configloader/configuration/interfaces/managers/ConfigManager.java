package me.cerratolabs.configloader.configuration.interfaces.managers;

import me.cerratolabs.configloader.ConfigFile;

public interface ConfigManager extends ConfigComparator {

    String getName();
    <P> P parse(String path);

    ConfigFile getConfig(String path);
}