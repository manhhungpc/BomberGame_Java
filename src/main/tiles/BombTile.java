package main.tiles;

import main.gfx.Assets;

public class BombTile extends Tile {

    public BombTile(char id) {
        super(Assets.grass, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
