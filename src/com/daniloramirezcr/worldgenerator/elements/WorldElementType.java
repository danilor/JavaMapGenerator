package com.daniloramirezcr.worldgenerator.elements;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldElementType implements Cloneable {


    private String identifier = "";
    private String label = "";
    private Color color = Color.white;
    private int minElevation = 0;
    private int maxElevation = 0;
    private int probabilityFactor = 1; // based upon the other elements
    private int maxGrowFactor = 0;


    /**
     * Constructor
     */
    public WorldElementType() {

    }

    /**
     * Constructor with all elements
     *
     * @param identifier        String
     * @param label             String
     * @param color             Color
     * @param minElevation      int
     * @param maxElevation      int
     * @param probabilityFactor int
     */
    public WorldElementType(String identifier, String label, Color color, int minElevation, int maxElevation, int probabilityFactor) {
        this.identifier = identifier;
        this.label = label;
        this.color = color;
        this.minElevation = minElevation;
        this.maxElevation = maxElevation;
        this.probabilityFactor = probabilityFactor;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMinElevation() {
        return minElevation;
    }

    public void setMinElevation(int minElevation) {
        this.minElevation = minElevation;
    }

    public int getMaxElevation() {
        return maxElevation;
    }

    public void setMaxElevation(int maxElevation) {
        this.maxElevation = maxElevation;
    }

    public int getProbabilityFactor() {
        return probabilityFactor;
    }

    public void setProbabilityFactor(int probabilityFactor) {
        this.probabilityFactor = probabilityFactor;
    }

    public int getMaxGrowFactor() {
        return maxGrowFactor;
    }

    public void setMaxGrowFactor(int maxGrowFactor) {
        this.maxGrowFactor = maxGrowFactor;
    }

    @Override
    public WorldElementType clone() {
        try {
            return (WorldElementType) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Here is where we define all the type of elements in the world map.
     *
     * @return WorldElementType[]
     */
    public static WorldElementType[] getArrayWithProbabilityFactor() {
        ArrayList<WorldElementType> list = new ArrayList<WorldElementType>();
        List<WorldElementType> listWithProbabilityDistribution = new ArrayList<>();


        list.add(new WorldElementType("G", "GRASS", new Color(35, 250, 117), 0, 4, 6));
        list.add(new WorldElementType("D", "DESERT", new Color(217, 211, 102), 0, 2, 3));
        list.add(new WorldElementType("M", "MOUNTAIN", new Color(90, 102, 94), 4, 16, 4));
        list.add(new WorldElementType("S", "SWAMP", new Color(140, 122, 140), 0, 4, 1));
        list.add(new WorldElementType("F", "FOREST", new Color(29, 122, 65), 0, 7, 5));

        WorldElementType[] types = list.toArray(new WorldElementType[0]);

        for (WorldElementType type : types) {
            for (int j = 0; j < type.probabilityFactor; j++) {
                listWithProbabilityDistribution.add(type.clone());
            }
        }
        return listWithProbabilityDistribution.toArray(new WorldElementType[0]);
    }


}


