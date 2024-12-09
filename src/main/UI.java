package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_blank;
    private Font customFont;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public boolean toggleTime = true;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); // Fallback font
        }

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(customFont);
        g2.setColor(Color.white);

        if (gameFinished && !messageOn){

            gp.gameThread = null;
        }
        else {
            if(gp.gameState == gp.playState){
                drawPlayerLife();
                playTime += (double) 1/60;
            }
            else if (gp.gameState == gp.pauseState){
                drawPlayerLife();
                drawPauseScreen();
            }
            if (gp.gameState == gp.dialogueState){
                drawPlayerLife();
                drawDialogueScreen();
            }
//            if (toggleTime){
//                g2.setFont(g2.getFont().deriveFont(20f));
//                g2.setColor(Color.white);
//                g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, gp.tileSize/2);
//            }
//            else{
//                g2.dispose();
//            }


            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20f));
                g2.setColor(Color.yellow);
                g2.drawString(message, getXforCenteredText(message), gp.player.screenY - 15);

                messageCounter++;

                if (messageCounter > 120 || gp.gameState == gp.dialogueState) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPauseScreen(){
        Color c = new Color (0, 0, 0, 95);
        g2.setColor(c);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(175f));
        g2.setColor(Color.white);
        String text = "||";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 + 40;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*5;

        drawSubWindow(x, y, width, height);

        x+= gp.tileSize;
        y+= gp.tileSize;

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y+=40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color (255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
