package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree, wall, brick, fake;
    public static BufferedImage[] player_down, player_up, player_left, player_right;

    public static void init(){
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];

//        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
//
//        player = sheet.crop(0, 0, width, height);
//        dirt = sheet.crop(width, 0, width, height);
//        grass = sheet.crop(width * 2, 0, width, height);
//        stone = sheet.crop(width * 3, 0, width, height);
//        tree = sheet.crop(0, height, width, height);

        player = ImageLoader.loadImage("/image/bomber-down.png");

        player_down[0] = ImageLoader.loadImage("/image/bomber-down-left.png");
        player_down[1] = ImageLoader.loadImage("/image/bomber-down-right.png");

        player_up[0] = ImageLoader.loadImage("/image/bomber-up-left.png");
        player_up[1] = ImageLoader.loadImage("/image/bomber-up-right.png");

        player_left[0] = ImageLoader.loadImage("/image/bomber-left-up.png");
        player_left[1] = ImageLoader.loadImage("/image/bomber-left-walk.png");

        player_right[0] = ImageLoader.loadImage("/image/bomber-right-up.png");
        player_right[1] = ImageLoader.loadImage("/image/bomber-right-walk.png");

        grass = ImageLoader.loadImage("/image/grass.jpg");
        wall = ImageLoader.loadImage("/image/hardWall.png");
        brick = ImageLoader.loadImage("/image/softWall.png");
        fake = ImageLoader.loadImage("/image/bomb1.png");
    }

}