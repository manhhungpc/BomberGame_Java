package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree, wall, brick, fake;

    public static void init(){
//        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
//
//        player = sheet.crop(0, 0, width, height);
//        dirt = sheet.crop(width, 0, width, height);
//        grass = sheet.crop(width * 2, 0, width, height);
//        stone = sheet.crop(width * 3, 0, width, height);
//        tree = sheet.crop(0, height, width, height);

        player = ImageLoader.loadImage("/image/bomber-down.png");
        grass = ImageLoader.loadImage("/image/grass.jpg");
        wall = ImageLoader.loadImage("/image/hardWall.png");
        brick = ImageLoader.loadImage("/image/softWall.png");
        fake = ImageLoader.loadImage("/image/bomb1.png");
    }

}