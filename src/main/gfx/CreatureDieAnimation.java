package main.gfx;

import main.Sound.SoundEffect;
import main.TimeManage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CreatureDieAnimation extends Animation {

    private float x;
    private float y;
    private final long startTime, time;
    private boolean isAlive = true;

    public static SoundEffect playerDie = new SoundEffect(SoundEffect.PLAYER_DEAD);

    public CreatureDieAnimation(int speed, BufferedImage[] frames, long time, float x, float y) {
        super(speed, frames);
        startTime = TimeManage.timeNow();
        this.time = time;
        this.x = x;
        this.y = y;
    }

    @Override
    public void tick() {
        if (isAlive) {
            super.tick();

            long elapseTime = TimeManage.timeNow() - startTime;
            playerDie.play();
            if (elapseTime >= time) {
                isAlive = false;
            }
        }
    }

    public void render(Graphics g) {
        if (isAlive) {
            g.drawImage(getCurrentFrame(), (int) x, (int) y, null);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
