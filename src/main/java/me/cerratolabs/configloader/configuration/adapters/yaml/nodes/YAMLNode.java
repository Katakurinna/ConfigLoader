package me.cerratolabs.configloader.configuration.adapters.yaml.nodes;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class YAMLNode implements Node {

    private Map<String, Node> map = new HashMap<>();
    private String key;
    @Getter @NonNull private Node parent;

    @Override
    public Node get(String key) {
        return map.get(key);
    }

    @Override
    public void addChildren(Node children) {
        map.put(children.getKey(), children);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getAbsoluteKey() {
        return (parent == null) ? key : parent.getAbsoluteKey() + "." + key;
    }

}