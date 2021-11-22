package main.entities.creatures;

import main.Handler;
import main.TestTime;
import main.entities.Entity;
import main.gfx.Animation;
import main.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flame extends Entity {

    private final Bomb bomb;
    private final Animation flameGifLeft, flameGifRight, flameGifUp, flameGifDown;
    private final long timeBeginLoading;
    private boolean alive = true;
    private int left, right, up, down;

    public Flame(Handler handler, float x, float y, int width, int height, Bomb bomb, int left, int right, int up, int down) {
        super(handler, x, y, width, height);
        this.bomb = bomb;

        flameGifLeft = new Animation(500, Assets.flameLeft(left));
        flameGifRight = new Animation(500, Assets.flameRight(right));
        flameGifUp = new Animation(500, Assets.flameUp(up));
        flameGifDown = new Animation(500, Assets.flameDown(down));

        this.left = left;
        this.right = right;
        this.down = down;
        this.up = up;
        timeBeginLoading = bomb.getStartTime() + 5;

        super.x = bomb.getX();
        super.y = bomb.getY();
    }

    @Override
    public void tick() {
        flameGifLeft.tick();
        flameGifRight.tick();
        flameGifUp.tick();
        flameGifDown.tick();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.explosion0, (int) x, (int) y, 36, 36, null);
        g.drawImage(getCurrentAnimationLeft(), (int) x-72, (int) y, 72, 36, null);
        g.drawImage(getCurrentAnimationRight(), (int) x+36, (int) y, 72, 36, null);
        g.drawImage(getCurrentAnimationUp(), (int) x, (int) y-72, 36, 72, null);
        g.drawImage(getCurrentAnimationDown(), (int) x, (int) y+36, 36, 72, null);
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
