package main.entities.creatures;

import main.Game;
import main.Handler;
import main.entities.bomb.BombSet;
import main.entities.bomb.Flame;
import main.gfx.Animation;
import main.gfx.Assets;
import main.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Creature {

    public static final double INCREASE_PLAYER_SPEED = 1.0f;

    private final Animation aniDown, aniUp, aniLeft, aniRight;

    private boolean isAlive = true;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, 32, 48);

        bounds.x = 2;
        bounds.y = 20;
        bounds.width = 28;
        bounds.height = 28;

        aniDown = new Animation(500, Assets.player_down);
        aniUp = new Animation(500, Assets.player_up);
        aniLeft = new Animation(500, Assets.player_left);
        aniRight = new Animation(500, Assets.player_right);
    }

    @Override
    public void tick() {
        aniDown.tick();
        aniUp.tick();
        aniLeft.tick();
        aniRight.tick();
        getInput();
        move();
        checkAlive();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) x, (int) y, width, height, null);
        g.setColor(Color.red);
//        g.fillRect((int) x+bounds.x, (int) y+bounds.y, bounds.width, bounds.height);
//        g.fillRect((int) x, (int) y, bounds.width + bounds.x, bounds.height + bounds.y);
//        System.out.println(isAlive);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

    }

    private BufferedImage getCurrentAnimation(){
        if(xMove < 0) return aniLeft.getCurrentFrame();
        if(xMove > 0) return aniRight.getCurrentFrame();
        if(yMove < 0) return aniUp.getCurrentFrame();
        if(yMove > 0) return aniDown.getCurrentFrame();
        return Assets.player;
    }

    public float getLeftX() {
        return x + bounds.x;
    }

    public float getUpY() {
        return y + bounds.y;
    }

    public float getRightX() {
        return x + bounds.x + bounds.width;
    }

    public float getDownY() {
        return y + bounds.y + bounds.height;
    }

    private void checkAlive() {
        List<Balloon> balloons = handler.getGame().getGameState().getBalloons();
        for (int i = 0; i < balloons.size(); i++) {
            if (isCollision((float) balloons.get(i).getCurrentTopLeftX(), (float) balloons.get(i).getCurrentTopLeftY())) {
                isAlive = false;
                return;
            }
        }
    }

    private boolean isCollision(float balloonX, float balloonY) {
//        System.out.println(getUpY() + " " + balloonY + " " + getLeftX() + " " + balloonX);
        return  getUpY() +  bounds.height >= balloonY
                && getUpY() <= balloonY + Tile.TILE_HEIGHT
                && getLeftX() + bounds.width >= balloonX
                && getLeftX() <= balloonX + Tile.TILE_WIDTH;
    }

    @Override
    protected boolean collisionTitle(int x, int y) {
        return handler.getWorld().getTile(x,y).isSolidToPlayer();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}