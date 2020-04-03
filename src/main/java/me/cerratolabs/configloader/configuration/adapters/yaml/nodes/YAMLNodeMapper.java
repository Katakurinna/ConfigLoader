package me.cerratolabs.configloader.configuration.adapters.yaml.nodes;

import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;
import me.cerratolabs.configloader.configuration.interfaces.nodes.NodeMapper;

import java.util.Map;

public class YAMLNodeMapper implements NodeMapper {

    private Node parent;
    private Map<String, Object> map;

    public YAMLNodeMapper(Map<String, Object> map) {
        this.map = map;
    }


    private void mapNodes() {

        for (String key : map.keySet()) {
            Node children = createNode(map.get(key));

        }
    }

    private Node createNode(Object map) {
        return null;
    }

    @Override
    public Node getParentNode() {
        return parent;
    }
}