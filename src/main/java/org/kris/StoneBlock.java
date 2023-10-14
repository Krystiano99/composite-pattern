package org.kris;

public class StoneBlock implements Block {

    private final String color;
    private final String material;

    public StoneBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}