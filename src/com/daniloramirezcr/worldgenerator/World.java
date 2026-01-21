package com.daniloramirezcr.worldgenerator;

import com.daniloramirezcr.worldgenerator.elements.WorldElement;

import com.daniloramirezcr.worldgenerator.elements.WorldElementType;
import com.daniloramirezcr.worldgenerator.elements.WorldElementTypes;
import com.daniloramirezcr.worldgenerator.utilities.ConsoleColors;
import com.daniloramirezcr.worldgenerator.utilities.Coordinates;
import com.daniloramirezcr.worldgenerator.utilities.Direction;
import com.daniloramirezcr.worldgenerator.utilities.RandomUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * THE IDEA:
 * <p>
 * The object World should have a list of seeds called elementSeeder.
 * These seeds will be "throw" all around the world. Each seed is randomly one type of the different World Element Types (right now defined as water, desert, mountain, grass, but they can be more
 * in the future). So, all those seeds have coordinates wherever they landed in the map.
 * After that, we select one seed randomly from that list, and expand it (or make it grow) to north, east west or south. We select one Direction for each step. Depending on the direction,
 * we generate a new world element in the new location, next to our initial element. IF WE CREATED A NEW ELEMENT, that element is being added to the seeds list (elementSeeder).
 * If we select one seed randomly, and that seed doesn't have space where to grow, that seed is completed and removed from the seeder list.
 * We continue this process until the map is completed and all spaces are assigned. The list should increase quite a lot during the first steps of the process since the map
 * is empty, but as it shrinks, the number of seeds will decrease as well.
 * <p>
 * The MAP starts at [0,0] on the top left corner of it.
 */

public class World {

    // The seed for the Random generator
    private long seed = Long.parseLong("1"); // The same seed will produce the exact same map, no matter how any times we execute the process.
    // The size of the world
    private int squaredSize = 100;

    // The random singleton
    private RandomUtil rand = null;

    // all the world elements
    private WorldElement[][] map = null;

    // This is the list that should contain all seeders
    private final List<WorldElement> elementSeeder = new ArrayList<>();

    // This will hold the initial number of seeds we want to throw to the world. This should be related to the size of the world like 5%
    private int initialElementSeederNumber = 35;


    /**
     * Constructor
     */
    public World() {

    }

    /**
     * This is the main process that generates the map
     */
    public void generate() {
        this.prepareRandomness();
        this.initializeWorldElements();
        this.growWorld();

    }


