package com.daniloramirezcr.worldgenerator.elements;


import java.util.ArrayList;
import java.util.List;

public class WorldElementType implements Cloneable {


    private String identifier = "";
    private String label = "";
    private String color = "";
    private int minElevation = 0;
    private int maxElevation = 0;
    private int probabilityFactor = 1; // based upon the other elements


    /**
     * Constructor
     */
    public WorldElementType(){

    }

    /**
     * Constructor with all elements
     * @param identifier String
     * @param label String
     * @param color String
     * @param minElevation int
     * @param maxElevation int
     * @param probabilityFactor int
     */
    public WorldElementType(String identifier, String label, String color, int minElevation, int maxElevation, int probabilityFactor) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    @Override
    public WorldElementType clone() {
        try {
            WorldElementType clone = (WorldElementType) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Here is where we define all the type of elements in the world map.
     * @return WorldElementType[]
     */
    public static WorldElementType[] getArrayWithProbabilityFactor(){
        ArrayList <WorldElementType> list = new ArrayList<WorldElementType>();
        List<WorldElementType> listWithProbabilityDistribution = new ArrayList<>();


        list.add(
                new WorldElementType("G","GRASS","green",0,4,6)
        );
        list.add(
                new WorldElementType("D","DESERT","yellow",0,2,3)
        );
        list.add(
                new WorldElementType("M","MOUNTAIN","gray",4,16,4)
        );
        list.add(
                new WorldElementType("S","SWAMP","purple",0,4,1)
        );
        list.add(
                new WorldElementType("F","FOREST","darkgreen",0,7,5)
        );

        WorldElementType[] types = list.toArray(new WorldElementType[0]);

        for (WorldElementType type : types) {
            for (int j = 0; j < type.probabilityFactor; j++) {
                listWithProbabilityDistribution.add(type.clone());
            }
        }
        return listWithProbabilityDistribution.toArray(new WorldElementType[0]);
    }


}


