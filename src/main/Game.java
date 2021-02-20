package src.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;

import src.gameobjects.Bird;
import src.gameobjects.Ground;
import src.handlers.KeyHandler;
import src.handlers.MouseHandler;
import src.handlers.ObjectHandler;
import src.handlers.TubeHandler;
import src.loaders.GraphicsLoader;
import src.supers.Button;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 288;
    public static final int HEIGHT = 512;

    public boolean running;
    public static boolean gameover;

    public static BufferedImage img_gameover;
    public static BufferedImage background;
    public static Ground ground;
    public static Bird bird;
    
    public static Button startButton;

    public static int score;

    Thread thread;
    ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        new Window(WIDTH, HEIGHT, "FlappyBird", new Game());
    }

    public synchronized void start(){
        running = true;
        thread  = new Thread();
        thread.start();
        run();
    }

    public void init(){
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());

        img_gameover = GraphicsLoader.loadGraphics("gameover.png");
        background = GraphicsLoader.loadGraphics("background.png");
        ground = new Ground();

        bird = new Bird(25, 25, 34, 24);

        startButton = new Button(Game.WIDTH / 2 - 108 / 2, 100, 108, 60, GraphicsLoader.loadGraphics("playbutton.png"));
    }

    public void tick(){
        if(!gameover){
            ObjectHandler.tick();
            ground.tick();
        }
        
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(background, 0, 0, null);
        ground.render(g);
        
        ObjectHandler.render(g);

        if(gameover) {
            g.drawImage(img_gameover, Game.WIDTH / 2 - 192/2, 50, null);
            Game.startButton.render(g);
        }

        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.setColor(Color.WHITE);

        String s = Integer.toString(score);
        int textWidth = g.getFontMetrics().stringWidth(s);

        g.drawString(s, Game.WIDTH / 2 - textWidth / 2, 40);

        g.dispose();
        bs.show();
    }

    @Override
    public void run(){
        init();
        this.requestFocus();

        long pastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while(delta > 0){
                tick();
                updates++;

                render();
                frames++;

                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: "+frames+" | TICKS: "+updates);
                TubeHandler.tick();
                updates = 0;
                frames = 0;
            }
        }
    }
}