import com.daniloramirezcr.worldgenerator.World;
import com.daniloramirezcr.worldgenerator.WorldPrinter;
import com.daniloramirezcr.worldgenerator.elements.WorldElement;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("World Map Generator");
        System.out.println("===================");
        System.out.println();
        System.out.println("Please wait while the World is being generated");

        Random r = new Random();

        World world = new World();
        world.setSeed(r.nextLong()); // The seeder for this map
        world.setSquaredSize(500); // The squared map size. This is the size of each side
        world.setInitialElementSeederNumber(21); // The number of initial seeds into the map

        System.out.println("Squared Size: " + world.getSquaredSize());
        System.out.println("Seeder Pool Size: " + world.getInitialElementSeederNumber());
        System.out.println("Seed: " + Long.toString(world.getSeed()));
        System.out.println();

        world.generate();
        // world.consolePrint();

        WorldPrinter wp = new WorldPrinter(world);
        wp.setBlockSize(15);

        try {
            wp.createImage(Long.toString(world.getSeed()));
        } catch (Exception e) {
            System.out.println("Image could not be created");
        }

        world.consolePrint();

    }
}