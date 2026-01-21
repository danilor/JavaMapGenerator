package com.daniloramirezcr.worldgenerator.utilities;

public class Coordinates {
    public int x = 0;
    public int y = 0;

    /**
     * Coordinates constructor
     * @param x int The X position
     * @param y int The Y position
     */
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
        if(this.x < 0) this.x = 0;
        if(this.y < 0) this.y = 0;
    }


    public void expand(int x, int y){
        this.x += x;
        this.y += y;
        if(this.x < 0) this.x = 0;
        if(this.y < 0) this.y = 0;
    }


}
