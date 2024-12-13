package main;

import entity.Entity;
import entity.Player;
import forms.CharacterPICK;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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


    public double FPS = 60;

    int failCTR = 0;

    TileManager tileM = new TileManager(this);
    CharacterPICK charSelection = new CharacterPICK(this);
    MainMenu mainMenu = new MainMenu(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    Thread gameThread;
    public StatWindow statWindow = null;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public Player player = new Player(this, keyH, mouseH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[20];
    public Entity monster[] = new Entity[20];
    public ArrayList <Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int flowState = 4;

    public Font customFont;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void setupGame(){
        System.out.println("Player Name: " + player.getName());
        System.out.println("Player Gender: " + player.getGender());
        System.out.println("Player Race: " + player.getRace());
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
        double drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            drawInterval = 1_000_000_000.0 / FPS;
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                try{
                    update();
                    repaint();
                    delta-=1;
                    drawCount++;
                } catch (ArrayIndexOutOfBoundsException e){
                    failCTR++;
                    JOptionPane.showMessageDialog(null, "Someone got erased from existence", "Dead Lol", JOptionPane.ERROR_MESSAGE);
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

    public void setFPS(double FPS){
        this.FPS = FPS;
    }

    public void toggleStatWindow(Entity entity) {
        if (statWindow == null) {
            statWindow = new StatWindow(this, entity);
            statWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            statWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    statWindow = null;
                }
            });
            statWindow.setVisible(true);
        } else {
            statWindow.dispose();
            statWindow = null;
        }
    }

    public void initializePlayer(String name, String gender, String race) {
        player.setName(name);
        player.setGender(gender);
        player.setRace(race);
        player.getPlayerImage();
    }


    public void update(){
        if (gameState == playState || gameState == flowState) {
            player.update();
        }
        if (gameState == playState){
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                        npc[i].update();
                    if (!npc[i].isAlive){
                        npc[i] = null;
                    }
                }
            }

            for (int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    if (monster[i].isAlive && !monster[i].isDying) {
                        monster[i].update();
                    }
                    if (!monster[i].isAlive){
                        monster[i] = null;
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/font1.ttf");
            assert fontStream != null;
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); // Fallback font
        }

        g2.setFont(customFont);

        long drawStart = 0;
        if (keyH.showDebugTest){
            drawStart = System.nanoTime();
        }
        tileM.draw(g2);

        entityList.clear();
        entityList.add(player);

        for (Entity[] entities : new Entity[][] {npc, obj, monster}) {
            for (Entity e : entities) {
                if (e != null) {
                    entityList.add(e);
                }
            }
        }


        entityList.sort(new Comparator<>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                return Integer.compare(e1.worldY, e2.worldY);
            }
        });

        for (Entity entity : entityList) {
            entity.draw(g2);
        }

        entityList.clear();

        ui.draw(g2);


        if (keyH.showDebugTest){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(g2.getFont().deriveFont(23f));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y);y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y);y += lineHeight;
            g2.drawString("Col" + (player.worldX+ player.solidArea.x)/tileSize, x, y);y += lineHeight;
            g2.drawString("Row" + (player.worldY+ player.solidArea.y)/tileSize, x, y);y += lineHeight;

            g2.drawString("Draw Time: " + passed , x, y );
        }


        g2.dispose();
    }

    public int randomize(int min, int max) {
        Random num = new Random();
        if (min > max) {
            throw new IllegalArgumentException("min should be less than or equal to max");
        }
        return num.nextInt((max - min) + 1) + min;
    }

    public String randomName(String filePath) {
        String[] names = new String[100];
        int count = 0;
        Random random = new Random();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && count < names.length) {
                names[count++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, filePath + " missing", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        if (count == 0) {
            System.out.println("No names found in " + filePath);
            return "Bob";
        }

        int randomIndex = random.nextInt(count);
        return names[randomIndex];
    }

}
