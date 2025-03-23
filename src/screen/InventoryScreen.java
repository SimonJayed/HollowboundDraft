package screen;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class InventoryScreen implements Screen{
    GamePanel gp;

    public int commandNum = 0;

    public InventoryScreen (GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {
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
    }
}
