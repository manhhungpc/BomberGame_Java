package main.entities.creatures;

import main.Game;
import main.Handler;
import main.entities.bomb.BombSet;
import main.entities.bomb.Flame;
import main.gfx.Animation;
import main.gfx.Assets;
import main.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Creature {

    public static final double INCREASE_PLAYER_SPEED = 1.0f;

    private final Animation aniDown, aniUp, aniLeft, aniRight;

    private boolean isAlive = true;

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

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) x, (int) y, width, height, null);
        g.setColor(Color.red);
//        g.fillRect((int) x+bounds.x, (int) y+bounds.y, bounds.width, bounds.height);
//        g.fillRect((int) x, (int) y, bounds.width + bounds.x, bounds.height + bounds.y);
        checkAlive();
//        System.out.println(isAlive);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

    }

    private BufferedImage getCurrentAnimation(){
        if(xMove < 0) return aniLeft.getCurrentFrame();
        if(xMove > 0) return aniRight.getCurrentFrame();
        if(yMove < 0) return aniUp.getCurrentFrame();
        if(yMove > 0) return aniDown.getCurrentFrame();
        return Assets.player;
    }

    public float getLeftX() {
        return x + bounds.x;
    }

    public float getUpY() {
        return y + bounds.y;
    }

    public float getRightX() {
        return x + bounds.x + bounds.width;
    }

    public float getDownY() {
        return y + bounds.y + bounds.height;
    }

    private void checkAlive() {
        List<Balloon> balloons = handler.getGame().getGameState().getBalloons();
        for (int i = 0; i < balloons.size(); i++) {
            if (isCollision((float) balloons.get(i).getCurrentTopLeftX(), (float) balloons.get(i).getCurrentTopLeftY())) {
//                isAlive = false;
                return;
            }
        }
    }

    private boolean isCollision(float balloonX, float balloonY) {
        return  (getUpY() - bounds.height < balloonY || balloonY < getUpY() + bounds.height)
                && (getLeftX() - bounds.width < balloonX || balloonX < getLeftX() + bounds.width);
    }

    @Override
    protected boolean collisionTitle(int x, int y) {
        return handler.getWorld().getTile(x,y).isSolidToPlayer();
    }

    public void move() {
        moveX();
        moveY();
    }

    public void moveX(){
        if(xMove > 0){
            //Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            char currentTileChar = handler.getWorld().getCharTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT);
            if (currentTileChar == 'g') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                if (Flame.flameSize < Flame.MAX_FLAME_SIZE) Flame.flameSize++;
            }
            if (currentTileChar == 'e') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                handler.getGame().getGameState().getPlayers().get(0).speed += Player.INCREASE_PLAYER_SPEED;
            }
            if (currentTileChar == 'n') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                BombSet.maxBombNumber++;
            }

            if(!collisionTitle(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTitle(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else{
//                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }

        if(xMove < 0){
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            char currentTileChar = handler.getWorld().getCharTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT);
            if (currentTileChar == 'g') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                if (Flame.flameSize < Flame.MAX_FLAME_SIZE) Flame.flameSize++;
            }
            if (currentTileChar == 'e') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                handler.getGame().getGameState().getPlayers().get(0).speed += Player.INCREASE_PLAYER_SPEED;
            }
            if (currentTileChar == 'n') {
                handler.getWorld().setTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT, ' ');
                BombSet.maxBombNumber++;
            }

            if(!collisionTitle(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTitle(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }else{
//                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
        //x += xMove;
    }

    public void moveY(){
        if(yMove < 0){
            //Move up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            char currentTileChar = handler.getWorld().getCharTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty);
            if (currentTileChar == 'g') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                if (Flame.flameSize < Flame.MAX_FLAME_SIZE) Flame.flameSize++;
            }
            if (currentTileChar == 'e') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                handler.getGame().getGameState().getPlayers().get(0).speed += Player.INCREASE_PLAYER_SPEED;
            }
            if (currentTileChar == 'n') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                BombSet.maxBombNumber++;
            }

            if(!collisionTitle((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionTitle((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else{
//                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }

        if(yMove > 0){
            //Move down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            char currentTileChar = handler.getWorld().getCharTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty);
            if (currentTileChar == 'g') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                if (Flame.flameSize < Flame.MAX_FLAME_SIZE) Flame.flameSize++;
            }
            if (currentTileChar == 'e') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                handler.getGame().getGameState().getPlayers().get(0).speed += Player.INCREASE_PLAYER_SPEED;
            }
            if (currentTileChar == 'n') {
                handler.getWorld().setTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty, ' ');
                BombSet.maxBombNumber++;
            }

            if(!collisionTitle((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionTitle((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }else{
//                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
        //y += yMove;
    }
}