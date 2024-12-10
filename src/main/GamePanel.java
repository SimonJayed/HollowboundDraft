package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxscreenCol = 16;
    public final int maxscreenRow = 12;

    public final int screenWidth = tileSize * maxscreenCol;
    public final int screenHeight = tileSize * maxscreenRow;

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;

    int failCTR = 0;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList <Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        gameState = 1;
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                try{
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                } catch (ArrayIndexOutOfBoundsException e){
                    failCTR++;
                    JOptionPane.showMessageDialog(null, "Someone got erased from existence", "Dead Lol", JOptionPane.ERROR_MESSAGE);
                    player.worldX = tileSize * 23;
                    player.worldY = tileSize * 21;
                    if (failCTR == 3){
                        JOptionPane.showMessageDialog(null, "Game doesn't want you, bro. Peace Out.", "Death Loop", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }

            }

            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if (gameState == playState){
            player.update();

            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    if (npc[i].isAlive == true && npc[i].isDying == false) {
                        npc[i].update();
                    }
                    if (npc[i].isAlive == false){
                        npc[i] = null;
                    }
                }
            }

            for (int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    if (monster[i].isAlive == true && monster[i].isDying == false) {
                        monster[i].update();
                    }
                    if (monster[i].isAlive == false){
                        monster[i] = null;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        tileM.draw(g2);

        entityList.add(player);

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                entityList.add(npc[i]);
            }
            for (i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                    if (monster[i].isAlive == true && monster[i].isDying == false) {
                        monster[i].update();
                    }
                    if (monster[i].isAlive == false){
                        monster[i] = null;
                    }
                }
            }
        }


        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                int result = Integer.compare(o1.worldY, o2.worldY);
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        for (int i = 0; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        }

        entityList.clear();

        ui.draw(g2);

        g2.dispose();
    }

    public int randomize(int Range){
        Random num = new Random();
        int i = num.nextInt(Range) + 1;
        return i;
    }

}
