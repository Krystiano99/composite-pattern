package org.kris;

import java.util.ArrayList;
import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {

    private final String color;
    private final String material;
    private final List<Block> childBlocks;

    public CompositeBlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
        this.childBlocks = new ArrayList<>();
    }

    @Override
    public void addBlock(Block block) {
        childBlocks.add(block);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public List<Block> getBlocks() {
        return childBlocks;
    }
}