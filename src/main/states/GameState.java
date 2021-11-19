package main.states;

import main.Game;
import main.entities.creatures.Player;
import main.gfx.Assets;
import main.tiles.Tile;
import main.worlds.World;

import java.awt.Graphics;


public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game, 100, 100);
        world = new World("map/level1.txt");
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.grass, 0, 0, null);
        world.render(g);
//        Tile.tiles['*'].render(g, 0, 0);
//        Tile.tiles['#'].render(g, 32, 0);
//        Tile.tiles['g'].render(g, 64, 0);
        player.render(g);

    }

}
