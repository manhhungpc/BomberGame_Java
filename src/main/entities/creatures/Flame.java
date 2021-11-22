package main.entities.creatures;

import main.Game;
import main.Handler;
import main.TestTime;
import main.entities.Entity;
import main.gfx.Animation;
import main.gfx.Assets;
import main.states.GameState;
import main.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flame extends Entity {

    public static int FLAME_SIZE = 2;

    private final Bomb bomb;
    private Animation flameGifLeft, flameGifRight, flameGifUp, flameGifDown;
    private int left, right, up, down;
    private GameState gameState;

    public Flame(Handler handler, GameState gameState, float x, float y, int width, int height, Bomb bomb, int left, int right, int up, int down) {
        super(handler, x, y, width, height);
        this.bomb = bomb;
        this.gameState = gameState;

        setFlame4Size();

        if (this.left != 0) flameGifLeft = new Animation(500, Assets.flameLeft(this.left));
        if (this.right != 0) flameGifRight = new Animation(500, Assets.flameRight(this.right));
        if (this.up != 0) flameGifUp = new Animation(500, Assets.flameUp(this.up));
        if (this.down != 0) flameGifDown = new Animation(500, Assets.flameDown(this.down));

//        this.left = left;
//        this.right = right;
//        this.down = down;
//        this.up = up;

        super.x = bomb.getX();
        super.y = bomb.getY();
    }

    private void setFlame4Size() {
        left = 0;
        while (left != FLAME_SIZE) {
            int xx = (int) x / 36 - left - 1;
            int yy =  (int) y / 36;
            if(gameState.getWorld().getTile(xx, yy).isSolid()) break;
            left++;
        }
//        int xx = (int) x / 36 - left - 1;
//        int yy =  (int) y / 36;
//        System.out.println(xx + " " + yy + " " + left
//                + " " + gameState.getWorld().getTile(xx, yy).isSolid());

        right = 0;
        while (right != FLAME_SIZE) {
            int xx = (int) x / 36 + right + 1;
            int yy =  (int) y / 36;
            if(gameState.getWorld().getTile(xx, yy).isSolid()) break;
            right++;
        }

        up = 0;
        while (up != FLAME_SIZE) {
            int xx = (int) x / 36;
            int yy =  (int) y / 36 - up - 1;
            if(gameState.getWorld().getTile(xx, yy).isSolid()) break;
            up++;
        }

        down = 0;
        while (down != FLAME_SIZE) {
            int xx = (int) x / 36;
            int yy =  (int) y / 36 + down + 1;
            if(gameState.getWorld().getTile(xx, yy).isSolid()) break;
            down++;
        }

//        left =;
//        right = 2;
//        up = 2;
//        down = 2;
    }

    @Override
    public void tick() {
        if (left != 0) flameGifLeft.tick();
        if (right != 0) flameGifRight.tick();
        if (up != 0) flameGifUp.tick();
        if (down != 0) flameGifDown.tick();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.explosion0, (int) x, (int) y, 36, 36, null);
        if (left != 0)
            g.drawImage(getCurrentAnimationLeft(), (int) x-left*36, (int) y, left*36, 36, null);
        if (right != 0)
            g.drawImage(getCurrentAnimationRight(), (int) x+36, (int) y, right*36, 36, null);
        if (up != 0)
            g.drawImage(getCurrentAnimationUp(), (int) x, (int) y-up*36, 36, up*36, null);
        if (down != 0)
            g.drawImage(getCurrentAnimationDown(), (int) x, (int) y+36, 36, down*36, null);
    }

    private BufferedImage getCurrentAnimationLeft(){
        return flameGifLeft.getCurrentFrame();
    }

    private BufferedImage getCurrentAnimationRight(){
        return flameGifRight.getCurrentFrame();
    }

    private BufferedImage getCurrentAnimationUp(){
        return flameGifUp.getCurrentFrame();
    }

    private BufferedImage getCurrentAnimationDown(){
        return flameGifDown.getCurrentFrame();
    }

}
