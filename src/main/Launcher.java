package main;

import main.tiles.Tile;
import main.worlds.World;

public class Launcher {
    public static String PATH = ".\\src\\resource\\map\\level2.txt";
    private static int width, height;

    public static void findWidthHeight() {
        World world = new World(PATH);
        width = world.getWidth() * Tile.TILE_WIDTH;
        height = world.getHeight() * Tile.TILE_HEIGHT;
    }

    public static void main(String[] args){
        findWidthHeight();
        Game game = new Game("Tile Game!", width, height);
        game.start();
    }

}