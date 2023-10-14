package org.kris;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByColorRecursively(color, blocks);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterialRecursively(material, blocks);
    }

    @Override
    public int count() {
        return 0;
    }

    private Optional<Block> findBlockByColorRecursively(String color, List<Block> blockList) {
        for (Block block : blockList) {
            if (color.equalsIgnoreCase(block.getColor())) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                Optional<Block> result = findBlockByColorRecursively(color, ((CompositeBlock) block).getBlocks());
                if (result.isPresent()) {
                    return result;
                }
            }
        }

        return Optional.empty();
    }

    private List<Block> findBlocksByMaterialRecursively(String material, List<Block> blockList) {
        List<Block> matchingBlocks = new ArrayList<>();

        for (Block block : blockList) {
            if (material.equalsIgnoreCase(block.getMaterial())) {
                matchingBlocks.add(block);
            }

            if (block instanceof CompositeBlock) {
                List<Block> nestedMatchingBlocks = findBlocksByMaterialRecursively(material, ((CompositeBlock) block).getBlocks());
                matchingBlocks.addAll(nestedMatchingBlocks);
            }
        }

        return matchingBlocks;
    }

}