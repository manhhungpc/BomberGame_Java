package main.states;

import main.AI.EnemyAI;
import main.Handler;
import main.entities.creatures.bot.Balloon;
import main.entities.bomb.BombSet;
//import main.entities.creatures.FindPath;
import main.entities.creatures.bot.Bot2;
import main.entities.creatures.Player;
import main.entities.creatures.bot.Bot3;
import main.gfx.Animation;
import main.gfx.Assets;
import main.gfx.CreatureDieAnimation;
import main.tiles.Tile;
import main.worlds.World;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static main.worlds.World.*;


public class GameState extends State {

    private final List<Player> players;
    private final World world;

    private final List<Balloon> balloons;
    private static EnemyAI enemyAI;
    private final List<Bot2> bot2s;
    private final List<Bot3> bot3s;

    private BombSet bombSet;
    private final List<CreatureDieAnimation> creatureDieAnimations = new ArrayList<>();

    private final List<Animation> portalAnimations = new ArrayList<>();
    private boolean openedPortal = false;
    private Animation portalCloseAnimation;
    private Animation portalOpenAnimation;

    public GameState(Handler handler){
        super(handler);
        world = new World(".\\src\\resource\\map\\level2.txt");
        handler.setWorld(world);

        players = new ArrayList<>();
        setPlayers();
        bombSet = new BombSet(handler, this,0, 0, 36, 36, players.get(0));

        balloons = new ArrayList<>();
        setBalloons();

        bot2s = new ArrayList<>();
        setBot2();

        bot3s = new ArrayList<>();
        setBot3();

        portalCloseAnimation = new Animation(100, Assets.portalClose);
        portalOpenAnimation = new Animation(100, Assets.portalOpen);
    }

    @Override
    public void tick() {
        world.tick();

        tickPlayers();
        if (bombSet != null) bombSet.tick();

        tickBalloons();
        if (enemyAI == null) enemyAI = new EnemyAI(handler);
        tickBot2s();
        tickBot3s();

        tickCreatureDie();

        portalCloseAnimation.tick();
        portalOpenAnimation.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);

        renderPortal(g);

        renderBalloons(g);
        renderBot2s(g);
        renderBot3s(g);

        if (bombSet != null) bombSet.render(g);
        renderPlayers(g);

        renderCreatureDie(g);

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

    private void setPlayers() {
        for (int i = 0; i < playerPosition.size(); i++) {
            int x = playerPosition.get(i).x;
            int y = playerPosition.get(i).y;
            players.add(new Player(handler, x*36-2, y*36 - 20));
        }
    }

    private void setBalloons() {
        for (int i = 0; i < balloonPosition.size(); i++) {
            int x = balloonPosition.get(i).x;
            int y = balloonPosition.get(i).y;
            balloons.add(new Balloon(handler, x*36, y*36));
        }
    }

    private void setBot2() {
        for (int i = 0; i < bot2Position.size(); i++) {
            int x = bot2Position.get(i).x;
            int y = bot2Position.get(i).y;
            bot2s.add(new Bot2(handler, x*36, y*36));
        }
    }

    private void setBot3() {
        for (int i = 0; i < bot3Position.size(); i++) {
            int x = bot3Position.get(i).x;
            int y = bot3Position.get(i).y;
            bot3s.add(new Bot3(handler, x*36, y*36));
        }
    }

    private void setPortalAnimations() {
//        for (int i = 0; i < portalPosition.size(); i++) {
//            int x = portalPosition.get(i).x;
//            int y = portalPosition.get(i).y;
//            portalAnimations.add(new Animation(500, Assets.portalClose));
//        }
    }

    private void renderPlayers(Graphics g) {
        for (int i = 0; i < players.size(); i++) {
            Player playerI = players.get(i);
            playerI.render(g);
        }
    }

    private void renderBalloons(Graphics g) {
        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            balloon.render(g);
        }
    }

    private void renderBot2s(Graphics g) {
        for (int i = 0; i < bot2s.size(); i++) {
            bot2s.get(i).render(g);
        }
    }

    private void renderBot3s(Graphics g) {
        for (int i = 0; i < bot3s.size(); i++) {
            bot3s.get(i).render(g);
        }
    }

    private void renderPortal(Graphics g) {
        if (!openedPortal && balloons.size() == 0 && bot2s.size() == 0 && bot3s.size() == 0)
            openedPortal = true;
        for (int i = 0; i < portalPosition.size(); i++) {
            int x = portalPosition.get(i).x;
            int y = portalPosition.get(i).y;
            if (world.getCharTile(x, y) == 'x') return;
            if (!openedPortal)
                g.drawImage(portalCloseAnimation.getCurrentFrame(), x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, null);
            else
                g.drawImage(portalOpenAnimation.getCurrentFrame(), x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, null);
        }
    }

    private void tickPlayers() {
        for (int i = players.size() - 1; i >= 0; i--) {
            Player playerI = players.get(i);
            if (!playerI.isAlive()) {

                // add player die animation
                CreatureDieAnimation temp = new CreatureDieAnimation(100, Assets.playerDie, 30, playerI.getX(), playerI.getY());
                creatureDieAnimations.add(temp);

                players.remove(i);
                continue;
            }
            playerI.tick();
        }
        if (players.size() == 0) bombSet = null;
    }

    private void tickBalloons() {
        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            balloon.tick();
        }
    }

    private void tickBot2s() {
        for (int i = 0; i < bot2s.size(); i++) {
            bot2s.get(i).tick();
        }
    }

    private void tickBot3s() {
        for (int i = 0; i < bot3s.size(); i++) {
            bot3s.get(i).tick();
        }
    }

    private void tickCreatureDie() {
        for (int i = creatureDieAnimations.size() - 1; i >= 0; i--) {
            CreatureDieAnimation creatureDieI = creatureDieAnimations.get(i);
            if (!creatureDieI.isAlive()) {
                creatureDieAnimations.remove(i);
                continue;
            }
            creatureDieI.tick();
        }
    }

    private void tickPortalAnimations() {
//        // if win
//        if (!changePortal && balloons.size() == 0 && bot2s.size() == 0 && bot3s.size() == 0) {
//            changePortal = true;
//            for (int i = 0; i < po)
//        }
    }

    private void renderCreatureDie(Graphics g) {
        for (int i = 0; i < creatureDieAnimations.size(); i++) {
            creatureDieAnimations.get(i).render(g);
        }
    }

    public List<CreatureDieAnimation> getCreatureDieAnimations() {
        return creatureDieAnimations;
    }

    public List<Bot2> getBot2s() {
        return bot2s;
    }

    public List<Bot3> getBot3s() {
        return bot3s;
    }

    public static EnemyAI getEnemyAI() {
        return enemyAI;
    }
}
