package main.game;

public class Launcher {
    private static final String TITLE = "Bomberman";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    public static void main(String[] args) {
        new Game(TITLE, HEIGHT, WIDTH);
    }
}
