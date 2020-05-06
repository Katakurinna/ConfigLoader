package me.cerratolabs.configloader.configuration.adapters.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import me.cerratolabs.configloader.configuration.interfaces.managers.ConfigFileLoader;
import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVFileLoader implements ConfigFileLoader {

    private File file; // File source
    private Node node; // Node map manager

    /**
     * Create a CSVFileLoader from path and Node.
     *
     * @param path file path.
     * @param node node manager.
     */
    public CSVFileLoader(String path, Node node) {
        this(new File(path), node);
    }

    /**
     * Create a CSVFileLoader from path and Node.
     *
     * @param file file to save/load.
     * @param node node manager.
     */
    public CSVFileLoader(File file, Node node) {
        this.file = file;
        this.node = node;
    }

    @Override
    public void save() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        Map map = (Map) node.getMap();
        List<String[]> list = convertMapToList(map);
        CSVWriter writer = new CSVWriter(bufferedWriter);
        writer.writeAll(list);
        writer.close();
    }

    @Override
    public void load() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        CSVReader reader = new CSVReader(bufferedReader);
        try {
            node.setMap(reader.readAll());
        } catch (CsvException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void setNewFile(File file) {
        // If file is null.
        if (file == null || file.isDirectory()) return;
        this.file = file;
    }

    private List<String[]> convertMapToList(Map map) {
        List<Object> keys = node.getKeyList();
        List<String[]> list = new ArrayList<>();
        for (Object obj : keys) {
            String key = (String) obj;
            list.add(getKeyElements(key, map));
        }
        return list;
    }

    private String[] getKeyElements(String key, Map map) {
        List<Object> objectList = (List<Object>) map.get(key);
        String[] list = new String[objectList.size() + 1];
        list[0] = key;
        for (int i = 1; i < list.length; i++) {
            list[i] = (String) objectList.get(i - 1);
        }
        return list;
    }
}