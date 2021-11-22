package main.gfx;

import main.entities.creatures.Flame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

//    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree, wall, brick, fake;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] balloon_down, balloon_up, balloon_left, balloon_right;
    public static BufferedImage bomb, explosionUncut, explosionX1,explosionX2, explosionX3
            , explosionY1, explosionY2, explosionY3, explosion0;
    public static BufferedImage[] bombGif, flameLeft, explosionRight, explosionUp, explosionDown;

    public static void init(){


//        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
//
//        player = sheet.crop(0, 0, width, height);
//        dirt = sheet.crop(width, 0, width, height);
//        grass = sheet.crop(width * 2, 0, width, height);
//        stone = sheet.crop(width * 3, 0, width, height);
//        tree = sheet.crop(0, height, width, height);

        player = ImageLoader.loadImage("/image/bomber-down.png");
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];

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
        bomb = ImageLoader.loadImage("/image/bomb1.png");


//        balloon = ImageLoader.loadImage("/image/enemy/balloon_down.png");
        balloon_down = new BufferedImage[1];
        balloon_up = new BufferedImage[1];
        balloon_right = new BufferedImage[1];
        balloon_left = new BufferedImage[1];

        balloon_down[0] = ImageLoader.loadImage("/image/enemy/balloon_down.png");
        balloon_up[0] = ImageLoader.loadImage("/image/enemy/balloon_up.png");
        balloon_left[0] = ImageLoader.loadImage("/image/enemy/balloon_left.png");
        balloon_right[0] = ImageLoader.loadImage("/image/enemy/balloon_right.png");

        bombGif = new BufferedImage[2];
        bombGif[0] = bomb;
        bombGif[1] = ImageLoader.loadImage("/image/bomb2.png");

        // explosion cutting
        explosionUncut = ImageLoader.loadImage("/image/uncut/explosion.png");
        SpriteSheet spriteSheetExplosion = new SpriteSheet(explosionUncut);
        explosionX1 = spriteSheetExplosion.crop(5*32, 32, 32, 32);
        explosionX2 = spriteSheetExplosion.crop(5*32, 3*32, 32, 32);
        explosionX3 = spriteSheetExplosion.crop(5*32, 4*32, 32, 32);

        explosionY1 = spriteSheetExplosion.crop(5*32, 2*32, 32, 32);
        explosionY2 = spriteSheetExplosion.crop(5*32, 5*32, 32, 32);
        explosionY3 = spriteSheetExplosion.crop(5*32, 6*32, 32, 32);

        explosion0 = spriteSheetExplosion.crop(5*32, 0, 32, 32);

//        File outputfile = new File("D:\\mid_mid.png");
//        try {
//            ImageIO.write(explosion0, "png", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    public static BufferedImage[] flameUp(int length) {
        BufferedImage[] ans = new BufferedImage[1];
        String s = "up" + (Flame.FLAME_SIZE);
        ans[0] = ImageLoader.loadImage("/image/explosion/bombbang_" + s + ".png");
        ans[0] = new SpriteSheet(ans[0]).crop(0, 0, ans[0].getWidth()-1, ans[0].getHeight()-1);

        double tileSize = (double) ans[0].getHeight() / Flame.FLAME_SIZE;
        System.out.println(tileSize + " " + ans[0].getWidth() + " " + (int) (length * tileSize));
        ans[0] = new SpriteSheet(ans[0]).crop( 0,
                (int) ((Flame.FLAME_SIZE - length) * tileSize),
                ans[0].getWidth(),
                (int) (length * tileSize));

        return ans;
    }

    public static BufferedImage[] flameRight(int length) {
        BufferedImage[] ans = new BufferedImage[1];
        String s = "right" + (Flame.FLAME_SIZE);
        ans[0] = ImageLoader.loadImage("/image/double_bomb.png");
        ans[0] = new SpriteSheet(ans[0]).crop(0, 0, ans[0].getWidth()-1, ans[0].getHeight()-1);

        double tileSize = (double) ans[0].getWidth() / Flame.FLAME_SIZE;
        ans[0] = new SpriteSheet(ans[0]).crop(0,
                0,
                (int) (length * tileSize),
                ans[0].getHeight());

        return ans;
    }

    public static BufferedImage[] flameDown(int length) {
        BufferedImage[] ans = new BufferedImage[1];
        String s = "down" + (Flame.FLAME_SIZE);
        ans[0] = ImageLoader.loadImage("/image/explosion/bombbang_" + s + ".png");
        ans[0] = new SpriteSheet(ans[0]).crop(0, 0, ans[0].getWidth()-1, ans[0].getHeight()-1);

        double tileSize = (double) ans[0].getHeight() / Flame.FLAME_SIZE;
        ans[0] = new SpriteSheet(ans[0]).crop( 0,
                0,
                ans[0].getWidth(),
                (int) (length * tileSize));

        return ans;
    }

    public static BufferedImage[] flameLeft(int length) {
        BufferedImage[] ans = new BufferedImage[1];
        String s = "left" + (Flame.FLAME_SIZE);
        ans[0] = ImageLoader.loadImage("/image/explosion/bombbang_" + s + ".png");
        ans[0] = new SpriteSheet(ans[0]).crop(0, 0, ans[0].getWidth()-1, ans[0].getHeight()-1);

        double tileSize = (double) ans[0].getWidth() / Flame.FLAME_SIZE;
        ans[0] = new SpriteSheet(ans[0]).crop((int) ((Flame.FLAME_SIZE - length) * tileSize),
                0,
                (int) (length * tileSize),
                ans[0].getHeight());

        return ans;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}