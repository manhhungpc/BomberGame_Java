package main.entities.creatures;

import main.Handler;
import main.TestTime;
import main.gfx.Animation;
import main.gfx.Assets;
import main.states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Creature {

//    private static final int BOMB_TIME = 2;
    private static final int BOMB_TIME = 20;
    private static final int FLAME_TIME = 5;

    private final Animation bombGif;
//    private final int startTime;
    private final long startTime;
    private boolean alive = true;
    private final Flame flame;
    private int left, right, up, down;
    private boolean flameAlive = false;

    public Bomb(Handler handler, GameState gameState, float x, float y, int width, int height) {
        super(handler, ((int) x ) / 36 * 36, ((int) y ) / 36 * 36, width, height);
        bombGif = new Animation(500, Assets.bombGif);
//        startTime = TestTime.timeNow();
        startTime = TestTime.timeNow2();
        flame = new Flame(handler, gameState, x, y, -1, -1, this, 2, 2, 2, 2);
    }

    @Override
    public void tick() {
        bombGif.tick();
//        long elapsedTime = TestTime.timeNow() - startTime;
        long elapsedTime = TestTime.timeNow2() - startTime;
//        if (elapsedTime == BOMB_TIME || elapsedTime + 60 == BOMB_TIME)
        if (elapsedTime == BOMB_TIME)
            alive = false;

        if (elapsedTime == BOMB_TIME-FLAME_TIME) {
            flameAlive = true;
            flame.tick();
        }
    }


    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimation(), (int) x, (int) y, width, height, null);

        if(flameAlive) flame.render(g);
    }

    private BufferedImage getCurrentAnimation(){
        return bombGif.getCurrentFrame();
    }

    public boolean isAlive() {
        return alive;
    }

    public long getStartTime() {
        return startTime;
    }
}