    /**
     * This will only print the map into the console. Using colors, so it needs a Color available console
     * to be able to see them
     */
    public void consolePrint() {
        for (WorldElement[] worldElements : this.map) {
            // Iterate through each column in the current row
            for (WorldElement worldElement : worldElements) {

                String color = "";
                if (worldElement.getType().getIdentifier().equals("G")) {
                    color = ConsoleColors.GREEN_BACKGROUND_BRIGHT;

                } else if (worldElement.getType().getIdentifier().equals("D")) {
                    color = ConsoleColors.YELLOW_BACKGROUND;
                } else if (worldElement.getType().getIdentifier().equals("W")) {
                    color = ConsoleColors.BLUE_BACKGROUND;
                } else if (worldElement.getType().getIdentifier().equals("M")) {
                    color = ConsoleColors.BLACK_BACKGROUND;
                } else if (worldElement.getType().getIdentifier().equals("S")) {
                    color = ConsoleColors.PURPLE_BACKGROUND;
                } else if (worldElement.getType().getIdentifier().equals("F")) {
                    color = ConsoleColors.GREEN_BACKGROUND;
                } else {
                    color = ConsoleColors.RESET;
                }

                System.out.print(color + worldElement.getElevation() + " " + ConsoleColors.RESET); // Print element followed by a space
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    /*
        PRIVATES
     */

    /**
     * This should seed the random generator
     */
    private void prepareRandomness() {
        this.rand = RandomUtil.getInstance(); // This should be a random for the whole app including the seeder
        this.rand.setSeed(this.seed);
    }

    /**
     * This will initialize all required world elements
     */
    private void initializeWorldElements() {
        WorldElementType u = new WorldElementType();
        u.setIdentifier("U");
        u.setLabel("Unknown");
        this.map = new WorldElement[this.squaredSize][this.squaredSize]; // We defined the map size at once
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                this.map[i][j] = new WorldElement(new Coordinates(i, j), u.clone());
            }
        }
        this.throwSeeds();
    }


    /**
     * This will throw the seeds across the map
     */
    private void throwSeeds() {
        for (int i = 0; i < this.initialElementSeederNumber; i++) {
            int x = this.rand.getRandom(0, this.squaredSize);
            int y = this.rand.getRandom(0, this.squaredSize);
            if (!map[x][y].isAssigned()) {
                WorldElement newWorldElement = new WorldElement(new Coordinates(x, y));
                this.elementSeeder.add(newWorldElement);
                this.assignElementToWorld(newWorldElement);
            }
        }
    }

    /**
     * This should be the main function
     */
    private void growWorld() {
        while (!this.isMapFull()) {

            if (this.elementSeeder.toArray().length == 0) {
                this.throwSeeds();
            } else {
                int randomSpace = this.rand.getRandom(0, this.elementSeeder.toArray().length);

                if (this.elementSeeder.get(randomSpace).isCovered()) {
                    this.elementSeeder.remove(randomSpace);
                } else {
                    Direction d = this.rand.getRandomDirection();
                    int x = this.elementSeeder.get(randomSpace).getCoord().x;
                    int y = this.elementSeeder.get(randomSpace).getCoord().y;
                    if (d == Direction.N && y > 0) {
                        y--;
                    } else if (d == Direction.S && y < this.squaredSize - 1) {
                        y++;
                    } else if (d == Direction.E && x < this.squaredSize - 1) {
                        x++;
                    } else if (d == Direction.W && x > 0) {
                        x--;
                    } else {
                        // NOTHING
                    }

                    if (!map[x][y].isAssigned()) {
                        map[x][y].setType(this.elementSeeder.get(randomSpace).getType());
                        this.elementSeeder.add(map[x][y]);
                    }

                    boolean completed = this.elementSeeder.get(randomSpace).setNeighbor(d, map[x][y]);
                    if (completed) {
                        this.elementSeeder.remove(randomSpace);
                    }
                }
            }


        }

//        this.consolePrint();

    }


    /**
     * This should assign any element to the map
     *
     * @param w
     */
    private void assignElementToWorld(WorldElement w) {
        this.map[w.getCoord().x][w.getCoord().y] = w;
    }

    /*
     *
     * GETS AND SETS
     * -------------------------------------------------------------------
     *
     */

    /**
     * Gets the seed
     *
     * @return long Seed
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Sets the seed
     *
     * @param seed long The seed number
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * Getrs the squared size as a single number
     *
     * @return int The size
     */
    public int getSquaredSize() {
        return squaredSize;
    }

    /**
     * Set the squared size
     *
     * @param squaredSize int the size
     */
    public void setSquaredSize(int squaredSize) {
        this.squaredSize = squaredSize;
    }


    /**
     * Gets the map
     *
     * @return WordElement[][]
     */
    public WorldElement[][] getMap() {
        return map;
    }

    public void setInitialElementSeederNumber(int initialElementSeederNumber) {
        this.initialElementSeederNumber = initialElementSeederNumber;
    }

    public int getInitialElementSeederNumber() {
        return this.initialElementSeederNumber;
    }

    public boolean isMapFull() {
        for (WorldElement[] worldElements : this.map) {
            for (WorldElement worldElement : worldElements) {
                if (!worldElement.isCovered()) {
                    return false;
                }
            }
        }
        return true;
    }
}
