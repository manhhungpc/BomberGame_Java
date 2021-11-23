package main.entities.creatures;

import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Balloon extends Creature {

    //private Game game;
    private Animation aniDown, aniUp, aniLeft, aniRight;

    private int choose = 0;
    private Random random = new Random(0);

    public Balloon(Handler handler, float x, float y) {
        super(handler, x, y, 36, 36);
        speed = 1.0f;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 32;
        bounds.height = 32;

        aniDown = new Animation(500, Assets.balloon_down);
        aniUp = new Animation(500, Assets.balloon_up);
        aniLeft = new Animation(500, Assets.balloon_left);
        aniRight = new Animation(500, Assets.balloon_right);

    }

    @Override
    public void tick() {
        aniDown.tick();
        aniUp.tick();
        aniLeft.tick();
        aniRight.tick();
        setAutoMove();
        move();
    }

    private void setAutoMove() {
        if (x % 36 == 0 && y % 36 == 0) {
            int tx = (int) x/36;
            int ty = (int) y/36;

            boolean canUp = true, canDown = true, canLeft = true, canRight = true;

            if (collisionTitle(tx, (int) (y+36) / 36)) canDown = false;
            if (collisionTitle(tx, (int) (y-1)/36)) canUp = false;
            if (collisionTitle((int) (x+36)/36, ty)) canRight = false;
            if (collisionTitle((int) (x-1)/36, ty)) canLeft = false;

            while(true) {
                choose = random.nextInt(4);
                if (choose == 0 && canDown) {
                    xMove = 0;
                    yMove = speed;
                    return;
                }
                if (choose == 1 && canUp) {
                    xMove = 0;
                    yMove = -speed;
                    return;
                }
                if (choose == 2 && canRight) {
                    xMove = speed;
                    yMove = 0;
                    return;
                }
                if (choose == 3 && canLeft) {
                    xMove = -speed;
                    yMove = 0;
                    return;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) x, (int) y, width, height, null);
        g.setColor(Color.red);
        //g.fillRect((int) x+bounds.x, (int) y+bounds.y, bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimation(){
        if(xMove < 0) return aniLeft.getCurrentFrame();
        if(xMove > 0) return aniRight.getCurrentFrame();
        if(yMove < 0) return aniUp.getCurrentFrame();
        return aniDown.getCurrentFrame();
    }

    public double getCurrentTopLeftX() {
        return x-2;
    }

    public double getCurrentTopLeftY() {
        return y;
    }

}
