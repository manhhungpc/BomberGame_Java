package main.states;

import main.Game;
import main.gfx.Assets;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Game game){
        super(game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grass, 0, 0, null);
    }
}
