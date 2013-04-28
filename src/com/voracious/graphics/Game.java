package com.voracious.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
/*
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;*/

import com.voracious.graphics.components.Screen;

/**
 * Basic boiler plate code to create a window and allow rendering pixel data to it.
 * Handles screen and sound management.
 * 
 * @author Andrew Kallmeyer
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int FPS = 60; //FPS to limit the game to
    public static final String NAME = "Monster Flow"; //Title of the game window
    public static final int WIDTH = 200; //Width of the canvas that will be scaled by the scale factor
    public static final int HEIGHT = 150; //Height of the canvas that will be scaled by the scale factor
    public static final int SCALE = 5; //Scale everything up by this factor, 3 means each drawn pixel is draw 3x3 
    private static final boolean showFps = false; //Whether the game should print the fps and ticks per second or not
//changed to false
    
    private static HashMap<String, Screen> screens = new HashMap<String, Screen>();
    private static Screen currentScreen;

    public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private static boolean running = false;
    private static boolean paused = false;
    private boolean displayOver=false;
    private boolean changed=false;
    private boolean clearDoors=false;
    
//aaron's vars
    PlayScreen playS;
    PauseScreen pauseS;
    GameOverScreen overS;
    
    public Game() {
        init();
        
    }

    /**
     * Startup!
     * 
     * @params args Does nothing, ignore.
     */
    public static void main(String[] args) {
        Game game = new Game();
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        game.setMinimumSize(size);
        game.setMaximumSize(size);
        game.setPreferredSize(size);

        JFrame window = new JFrame();
        window.setTitle(NAME);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(game, BorderLayout.CENTER);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.start();
    }

    public void init() {
        this.addKeyListener(new InputHandler());
        
        playS=new PlayScreen(WIDTH, HEIGHT);
        pauseS= new PauseScreen(WIDTH,HEIGHT,playS.getPlayer());
    	overS=new GameOverScreen(this.WIDTH, this.HEIGHT, this.playS.getPlayer());
    	
    	Game.switchScreen(playS);
           
    }
    
    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void tick() {
        if(!paused){
            currentScreen.tick();
            
            if(pauseS.getSwap()){
            	Game.switchScreen(playS);
            	pauseS.changeSwap();
            }
            else if(playS.getSwap()){
            	Game.switchScreen(pauseS);
            	playS.changeSwap();
            }
            
            if(playS.getData().getComplete()[playS.getPlayer().getLoc().getFirst()][playS.getPlayer().getLoc().getSecond()]&&!this.clearDoors){//check if the room has been cleared
            	playS.changeClearDoors();
            	this.clearDoors=true;	
            }
            else if(!playS.getData().getComplete()[playS.getPlayer().getLoc().getFirst()][playS.getPlayer().getLoc().getSecond()]){
            	this.clearDoors=false;
            }
            
            if(playS.getPlayer().getCurrentHp()<=0){
            	switchScreen(overS);
            	if(displayOver){
                	overS.onDisplay(false,0);
                	displayOver=false;
                	changed=true; 
            	}
            	if(!this.changed)
            		this.displayOver=true;
            }
            else if(playS.getPlayer().getLoc().getFirst()==playS.getData().getMonsters().size()-1
        			&&playS.getPlayer().getLoc().getSecond()==playS.getData().getMonsters().get(playS.getPlayer().getLoc().getFirst()).size()-1
        			&&playS.getData().getComplete()[playS.getPlayer().getLoc().getFirst()][playS.getPlayer().getLoc().getSecond()]){
        		overS.changeSprite();
            	switchScreen(overS);
            	if(displayOver){
                	overS.onDisplay(true,this.playS.getData().getMonsters().size()*this.playS.getData().getMonsters().get(0).size());
                	displayOver=false;
                	changed=true; 
            	}
            	if(!this.changed)
            		this.displayOver=true;
        	}
            
            
            	
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        currentScreen.render();
        currentScreen.draw(pixels);
        

        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        
        //Draws the strings for me on the game over Screen.
        if(this.currentScreen.getClass()==this.overS.getClass() &&overS.getS().getFileName().equals("gameOver.png")){
        	ArrayList<String> tmp=overS.getRanks();
        	for(int i=0, x=455,  y=125;i<tmp.size();i++,y+=20){
        		g.drawString(tmp.get(i), x, y);
        	}
        }
        else if(this.currentScreen.getClass()==this.overS.getClass() &&overS.getS().getFileName().equals("Winner.png")){
        	ArrayList<String> tmp=overS.getRanks();
        	for(int i=0, x=505,  y=75;i<tmp.size();i++,y+=20){
        		g.drawString(tmp.get(i), x, y);
        	}
        }
        
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long last = System.nanoTime();
        long lastMillis = System.currentTimeMillis();
        double unprocessedTicks = 0;
        double nsPerTick = 1000000000.0 / FPS;
        int framesShown = 0;
        int ticksProcessed = 0;
        boolean needsRender = false;

        while (running) {
            long now = System.nanoTime();
            unprocessedTicks += (now - last) / nsPerTick;
            last = now;

            if (unprocessedTicks >= 1.0) {
                needsRender = true;
                ticksProcessed++;
                tick();
                unprocessedTicks -= 1.0;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (needsRender) {
                render();
                framesShown++;
                needsRender = false;
            }

            if (System.currentTimeMillis() - lastMillis > 1000) {
                lastMillis = System.currentTimeMillis();
                if (showFps) {
                    System.out.println(ticksProcessed + " tps    " + framesShown + " fps");
                }
                framesShown = 0;
                ticksProcessed = 0;
            }
        }
    }

    public static void switchScreen(Screen screen) {
        if(currentScreen != null){
            currentScreen.stop();
        }
        currentScreen = screen;
        currentScreen.start();
    }

    public static void setPaused(boolean paused){
        Game.paused = paused;
    }
    
    public static boolean isPaused(){
        return Game.paused;
    }
}
