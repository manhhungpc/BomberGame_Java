package main.game;

public class Game implements Runnable {
    private Display display;
    public int height, width;
    public String title;

    private Thread thread;
    private boolean running = false;

    public Game(String title, int height, int width) {
        this.title = title;
        this.height = height;
        this.width = width;
    }

    public void run() {
        init();
        while(running) {
            tick();
            render();
        }
        stop();
    }

    private void init() {
        display = new Display(title, height, width);
    }

    private void tick(){

    }

    private void render(){

    }

    public synchronized void start() {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running) return;
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
