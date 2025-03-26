package screen;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class InventoryScreen implements Screen{
    GamePanel gp;
    Graphics2D g2;

    public int commandNum = 0;

    public InventoryScreen (GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        //Inventory Frame
        int frameX = gp.tileSize;
        int frameY = gp.tileSize*3;
        int frameWidth = gp.tileSize*7;
        int frameHeight  = gp.tileSize*10;
        gp.ui.drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        frameX = frameWidth + gp.tileSize*2;
        frameWidth += gp.tileSize;
        gp.ui.drawSubWindow(frameX, frameY, frameWidth, frameHeight);



        //Inventory Content
        g2.setColor(Color.white);
        int x = gp.tileSize*2+(gp.tileSize/2);
        int y = gp.tileSize*5;

        g2.drawImage(gp.player.down1, x, y, gp.tileSize*4, gp.tileSize*4, null);

        x = gp.tileSize+8;
        y = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.drawString("NAME: " + gp.player.getName(), x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
        x = (gp.tileSize/2) + gp.screenWidth/2;
        g2.drawString("LEVEL: " + gp.df.format(gp.player.level), x, y);
        y += gp.tileSize/2;
        g2.drawString("EXP: " + gp.df.format(gp.player.exp), x, y);
        y += gp.tileSize/2;
        g2.drawString("EXP TO NEXT LEVEL: " + gp.df.format((gp.player.nextLevelExp-gp.player.exp)), x, y);

        y += gp.tileSize;
        g2.drawString("HEALTH: " + gp.df.format(gp.player.hp) + "/" + gp.df.format(gp.player.maxHP), x, y);
        y += gp.tileSize/2;
        g2.drawString("ENERGY: " + gp.df.format(gp.player.energy) + "/" + gp.df.format(gp.player.maxEnergy), x, y);

        y += gp.tileSize/2;
        g2.drawString("ATTACK: " + gp.df.format(gp.player.attack), x, y);
        y += gp.tileSize/2;
        g2.drawString("DEFENSE: " + gp.df.format(gp.player.defense), x, y);
        y += gp.tileSize/2;
        g2.drawString("SPEED: " + gp.df.format(gp.player.speed), x, y);

        y += gp.tileSize;
        g2.drawString("VITALITY: " + gp.df.format(gp.player.vit), x, y);
        y += gp.tileSize/2;
        g2.drawString("POWER: " + gp.df.format(gp.player.pow), x, y);
        y += gp.tileSize/2;
        g2.drawString("MAGIC: " + gp.df.format(gp.player.mag), x, y);
        y += gp.tileSize/2;
        g2.drawString("AGILITY: " + gp.df.format(gp.player.agi), x, y);
        y += gp.tileSize/2;
        g2.drawString("LUCK: " + gp.df.format(gp.player.luck), x, y);
        if(gp.player.statPoints > 0){
            addStatPoints(g2, (gp.tileSize/2) + gp.screenWidth/2, gp.screenHeight/2+(gp.tileSize/2*4));
        }
    }

    public void addStatPoints(Graphics2D g2, int x, int y){

        g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
        g2.setColor(new Color(0,0,0, 150));
        g2.drawString("+", x+gp.tileSize*3, y);
        g2.setColor(new Color(255,255,255));
        if(commandNum == 0){
            g2.setColor(new Color(255, 0, 0, 255));
            g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
            g2.setColor(new Color(0,0,0, 150));
            g2.drawString("+", x+gp.tileSize*3, y);
            g2.setColor(new Color(255,255,255));
        }
        y += gp.tileSize/2;
        g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
        g2.setColor(new Color(0,0,0, 150));
        g2.drawString("+", x+gp.tileSize*3, y);
        g2.setColor(new Color(255,255,255));
        if(commandNum == 1){
            g2.setColor(new Color(255, 0, 0, 255));
            g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
            g2.setColor(new Color(0,0,0, 150));
            g2.drawString("+", x+gp.tileSize*3, y);
            g2.setColor(new Color(255,255,255));
        }
        y += gp.tileSize/2;
        g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
        g2.setColor(new Color(0,0,0, 150));
        g2.drawString("+", x+gp.tileSize*3, y);
        g2.setColor(new Color(255,255,255));
        if(commandNum == 2){
            g2.setColor(new Color(255, 0, 0, 255));
            g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
            g2.setColor(new Color(0,0,0, 150));
            g2.drawString("+", x+gp.tileSize*3, y);
            g2.setColor(new Color(255,255,255));
        }
        y += gp.tileSize/2;
        g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
        g2.setColor(new Color(0,0,0, 150));
        g2.drawString("+", x+gp.tileSize*3, y);
        g2.setColor(new Color(255,255,255));
        if(commandNum == 3){
            g2.setColor(new Color(255, 0, 0, 255));
            g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
            g2.setColor(new Color(0,0,0, 150));
            g2.drawString("+", x+gp.tileSize*3, y);
            g2.setColor(new Color(255,255,255));
        }
        y += gp.tileSize/2;
        g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
        g2.setColor(new Color(0,0,0, 150));
        g2.drawString("+", x+gp.tileSize*3, y);
        g2.setColor(new Color(255,255,255));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
        if(commandNum == 4){
            g2.setColor(new Color(255, 0, 0, 255));
            g2.drawOval(x+gp.tileSize*3, y-10, 8, 8);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));
            g2.setColor(new Color(0,0,0, 150));
            g2.drawString("+", x+gp.tileSize*3, y);
            g2.setColor(new Color(255,255,255));
        }
    }
}
