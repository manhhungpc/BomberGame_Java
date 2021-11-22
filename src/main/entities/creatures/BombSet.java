package main.entities.creatures;

import main.Game;
import main.Handler;
import main.entities.Entity;
import main.states.GameState;
import main.worlds.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BombSet extends Entity {

    public static int maxBombNumber = 10;

    private final Player player;
    private final int bombNumber = 0;
    private List<Bomb> bombList;
    private boolean bombDone;
    private GameState gameState;
    private World world;

    public BombSet(Handler handler, GameState gameState, float x, float y, int width, int height, Player player) {
        super(handler, x, y, width, height);
        this.player = player;
        super.x = player.getX();
        super.y = player.getY();
        bombList = new ArrayList<>();
        this.gameState = gameState;
        this.world = gameState.getWorld();
    }

    @Override
    public void tick() {
        boolean bombedRequest = handler.getKeyManager().bombed;

        x = player.getX()+2+14;
        y = player.getY()+20+14;

        for (int i = bombList.size()-1; i >= 0; i--) {
            if (!bombList.get(i).isAlive()) {
                setNewWorld(bombList.get(i).getChangePositions());
                bombList.remove(i);
            }
        }

        if (bombedRequest && bombList.size() < maxBombNumber && !bombDone) {
            bombList.add(new Bomb(handler, gameState, x, y, 36, 36));
//            System.out.println(x + " " + y);
            bombDone = true;
        }
        if (!bombedRequest) {
            bombDone = false;
        }

        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).render(g);
        }

    }

    private void setNewWorld(List<World.Position> changePositions) {
        for (int i = 0; i < changePositions.size(); i++) {
            int x = changePositions.get(i).x;
            int y = changePositions.get(i).y;
            char temp = world.getCharTile(x, y);
            if (temp == '*')
                world.setTile(x, y, ' ');
        }
    }
}
