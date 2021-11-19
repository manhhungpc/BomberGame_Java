package main;

import main.Game;

public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Tile Game!", 500, 1200);
        game.start();
    }

}