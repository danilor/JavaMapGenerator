package com.daniloramirezcr.worldgenerator.utilities;

import java.util.Random;

/**
 * This class will ensure there is only one random generator
 * across the app. This is because we want to seed this Random process.
 */
public class RandomUtil {

    // The singleton instance controller
    private static RandomUtil single_instance = null;

    // The custom seed
    private long seed = 12345678;
    private Random r = null;

    /**
     * Constructor
     */
    public RandomUtil() {
        this.initialize();
    }

    /**
     * Sets the random seed
     *
     * @param seed The seed we want to use as random generator
     */
    public void setSeed(long seed) {
        this.seed = seed;
        this.initialize();
    }

    /**
     * Generates a random number between 2 integers
     *
     * @param low  The initial number (included)
     * @param high The last number (included)
     * @return int The random number. The high number is excluded
     */
    public int getRandom(int low, int high) {
        return this.r.nextInt(high - low) + low;
    }

    /**
     * This should return a random direction defined into North, South, East or West
     * @return Direction The direction as N, E, S or W
     */
    public Direction getRandomDirection() {
        int result = this.getRandom(1, 5);
        return switch (result) {
            case 1 -> Direction.N;
            case 2 -> Direction.E;
            case 3 -> Direction.S;
            case 4 -> Direction.W;
            default -> Direction.NA;
        };
    }

    public Random getRandomObject(){
        return this.r;
    }

    /**
     * Initializes the random object
     */
    private void initialize() {
        this.r = new Random(this.seed);
    }


    /**
     * The static method to get the singleton class
     * @return RandomUtil Class instance
     */
    public static synchronized RandomUtil getInstance() {
        if (single_instance == null) {
            single_instance = new RandomUtil();
        }
        return single_instance;
    }
}
