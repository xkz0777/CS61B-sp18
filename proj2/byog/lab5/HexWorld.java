package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 27;
    private static final int HEIGHT = 18;

    /**
     * @param startX, startY: he coordinates of the top left corner after filling the hexagon into a rectangle
     * @param size:   size of the hexagon
     */
    private static void drawHexagon(TETile[][] world, TETile shape, int startX, int startY, int size) {
        for (int i = 0; i < size; ++i) {
            for (int j = startX - i + size - 1; j < startX + i + 2 * size - 1; ++j) {
                world[j][startY + i] = world[j][startY + 2 * size - i - 1] = shape;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        drawHexagon(world, Tileset.MOUNTAIN, 10, 0, 3);
        drawHexagon(world, Tileset.FLOWER, 5, 3, 3);
        drawHexagon(world, Tileset.FLOWER, 15, 3, 3);
        drawHexagon(world, Tileset.GRASS, 0, 6, 3);
        drawHexagon(world, Tileset.GRASS, 10, 6, 3);
        drawHexagon(world, Tileset.GRASS, 20, 6, 3);
        drawHexagon(world, Tileset.SAND, 5, 9, 3);
        drawHexagon(world, Tileset.SAND, 15, 9, 3);
        drawHexagon(world, Tileset.TREE, 10, 12, 3);

        ter.renderFrame(world);
    }
}
