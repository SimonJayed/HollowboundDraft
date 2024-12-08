package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    private Font customFont;
    BufferedImage keyImage;

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

    public void draw(Graphics2D g2){
        g2.setFont(customFont);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 - 8, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = " + gp.player.hasKey, 74, 50);
    }
}
