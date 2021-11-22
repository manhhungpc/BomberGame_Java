package main.worlds;

import main.Utils;
import main.tiles.Tile;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class World {

    private int width, height;
    private char[][] tiles;

    // Entity position
    public static List<Position> playerPosition = new ArrayList<>();
    public static List<Position> balloonPosition = new ArrayList<>();

    public World(String path){
        loadWorld(path);
    }

    public void tick(){

    }

    public void render(Graphics g){
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0  || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        char typeTile = tiles[y][x];
        Tile t = Tile.tiles[typeTile];
        if (typeTile == 'f') return Tile.brickTile;
        if (typeTile == '1' || typeTile == '2' || typeTile == 'p' || typeTile == 'x') return Tile.grassTile;
        if(t == null)
            return Tile.fakeTile;
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.fileToString(path);
        String[] lines = file.split(Pattern.quote("\n"));
        String[] levelHeightWidth = lines[0].split(Pattern.quote(" "));
        int level = Integer.parseInt(levelHeightWidth[0]);
        height = Integer.parseInt(levelHeightWidth[1]);
        width = Integer.parseInt(levelHeightWidth[2]);

//        System.out.println(level + " " + height + " " + width);

        System.out.println("width = " + width);
        System.out.println("height = " + height);

        tiles = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = lines[i+1].charAt(j);
            }
        }


//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(tiles[i][j]);
//            }
//            System.out.println();
//        }

        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                if (tiles[y][x] == 'p') playerPosition.add(new Position(x, y));
                if (tiles[y][x] == '1') balloonPosition.add(new Position(x, y));
            }
        }


    }



    public static void main(String[] args) {
        int[] levelHeightWidth = null;
        char[][] tiles = null;
//        fileToChar(".\\src\\resource\\map\\level1.txt", tiles, levelHeightWidth);

        int height = levelHeightWidth[1];
        int width = levelHeightWidth[2];
        int level = levelHeightWidth[0];
        System.out.println(level + " " + height + " " + width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public void setTile(int x, int y, char data) {
        tiles[y][x] = data;
    }

    public char getCharTile(int x, int y) {
        return tiles[y][x];
    }
}