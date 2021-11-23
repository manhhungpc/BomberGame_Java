package main.entities.creatures;

import main.Handler;
import main.TestTime;
import main.gfx.Animation;
import main.gfx.Assets;
import main.states.GameState;
import main.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Bomb extends Creature {

    private static final int BOMB_TIME = 20;
    private static final int FLAME_TIME = 2;

    private final Animation bombGif;
    private long startTime;
    private boolean alive = true;
    private final Flame flame;
    private boolean flameAlive = false;

    public Bomb(Handler handler, GameState gameState, float x, float y, int width, int height) {
        super(handler, ((int) x ) / 36 * 36, ((int) y ) / 36 * 36, width, height);
        bombGif = new Animation(500, Assets.bombGif);
        startTime = TestTime.timeNow2();
        flame = new Flame(handler, gameState, x, y, -1, -1, this);
//        handler.getGame().getGameState().getWorld().setTile((int) flame.getX()/36, (int) flame.getY()/36, 'B');
    }

    @Override
    public void tick() {
        bombGif.tick();
        long elapsedTime = TestTime.timeNow2() - startTime;
        if (elapsedTime == BOMB_TIME)
            alive = false;

        if (elapsedTime == BOMB_TIME - FLAME_TIME) {
            flameAlive = true;
            flame.tick();
        }
    }

    public void setFlameRightNow() {
        startTime = TestTime.timeNow2() - (BOMB_TIME-FLAME_TIME);
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

    public List<World.Position> getChangePositions() {
        return flame.getChangePositions();
    }

    public Flame getFlame() {
        return flame;
    }
}
