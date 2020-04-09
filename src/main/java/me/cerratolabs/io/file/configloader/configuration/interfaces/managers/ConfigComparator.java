package me.cerratolabs.io.file.configloader.configuration.interfaces.managers;

/**
 * This interface is used to create comparators.
 *
 * @author Alejandro '@Katakurinna' Cerrato Espejo
 * @version 1.0.0
 */
public interface ConfigComparator {
    /**
     * If path match with yaml extension,
     * return true, else, false.
     *
     * @param path file path.
     * @return if is yaml extension or not (end with .yaml or .yml)
     */
    boolean matches(String path);
}