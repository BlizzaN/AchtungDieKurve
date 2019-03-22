package com.b16ponpe;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private Menu menu;

    public static final int WIDTH = 900, HEIGHT = 600;
    public static final int PLAYERSIZE = 5;

    public Game() {
        handler = new Handler(this);
        menu = new Menu(this, handler);

        new Gui(WIDTH, HEIGHT, "Achtung Die Kurve", this);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            System.out.println("Game error catch: " + e.getMessage());
        }
    }

    //Different states for different behaviors
    public enum STATE {
        Menu,
        Game,
        Settings,
        Collide;
    }

    public STATE gameState = STATE.Menu;

    //Gameloop handles the frames per second to update the frame
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        //Speed for updating the frame
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            render();
            frames++;

            //Print out FPS
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

        if (gameState == STATE.Game) {
            handler.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        //Render background
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        //Render correct state
        if (gameState == STATE.Game || gameState == STATE.Collide) {
            handler.render(g2);
        } else if (gameState == STATE.Menu || gameState == STATE.Settings) {
            menu.render(g2);
        }

        g2.dispose();
        bs.show();

    }


}
