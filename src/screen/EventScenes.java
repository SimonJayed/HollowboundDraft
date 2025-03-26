package screen;

import entity.Entity;
import entity.NPC_MamaPausy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EventScenes implements Screen{
    GamePanel gp;
    Graphics2D g2;

    BufferedImage portrait = null;
    BufferedImage sceneBackground = null;

    public ArrayList<String> dialogues = new ArrayList<>();
    public String currentDialogue = "...";
    public int dialogueIndex = 0;

    int buffer = 0;
    int sequenceCheck = 0;
    public int eventNum = 0;

    public boolean dialogueOn = false;
    public boolean dialogueFinished = false;
    public boolean eventFinished = false;
    public boolean entitySet = false;

    public boolean event0Flag = false;
    public boolean event2Flag = false;

    public EventScenes(GamePanel gp){
        this.gp = gp;
    }

    public void updateEvent() {
        switch(eventNum){
            case 0:{
                if(gp.currentMap == 1 && !event0Flag){
                    if(!entitySet){
                        gp.livingEntity[1][1] = gp.livingEntity[0][3];
                        gp.livingEntity[1][1].setEvent("left", 5, 45, 2, 1, false);
                        entitySet = true;
                    }
                    else{
                        playEvent0();
                    }
                }
                break;
            }
            case 1:{
                if(gp.currentMap == 3 && !event2Flag){

                    if(!entitySet){
                        gp.livingEntity[3][0].setEvent("left", 46, 39, 50, 1, false);
                        gp.livingEntity[3][0].setLevel(60);
                        gp.livingEntity[3][0].hasEvent = true;
                        gp.livingEntity[3][0].setDialogue();
                        eventFinished = false;
                        System.out.println("Mama Pausy Dialogue Set");
                        entitySet = true;
                        System.out.println("POTAAAAAA");
                    }
                    else{
                        playEvent2();
                    }
                }
            break;
            }
        }
    }

    public void playEvent0(){
        int sequenceLimit = dialogues.size();
        System.out.println("Dialogue size: " + dialogues.size());
        System.out.println("Entityet: " + entitySet);
        gp.gameState = gp.eventState;
        gp.player.isRunning = false;
        buffer++;


        if (gp.livingEntity[0][3] != null) {  // Prevents null assignment
            gp.livingEntity[1][1] = gp.livingEntity[0][3];
        }

        if (gp.livingEntity[1][1] != null && gp.livingEntity[1][1].collisionOn && sequenceCheck == 0) {
            setDialoguePortrait("amaryllis");
            System.out.println("Sequence: " + sequenceCheck);
        }

        if (sequenceCheck == 1) {
            portrait = null;
            System.out.println("Sequence: " + sequenceCheck);
            sceneBackground = setBackground("nightmareScene3");
        }
        if (sequenceCheck == 2) {
            System.out.println("Sequence: " + sequenceCheck);
            sceneBackground = setBackground("nightmareScene2");

        }
        if (sequenceCheck == 3) {
            System.out.println("Sequence: " + sequenceCheck);
            sceneBackground = setBackground("nightmareScene2");
            gp.ui.startFadeOut();
        }

        if (sequenceCheck == sequenceLimit) {
            eventFinished = true;
            Entity entity = gp.livingEntity[1][1];
            System.out.println("Sequence: " + sequenceCheck);
            if (entity != null) {
                gp.livingEntity[1][1] = null;
                gp.entityList.remove(entity);
                gp.livingEntity[0][3] = entity;
                entity.worldX = entity.spawnPointX = 8 * gp.tileSize;
                entity.worldY = entity.spawnPointY = 10 * gp.tileSize;
            }
            event0Flag = true;
        }
    }
    public void playEvent2(){
        int sequenceLimit = dialogues.size();
        System.out.println("Event: " + eventNum);
        System.out.println("Dialogue size: " + dialogues.size());
        gp.gameState = gp.eventState;
        gp.player.isRunning = false;
        gp.player.worldX = 37 *gp.tileSize;
        gp.player.worldY = 39*gp.tileSize;
        buffer++;
//
        if(gp.livingEntity[3][0].collisionOn && sequenceCheck == 0){
            System.out.println("Sequence: " + sequenceCheck);
            System.out.println("DialogeOn :: " + dialogueOn);
            System.out.println("DialogueFinished :: " + dialogueFinished);
            System.out.println("EventeFinished :: " + eventFinished);
            gp.gameState = gp.battleState;
            gp.battleScreen.currentEnemy = gp.livingEntity[3][0];
            gp.player.isUnconscious = true;
        }
        if(sequenceCheck == 2){
            System.out.println("Sequence: " + sequenceCheck);
            gp.player.draw(g2);
            gp.player.isUnconscious = true;
            gp.ui.startFadeOut();
        }
        if(sequenceCheck == 3){
            sceneBackground = setBackground("nightmareScene1");
            gp.player.isUnconscious = true;
            gp.player.draw(g2);
        }
        if(sequenceCheck == 4){
            sceneBackground = setBackground("nightmareScene2");
            System.out.println("Sequence: " + sequenceCheck);
        }
        if(sequenceCheck == 5){
            sceneBackground = setBackground("nightmareScene3");
            System.out.println("Sequence: " + sequenceCheck);
        }
        if(sequenceCheck == 6){
            sceneBackground = setBackground("nightmareScene4");
            System.out.println("Sequence: " + sequenceCheck);
        }
        if(sequenceCheck == 7){
            sceneBackground = setBackground("nightmareScene3");
            System.out.println("Sequence: " + sequenceCheck);
        }

        if(sequenceCheck == sequenceLimit) {
            if(buffer > 100){
                gp.player.isUnconscious = true;
                gp.ui.startFadeOut();
                gp.currentMap = 4;
                gp.player.worldX = gp.player.spawnPointX = 20 * gp.tileSize;
                gp.player.worldY = gp.player.spawnPointY = 14 * gp.tileSize;
                eventFinished = true;
                System.out.println("Sequence: " + sequenceCheck);
                System.out.println("DialogeOn :: " + dialogueOn);
                System.out.println("DialogueFinished :: " + dialogueFinished);
                System.out.println("EventeFinished :: " + eventFinished);
                Entity entity = gp.livingEntity[3][0];
                gp.livingEntity[3][0] = null;
                gp.entityList.remove(entity);
                gp.livingEntity[4][0] = entity;
                gp.livingEntity[4][0].hasEvent = false;
                gp.livingEntity[4][0].speed = 3;
                gp.livingEntity[4][0].worldX = gp.livingEntity[4][0].spawnPointX = 21 * gp.tileSize;
                gp.livingEntity[4][0].worldY = gp.livingEntity[4][0].spawnPointY = 22 * gp.tileSize;
            }
        }
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        int x = 0;
        int y = 0;
        g2.setColor(Color.black);
        g2.fillRect(x, y, gp.screenWidth, gp.tileSize*3);
        g2.fillRect(x, gp.screenHeight-gp.tileSize*3, gp.screenWidth, gp.tileSize*3);

        if(dialogueOn && !dialogueFinished && !eventFinished){
            drawDialogueScreen();
        }
    }

    public BufferedImage setBackground(String filename){
        BufferedImage background = null;
        try{
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/"+ filename + ".png")));
        }catch (IOException e){
            System.err.println("Error loading background: " + filename);
            return null;
        }
        return background;
    }

    public BufferedImage setDialoguePortrait(String characterName){
        try {
            portrait = ImageIO.read(getClass().getResourceAsStream("/sprites/" + characterName +"/portrait.png"));
        } catch (IOException e) {
            System.err.println("Error loading background: " + characterName);
            return null;
        }
        return portrait;
    }

    public void drawDialogueScreen() {
        int x = 0;
        int y = 0;

        if(sceneBackground != null){
            g2.drawImage(sceneBackground, x, y,gp.screenWidth, gp.screenHeight, null);
        }

        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 4;
        x = gp.tileSize;
        y = gp.tileSize * 9 + gp.tileSize/2;

        drawSubWindow(x, y, width, height);

        if (portrait != null) {
            int imgX = gp.tileSize * 10;
            int imgY = gp.tileSize * 4;
            int imgHeight = gp.tileSize * 11;
            int imgWidth = gp.tileSize * 8;
            g2.drawImage(portrait, imgX, imgY, imgWidth, imgHeight, null);
        }

        x += gp.tileSize;
        y += gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
        g2.setColor(Color.white);
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void nextDialogue() {
        if (dialogueIndex < dialogues.size()) {
            currentDialogue = dialogues.get(dialogueIndex);
            System.out.println("Current Dialogue: " + currentDialogue);
            dialogueIndex++;
            sequenceCheck++;
        } else {
            dialogueFinished = true;
            dialogueOn = false;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,20, 20);

        g2.setColor(new Color(255,255,255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 20, 20);
    }

    public void eventExit(){
        System.out.println("Event " + eventNum + " finished");
        eventNum++;
        eventFinished = false;
        System.out.println("Finishded : " + eventFinished );
        entitySet = false;
        gp.player.isUnconscious = false;
        dialogueIndex = 0;
        System.out.println("Entityset : " + entitySet );
        sequenceCheck = 0;
        portrait = null;
        sceneBackground = null;
        dialogues.clear();
        System.out.println("buffer MAN"+ buffer);
        gp.gameState = gp.playState;
        buffer = 0;
    }
}

