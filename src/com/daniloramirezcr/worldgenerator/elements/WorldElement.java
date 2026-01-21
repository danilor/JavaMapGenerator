package com.daniloramirezcr.worldgenerator.elements;

import com.daniloramirezcr.worldgenerator.World;
import com.daniloramirezcr.worldgenerator.utilities.Coordinates;
import com.daniloramirezcr.worldgenerator.utilities.Direction;
import com.daniloramirezcr.worldgenerator.utilities.RandomUtil;

import java.util.Random;

public class WorldElement {

    private WorldElementType type = null;
    private Coordinates coord = new Coordinates(0,0);

    /**
     * This will indicate if this element is covered by all sides
     */
    private WorldElement north = null;
    private WorldElement east = null;
    private WorldElement south = null;
    private WorldElement west = null;

    private int elevation = 0;

    /**
     *
     */
    public WorldElement(Coordinates c){
//        this.type = WorldElementTypes.UNKNOWN;

       // System.out.println( WorldElementType.getArrayWithProbabilityFactor() );

        WorldElementType[] w = WorldElementType.getArrayWithProbabilityFactor();
        RandomUtil r = RandomUtil.getInstance();
        this.type = w[ r.getRandomObject().nextInt( w.length ) ];
        this.coord = c;
    }

    /**
     *
     * @param type
     */
    public WorldElement(Coordinates c,WorldElementType type){
        this.type = type;
        this.coord = c;
    }


    /**
     *
     * @return WorldElementType
     */
    public WorldElementType getType() {
        return type;
    }

    /**
     *
     * @param type WorldElementType
     */
    public void setType(WorldElementType type) {
        this.type = type;
    }

    @Override
    public String toString() {
//        return super.toString();
            if(this.isAssigned()){
                return this.type.toString().substring(0,1);
            }else{
                return "▯";
            }
    }

    /**
     * Getting the coordinates
     * @return Coordinates
     */
    public Coordinates getCoord() {
        return coord;
    }

    /**
     * Just to know if this element is already assigned
     * @return boolean
     */
    public boolean isAssigned(){
        return !(this.type.getIdentifier().equals("U"));
    }


    /**
     * Just to know if this element is already covered
     */
    public boolean isCovered(){
        return (this.north != null && this.east != null && this.south != null && this.west != null);
    }


    public boolean setNeighbor(Direction d, WorldElement e){
        if(d == Direction.N ){
            this.north = e;
        }
        if(d == Direction.E ){
            this.east = e;
        }
        if(d == Direction.S ){
            this.south = e;
        }
        if(d == Direction.W ){
            this.west = e;
        }
        return this.isCovered();
    }

    /**
     * Gets the current terrain elevation
     * @return int
     */
    public int getElevation() {
        return elevation;
    }

    /**
     * Sets the current terrain eleveation
     * @param elevation int Elevation number. It should be between 0 and 256. No negative numbers please.
     */
    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}
