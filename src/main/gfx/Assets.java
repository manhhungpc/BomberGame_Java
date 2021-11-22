package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree, wall, brick, fake, player1;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_die,
            player1_down, player1_up, player1_left, player1_right,
            player2_down, player2_up, player2_left, player2_right,
            player3_down, player3_up, player3_left, player3_right,
            player1_die, player2_die, player3_die;

    public static void init(){
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];

        player1_down = new BufferedImage[2];
        player1_up = new BufferedImage[2];
        player1_right = new BufferedImage[2];
        player1_left = new BufferedImage[2];

        player2_down = new BufferedImage[2];
        player2_up = new BufferedImage[2];
        player2_right = new BufferedImage[2];
        player2_left = new BufferedImage[2];

        player3_down = new BufferedImage[2];
        player3_up = new BufferedImage[2];
        player3_right = new BufferedImage[2];
        player3_left = new BufferedImage[2];

        player_die = new BufferedImage[3];
        player1_die = new BufferedImage[3];
        player2_die = new BufferedImage[3];
        player3_die = new BufferedImage[3];

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

        SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/image/uncut/bomber4.png"));

        player1_up[0] = player.crop(0, 0, 30, 48);
        player1_down[0] = player.crop(0, 48, 30, 48);
        player1_left[0] = player.crop(0, 96, 30, 48);
        player1_right[0] = player.crop(0, 144, 30, 48);

        player1_up[1] = player.crop(30, 0, 30, 48);
        player1_down[1] = player.crop(30, 48, 30, 48);
        player1_left[1] = player.crop(30, 96, 30, 48);
        player1_right[1] = player.crop(30, 144, 30, 48);

        player1_up[2] = player.crop(90, 0, 30, 48);
        player1_down[2] = player.crop(90, 48, 30, 48);
        player1_left[2] = player.crop(90, 96, 30, 48);
        player1_right[2] = player.crop(90, 144, 30, 48);

        player2_up[0] = player.crop(0, 0, 30, 48);
        player2_down[0] = player.crop(0, 48, 30, 48);
        player2_left[0] = player.crop(0, 96, 30, 48);
        player2_right[0] = player.crop(0, 144, 30, 48);

        player2_up[1] = player.crop(30, 0, 30, 48);
        player2_down[1] = player.crop(30, 48, 30, 48);
        player2_left[1] = player.crop(30, 96, 30, 48);
        player2_right[1] = player.crop(30, 144, 30, 48);

        player2_up[2] = player.crop(90, 0, 30, 48);
        player2_down[2] = player.crop(90, 48, 30, 48);
        player2_left[2] = player.crop(90, 96, 30, 48);
        player2_right[2] = player.crop(90, 144, 30, 48);

        player3_up[0] = player.crop(0, 0, 30, 48);
        player3_down[0] = player.crop(0, 48, 30, 48);
        player3_left[0] = player.crop(0, 96, 30, 48);
        player3_right[0] = player.crop(0, 144, 30, 48);

        player3_up[1] = player.crop(30, 0, 30, 48);
        player3_down[1] = player.crop(30, 48, 30, 48);
        player3_left[1] = player.crop(30, 96, 30, 48);
        player3_right[1] = player.crop(30, 144, 30, 48);

        player3_up[2] = player.crop(90, 0, 30, 48);
        player3_down[2] = player.crop(90, 48, 30, 48);
        player3_left[2] = player.crop(90, 96, 30, 48);
        player3_right[2] = player.crop(90, 144, 30, 48);

        player1_die[0] = player.crop(0, 192, 30, 48);
        player1_die[1] = player.crop(30, 192, 30, 48);
        player1_die[2] = player.crop(60, 192, 30, 48);
        player1_die[3] = player.crop(90, 192, 30, 48);

        player2_die[0] = player.crop(0, 192, 30, 48);
        player2_die[1] = player.crop(30, 192, 30, 48);
        player2_die[2] = player.crop(60, 192, 30, 48);
        player2_die[3] = player.crop(90, 192, 30, 48);

        player3_die[0] = player.crop(0, 192, 30, 48);
        player3_die[1] = player.crop(30, 192, 30, 48);
        player3_die[2] = player.crop(60, 192, 30, 48);
        player3_die[3] = player.crop(90, 192, 30, 48);

        player_die[0] = player.crop(0, 192, 30, 48);
        player_die[1] = player.crop(30, 192, 30, 48);
        player_die[2] = player.crop(60, 192, 30, 48);
        player_die[3] = player.crop(90, 192, 30, 48);

        grass = ImageLoader.loadImage("/image/grass.jpg");
        wall = ImageLoader.loadImage("/image/hardWall.png");
        brick = ImageLoader.loadImage("/image/softWall.png");
        fake = ImageLoader.loadImage("/image/bomb1.png");
    }
}