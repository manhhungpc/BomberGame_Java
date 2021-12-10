package main.AI;

import main.TimeManage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayerAI {
    public static final long TIME_LAST_FOR_KEY = 1;

    private Robot robot;
    private final List<KeyWithTime> keyWithTimeList;

    public PlayerAI() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        keyWithTimeList = new ArrayList<>();

    }

    public void press(int keyCode) {
        robot.keyPress(keyCode);
    }

    public void release(int keyCode) {
        robot.keyRelease(keyCode);
    }

    public void pressForTime(int keyCode) {
        press(keyCode);
        keyWithTimeList.add(new KeyWithTime(keyCode));
    }

    public void tick() {
        for (int i = keyWithTimeList.size() - 1; i >= 0; i--) {
            if (TimeManage.timeNow() >= keyWithTimeList.get(i).timeDie) {
                release(keyWithTimeList.get(i).keyCode);
                keyWithTimeList.remove(i);
            }
        }
    }

    public void bomb() {
        pressForTime(KeyEvent.VK_B);
    }

    private static class KeyWithTime {
        int keyCode;
        long timeDie;

        public KeyWithTime(int keyCode) {
            this.keyCode = keyCode;
            timeDie = TimeManage.timeNow() + TIME_LAST_FOR_KEY;
        }
    }

    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_T);
                    robot.keyRelease(KeyEvent.VK_T);
                    robot.keyPress(KeyEvent.VK_KP_UP);
                    robot.keyRelease(KeyEvent.VK_KP_UP);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 3, 3, TimeUnit.SECONDS);
    }
}
