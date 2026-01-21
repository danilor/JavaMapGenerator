package com.daniloramirezcr.worldgenerator;

import me.tongfei.progressbar.ProgressBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The objective of this class is only to take the generated world, and to create an image
 * out of it
 */

public class WorldPrinter {

    /**
     * The World object being use to print
     */
    private World world;

    private int blockSize = 100;

    private final String extension = "png";

    public WorldPrinter(World world) {
        this.world = world;
    }


    public void setBlockSize(int s){
        this.blockSize = s;
    }

    public int getBlockSize(){
        return this.blockSize;
    }

    /**
     * This should generate an image out of a world map
     *
     * @param name String
     * @throws Exception If the world is not full, it will throw an exception
     */
    public void createImage(String name) throws Exception {


        if (world.isMapFull()) {
            System.out.println("Printing world map");
            // We only work if the map is full
            int width = world.getSquaredSize() * this.blockSize;
            int height = world.getSquaredSize() * this.blockSize;

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


            Graphics2D g2d = bufferedImage.createGraphics();

            // Fill the whole background as white
            g2d.setColor(Color.white);
            g2d.fillRect(0, 0, width, height);

            try (ProgressBar pb = new ProgressBar("Printing World map", (long) this.world.getSquaredSize() * this.world.getSquaredSize())) {
                for (int x = 0; x < world.getSquaredSize(); x++) {
                    for (int y = 0; y < world.getSquaredSize(); y++) {
                        pb.step();
                        g2d.setColor(world.getMap()[x][y].getType().getColor());
                        g2d.fillRect(x * this.blockSize, y * this.blockSize, (x * this.blockSize) + (this.blockSize - 1), (y * this.blockSize) + (this.blockSize - 1));

                    }
                }
            }


            File file = new File(name + "." + extension);
            ImageIO.write(bufferedImage, extension, file);

        } else {
            throw new Exception("World map must be full to be printable");
        }


    }
}
