package main.entities.bomb;

import main.Handler;
import main.entities.Entity;
import main.entities.creatures.bot.Balloon;
import main.entities.creatures.Player;
import main.states.GameState;
import main.tiles.Tile;
import main.worlds.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BombSet extends Entity {

    public static int maxBombNumber = 1;

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
                momentAfterExploding(bombList.get(i));
                bombList.remove(i);
            }
        }

        if (bombedRequest && bombList.size() < maxBombNumber && !bombDone) {
            Bomb newBomb = new Bomb(handler, gameState, x, y, 36, 36);
            if (!hasDuplicate(newBomb))
                bombList.add(newBomb);
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
            if (temp == '*' || temp == 'e' || temp == 'g' || temp == 'n') {
                world.setTile(x, y, ' ');
            } else if (temp == 'f') {
                world.setTile(x, y, 'g');
            } else if (temp == 'b') {
                world.setTile(x, y, 'n');
            } else if (temp == 's') {
                world.setTile(x, y, 'e');
            }

        }
    }

    private void momentAfterExploding(Bomb bomb) {
        setNewWorld(bomb.getChangePositions());
//        handler.getGame().getGameState().getWorld().setTile( (int) bomb.getFlame().getX()/36, (int) bomb.getFlame().getY()/36, ' ');
        deleteEnemyAtFlame(bomb.getFlame());
        explodeBombAtFlame(bomb.getFlame());

    }

    private boolean enemyIsAtFlame(Flame flame, double enemyX, double enemyY) {
        double flameX = flame.getX();
        double flameY = flame.getY();
        if (enemyY > flameY - 0.9 * Tile.TILE_HEIGHT
                && enemyY < flameY + 0.9 * Tile.TILE_HEIGHT
                && enemyX > flameX - (0.9 + flame.getLeft()) * Tile.TILE_WIDTH
                && enemyX < flameX + (0.9 + flame.getRight()) * Tile.TILE_WIDTH)
            return true;
        return enemyX > flameX - 0.9 * Tile.TILE_WIDTH
                && enemyX < flameX + 0.9 * Tile.TILE_WIDTH
                && enemyY > flameY - (0.9 + flame.getUp()) * Tile.TILE_HEIGHT
                && enemyY < flameY + (0.9 + flame.getDown()) * Tile.TILE_HEIGHT;
    }

    private void deleteEnemyAtFlame(Flame flame) {
        List<Balloon> balloonList = handler.getGame().getGameState().getBalloons();
        for (int i = balloonList.size() - 1; i >= 0; i--) {
            double balloonX = balloonList.get(i).getCurrentTopLeftX();
            double balloonY = balloonList.get(i).getCurrentTopLeftY();
            if (enemyIsAtFlame(flame, balloonX, balloonY)) {
                balloonList.remove(i);
//                System.out.println("boom" + TimeManage.timeNow());
            }
        }
    }

    private void explodeBombAtFlame(Flame flame) {
        for (int i = 0; i < bombList.size(); i++) {
            Bomb bomb = bombList.get(i);
            if (bomb.getFlame() == flame) continue;
            double bombX = bomb.getFlame().getX();
            double bombY = bomb.getFlame().getY();
            if (bombIsAtFlame(flame, bombX, bombY)) {
                bomb.setFlameRightNow(); // Set Flame begin

                // set flame realtime (2 flame can del 2 boxes)
                bomb.getFlame().setFlame4Size();
                bomb.getFlame().setAnimation();
            }
        }
    }

    private boolean bombIsAtFlame(Flame flame, double bombX, double bombY) {
        double flameX = flame.getX();
        double flameY = flame.getY();
        if (bombY == flameY
                && bombX >= flameX - flame.getLeft() * Tile.TILE_WIDTH
                && bombX <= flameX + flame.getRight() * Tile.TILE_WIDTH)
            return true;
        return bombX == flameX
                && bombY >= flameY - flame.getUp() * Tile.TILE_HEIGHT
                && bombY <= flameY + flame.getDown() * Tile.TILE_HEIGHT;
    }

    private boolean hasDuplicate(Bomb bomb) {
        for (int i = 0; i < bombList.size(); i++) {
            Flame bombFlameI = bombList.get(i).getFlame();
            if (bombFlameI.getX() == bomb.getFlame().getX()
                    && bombFlameI.getY() == bomb.getFlame().getY())
                return true;
        }
        return false;
    }
}
