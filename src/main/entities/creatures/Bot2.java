package main.entities.creatures;

import main.AI.EnemyAI;
import main.Game;
import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;
import main.states.GameState;
import main.tiles.Tile;
import main.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class Bot2 extends Balloon {
    int count1 = 0, count2 = 0, count3 = 0;

    public static final int MIN_SPEED = 1, MAX_SPEED = 3;
    private EnemyAI enemyAI;

    public Bot2(Handler handler, float x, float y) {
        super(handler, x, y);

        aniDown = new Animation(500, Assets.bot2_down);
        aniUp = new Animation(500, Assets.bot2_up);
        aniLeft = new Animation(500, Assets.bot2_left);
        aniRight = new Animation(500, Assets.bot2_right);
    }

    @Override
    public void tick() {
        super.tick();
        setRandomSpeed();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    private void setRandomSpeed() {
        speed = random.nextInt(MIN_SPEED, MAX_SPEED+1);
//        if (speed != 1 && speed !=2 && speed != 3) System.out.println(speed);
//        if (speed == 1) count1++;
//        if (speed == 2) count2++;
//        if (speed == 3) count3++;
//        System.out.println(count1 + " " + count2 + " " + count3);
    }

    @Override
    protected void setAutoMove() {
        if (x % 36 == 0 && y % 36 == 0) {
            int tx = (int) x/36;
            int ty = (int) y/36;

            boolean canUp = true, canDown = true, canLeft = true, canRight = true;

            if (collisionTitle(tx, (int) (y+36) / 36)) canDown = false;
            if (collisionTitle(tx, (int) (y-1)/36)) canUp = false;
            if (collisionTitle((int) (x+36)/36, ty)) canRight = false;
            if (collisionTitle((int) (x-1)/36, ty)) canLeft = false;

            // catching player
            List<Player> players = handler.getGame().getGameState().getPlayers();
            if (players.size() > 0) {
                EnemyAI enemyAI = new EnemyAI(handler);
                int playerX = (int) players.get(0).getLeftX() / Tile.TILE_WIDTH;
                int playerY = (int) players.get(0).getUpY() / Tile.TILE_HEIGHT;
                List<World.Position> path = enemyAI.path(playerX, playerY, tx, ty);
                if (path.size() > 1) {
                    World.Position catchingPosition = path.get(1);
                    int newTx = catchingPosition.x;
                    int newTy = catchingPosition.y;

                    if (newTy == ty && newTx > tx) {
                        xMove = speed;
                        yMove = 0;
                        return;
                    }

                    if (newTy == ty && newTx < tx) {
                        xMove = -speed;
                        yMove = 0;
                        return;
                    }

                    if (newTx == tx && newTy > ty) {
                        xMove = 0;
                        yMove = speed;
                        return;
                    }

                    if (newTx == tx && newTy < ty) {
                        xMove = 0;
                        yMove = -speed;
                        return;
                    }
                }
            }



            while(true) {
                int choose = random.nextInt(4);
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
}
