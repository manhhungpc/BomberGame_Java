package main.states;

import main.Handler;
import main.entities.creatures.Balloon;
import main.entities.creatures.BombSet;
import main.entities.creatures.Player;
import main.worlds.World;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static main.worlds.World.balloonPosition;
import static main.worlds.World.playerPosition;


public class GameState extends State {

    private final List<Player> players;
    private final World world;
    private final List<Balloon> balloons;
    private final BombSet bombSet;

    public GameState(Handler handler){
        super(handler);
        world = new World(".\\src\\resource\\map\\level2.txt");
        handler.setWorld(world);

        players = new ArrayList<>();
        for (int i = 0; i < playerPosition.size(); i++) {
            int x = playerPosition.get(i).x;
            int y = playerPosition.get(i).y;
            players.add(new Player(handler, x*36-2, y*36 - 20));
        }

//        player = new Player(handler, 37, 17);
//        balloom = new Balloom(handler, 36, 36);

        balloons = new ArrayList<>();
        for (int i = 0; i < balloonPosition.size(); i++) {
            int x = balloonPosition.get(i).x;
            int y = balloonPosition.get(i).y;
            balloons.add(new Balloon(handler, x*36, y*36));
        }

        bombSet = new BombSet(handler, this,0, 0, 36, 36, players.get(0));
    }

    @Override
    public void tick() {
        world.tick();
        for (int i = 0; i < players.size(); i++) {
            Player playerI = players.get(i);
            playerI.tick();
        }
//        player.tick();
//        balloom.tick();

        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            balloon.tick();
        }
        bombSet.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.grass, 0, 0, null);
        world.render(g);
//        Tile.tiles['*'].render(g, 0, 0);
//        Tile.tiles['#'].render(g, 32, 0);
//        Tile.tiles['g'].render(g, 64, 0);
        for (int i = 0; i < players.size(); i++) {
            Player playerI = players.get(i);
            playerI.render(g);
        }
//        player.render(g);
//        balloom.render(g);
        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            balloon.render(g);
        }
        bombSet.render(g);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public World getWorld() {
        return world;
    }

    public List<Balloon> getBalloons() {
        return balloons;
    }

    public BombSet getBombSet() {
        return bombSet;
    }
}
