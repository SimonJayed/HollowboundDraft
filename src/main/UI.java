package main;

import object.OBJ_Key;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    private Font customFont;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(20f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); // Fallback font
        }

        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(customFont);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 - 8, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = " + gp.player.hasKey, 74, 50);

        if (gameFinished && !messageOn){
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.setColor(Color.white);

            String text;
            int textLength, x, y, regressCTR = 0;

            text = "All Objects Obtained!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(40f));
            g2.setColor(Color.yellow);
            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(15f));
            g2.setColor(Color.yellow);
            text = "GAME FINISHED";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize);
            g2.drawString(text, x, y);

            regressCTR++;

            if (regressCTR > 250) {
                int choice = JOptionPane.showConfirmDialog(null, "Regress??", " ??? ", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    JFrame window = new JFrame();
                    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setSize(768, 576);
                    window.setTitle("REMAIN");

                    GamePanel gamePanel = new GamePanel();
                    window.add(gamePanel);

                    window.pack();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true); // Make the window visible

                    gamePanel.setupGame();
                    gamePanel.startGameThread();
                } else {
                    System.exit(0);
                }
            }
        }

        if (messageOn) {

            g2.setFont(g2.getFont().deriveFont(20f));
            g2.setColor(Color.yellow);
            g2.drawString(message, gp.player.screenX-60, gp.player.screenY-15);

            messageCounter++;

            if (messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
