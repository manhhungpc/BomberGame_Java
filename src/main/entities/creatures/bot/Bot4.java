package main.entities.creatures.bot;

import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;

public class Bot4 extends Balloon {

    public Bot4(Handler handler, float x, float y) {
        super(handler, x, y);

        aniLeft = new Animation(500, Assets.bot4_left);
        aniRight = new Animation(500, Assets.bot4_right);
        aniUp = new Animation(500, Assets.bot4_up);
        aniDown = new Animation(500, Assets.bot4_down);
    }
}