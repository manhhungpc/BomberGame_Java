package main.gfx;

import main.entities.bomb.Flame;

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
    public static BufferedImage[] bombGif;
    public static BufferedImage flameItem, bombItem, speedItem;

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
        bombGif[1] = bomb;

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

        flameItem = ImageLoader.loadImage("/image/power_pierce.png");
        speedItem = ImageLoader.loadImage("/image/power_speed.png");
        bombItem = ImageLoader.loadImage("/image/power_bomb.png");

//        for (int i = 0; i <= 4; i++) {
//            for (int j = 0; j <= 6; j++) {
//                BufferedImage temp = spriteSheetExplosion.crop(i*32, j*32, 32, 32);
//                saveImage(temp, i + "_" + j);
//            }
//        }
    }

    private static void saveImage(BufferedImage image, String name) {
        File outputfile = new File("D:\\" + name + ".png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage[] flameLeft(int length) {
        BufferedImage[] ans = new BufferedImage[8];
        String s = "left" + (Flame.flameSize);

        for (int i = 0; i <= 4; i++) {
//            System.out.println("ans[" + i + "]" + " /image/explosion/"+ i + "/" + i + "bombbang_" + s + ".png");
//            ans[i] = ImageLoader.loadImage("/image/explosion/"+ i + "/" + i + "bombbang_" + s + ".png");
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (4-i) + "/bombbang_" + s + ".png");
            ans[i] = cropLeft(length, ans[i]);
        }
        for (int i = 5; i <= 7; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (i-4) + "/bombbang_" + s + ".png");
            ans[i] = cropLeft(length, ans[i]);
        }
//        ans[6] = ImageLoader.loadImage("/image/explosion/"+ 2 + "/bombbang_" + s + ".png");
//        ans[7] = ImageLoader.loadImage("/image/explosion/"+ 3 + "/bombbang_" + s + ".png");

        return ans;
    }

    public static BufferedImage[] flameRight(int length) {
        BufferedImage[] ans = new BufferedImage[8];
        String s = "right" + (Flame.flameSize);

        for (int i = 0; i <= 4; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (4-i) + "/bombbang_" + s + ".png");
            ans[i] = cropRight(length, ans[i]);
        }
        for (int i = 5; i <= 7; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (i-4) + "/bombbang_" + s + ".png");
            ans[i] = cropRight(length, ans[i]);
        }

        return ans;
    }

    public static BufferedImage[] flameUp(int length) {
        BufferedImage[] ans = new BufferedImage[8];
        String s = "up" + (Flame.flameSize);

        for (int i = 0; i <= 4; i++) {
//            System.out.println("/image/explosion/"+ i + "/bombbang_" + s + ".png");
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (4-i) + "/bombbang_" + s + ".png");
            ans[i] = cropUp(length, ans[i]);
        }
        for (int i = 5; i <= 7; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (i-4) + "/bombbang_" + s + ".png");
            ans[i] = cropUp(length, ans[i]);
        }

        return ans;
    }

    public static BufferedImage[] flameDown(int length) {
        BufferedImage[] ans = new BufferedImage[8];
        String s = "down" + (Flame.flameSize);

        for (int i = 0; i <= 4; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (4-i) + "/bombbang_" + s + ".png");
            ans[i] = cropDown(length, ans[i]);
        }
        for (int i = 5; i <= 7; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (i-4) + "/bombbang_" + s + ".png");
            ans[i] = cropDown(length, ans[i]);
        }

        return ans;
    }

    public static BufferedImage[] flameMid() {
        BufferedImage[] ans = new BufferedImage[8];
        for (int i = 0; i <= 4; i++) {
//            System.out.println("/image/explosion/"+ i + "/bombbang_mid.png");
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (4-i) + "/bombbang_mid.png");
        }
        for (int i = 5; i <= 7; i++) {
            ans[i] = ImageLoader.loadImage("/image/explosion/"+ (i-4) + "/bombbang_mid.png");
        }
        return ans;
    }



    private static BufferedImage cropLeft(int length, BufferedImage ans) {
        ans = new SpriteSheet(ans).crop(0, 0, ans.getWidth()-1, ans.getHeight()-1);
        double tileSize = (double) ans.getWidth() / Flame.flameSize;
        ans = new SpriteSheet(ans).crop((int) ((Flame.flameSize - length) * tileSize),
                0,
                (int) (length * tileSize),
                ans.getHeight());
        return ans;
    }

    private static BufferedImage cropRight(int length, BufferedImage ans) {
        ans = new SpriteSheet(ans).crop(0, 0, ans.getWidth()-1, ans.getHeight()-1);
        double tileSize = (double) ans.getWidth() / Flame.flameSize;
        ans = new SpriteSheet(ans).crop(0,
                0,
                (int) (length * tileSize),
                ans.getHeight());
        return ans;
    }

    private static BufferedImage cropUp(int length, BufferedImage ans) {
        ans = new SpriteSheet(ans).crop(0, 0, ans.getWidth()-1, ans.getHeight()-1);
        double tileSize = (double) ans.getHeight() / Flame.flameSize;
        ans = new SpriteSheet(ans).crop( 0,
                (int) ((Flame.flameSize - length) * tileSize),
                ans.getWidth(),
                (int) (length * tileSize));
        return ans;
    }

    private static BufferedImage cropDown(int length, BufferedImage ans) {
        ans = new SpriteSheet(ans).crop(0, 0, ans.getWidth()-1, ans.getHeight()-1);
        double tileSize = (double) ans.getHeight() / Flame.flameSize;
        ans = new SpriteSheet(ans).crop( 0,
                0,
                ans.getWidth(),
                (int) (length * tileSize));
        return ans;
    }

//    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
//        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
//        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
//        graphics2D.dispose();
//        return resizedImage;
//    }
}