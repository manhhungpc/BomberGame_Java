package main.entities.creatures;

import main.Game;
import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //private Game game;
    private final Animation aniDown, aniUp, aniLeft, aniRight;
//    private int bombedNumber = 0;
//    private boolean bombed = true;

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
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;
//        bombed = false;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

//        if(handler.getKeyManager().bombed) {
//            bombed = true;
//        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) x, (int) y, width, height, null);
        g.setColor(Color.red);
        //g.fillRect((int) x+bounds.x, (int) y+bounds.y, bounds.width, bounds.height);

//        if (bombed && bombedNumber == 0) {
//            System.out.println("Bombed " + x + " " + y);
//            g.drawImage(Assets.bomb, (int) x, (int) y, width, height, nu)
//            bombedNumber++;
//        }
    }

    private BufferedImage getCurrentAnimation(){
        if(xMove < 0) return aniLeft.getCurrentFrame();
        if(xMove > 0) return aniRight.getCurrentFrame();
        if(yMove < 0) return aniUp.getCurrentFrame();
        if(yMove > 0) return aniDown.getCurrentFrame();
        return Assets.player;
    }

}