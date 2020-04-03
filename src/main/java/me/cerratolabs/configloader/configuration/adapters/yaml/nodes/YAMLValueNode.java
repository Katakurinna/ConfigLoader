package me.cerratolabs.configloader.configuration.adapters.yaml.nodes;

import lombok.NonNull;
import me.cerratolabs.configloader.configuration.interfaces.nodes.Node;
import me.cerratolabs.configloader.configuration.interfaces.nodes.ValuableNode;

import java.util.List;

public class YAMLValueNode extends YAMLNode implements ValuableNode {


    public YAMLValueNode(@NonNull Node parent, Object value) {
        super(parent);
    }

    @Override
    public int getInt(String key) {
        return 0;
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key) {
        return null;
    }
}