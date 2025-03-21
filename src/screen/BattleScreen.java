package screen;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class BattleScreen implements Screen{
    GamePanel gp;

    public Entity currentEnemy;
    public int commandNum = 0;

    public int hitChance;
    public boolean isAttacking = false;

    public BattleScreen(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2){
        int x = 0;
        int y = 0;


        g2.setColor(Color.white);
        g2.fillRect(x,y,gp.screenWidth, gp.screenHeight);

//        x = gp.screenWidth/2 - gp.tileSize;
//        y = gp.tileSize*2;


        double oneScale = gp.screenWidth/currentEnemy.maxLife;
        double hpBarValue = oneScale * currentEnemy.life;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, gp.screenWidth, 20);

        g2.setColor(new Color(255,0,30));
        g2.fillRect(x, y, (int) hpBarValue, 18);

        String text = currentEnemy.life + "/" + currentEnemy.maxLife;
        g2.setFont(g2.getFont().deriveFont( 14f));
        g2.setColor(Color.black);
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);

        y += gp.tileSize/3;

        double oneScale1 = gp.screenWidth/currentEnemy.maxEnergy;
        double energyBarValue = oneScale1 * currentEnemy.energy;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, gp.screenWidth, 18);

        g2.setColor(new Color(255, 227, 24));
        g2.fillRect(x, y, (int) energyBarValue, 16);

        text = currentEnemy.life + "/" + currentEnemy.maxLife;
        g2.setFont(g2.getFont().deriveFont( 14f));
        g2.setColor(Color.black);
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);

        x = gp.screenHeight/2 - gp.tileSize/2;
        y = gp.screenHeight/3 - gp.tileSize;

        g2.drawImage(currentEnemy.down1, x, y, gp.tileSize*5, gp.tileSize*5, null);

        x = gp.tileSize/2;
        y = gp.tileSize*9 + (gp.tileSize/2);
        g2.setColor(new Color(0,0,0));
        g2.fillRoundRect(x, y, gp.tileSize*5, gp.tileSize*4, 20, 20);

        x += gp.tileSize*5 + gp.tileSize;
        g2.setColor(new Color(0,0,0));
        g2.fillRoundRect(x, y, gp.tileSize*11, gp.tileSize*4, 20, 20);

        if(!isAttacking){
            text = "ATTACK";
            g2.setFont(g2.getFont().deriveFont(25f));
            g2.setColor(Color.white);
            x = gp.tileSize/2+5;
            y = gp.tileSize*10+5;
            g2.drawString(text, x, y);

            if(commandNum == 0){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("ATTACK", x, y);
            }

            text = "SKILL";
            g2.setFont(g2.getFont().deriveFont(25f));
            g2.setColor(Color.white);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1  && !isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("SKILL", x, y);
            }

            text = "INVENTORY";
            g2.setFont(g2.getFont().deriveFont(25f));
            g2.setColor(Color.white);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2  && !isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("INVENTORY", x, y);
            }

            text = "FLEE";
            g2.setFont(g2.getFont().deriveFont(25f));
            g2.setColor(Color.white);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3 && !isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("FLEE", x, y);
            }
        }
        else{

            g2.setFont(g2.getFont().deriveFont(25f));
            x = gp.tileSize/2+5;
            y = gp.tileSize*10+5;
            g2.setColor(Color.white);
            g2.drawString("HEAD", x, y);
            if(commandNum == 0 && isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("HEAD", x, y);
            }

            g2.setFont(g2.getFont().deriveFont(25f));
            x = gp.tileSize/2+5;
            y += gp.tileSize;
            g2.setColor(Color.white);
            g2.drawString("TORSO", x, y);
            if(commandNum == 1 && isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("TORSO", x, y);
            }

            g2.setFont(g2.getFont().deriveFont(25f));
            x = gp.tileSize/2+5;
            y += gp.tileSize;
            g2.setColor(Color.white);
            g2.drawString("LEGS", x, y);
            if(commandNum == 2 && isAttacking){
                g2.setColor(new Color(255, 0, 0));
                g2.drawString("LEGS", x, y);
            }
        }


    }


    public void attack() {
        isAttacking = true;
        commandNum = 0;
    }

    public void damage(String targetArea) {
        isAttacking = true;
        int hitRoll = gp.randomize(1, 100);
        double luckFactor = gp.player.luck * 0.01;

        int hitChance = 0;
        double damageMultiplier = 1.0;


        switch (targetArea) {
            case "HEAD":
                hitChance = (int)(40 + (luckFactor * 100));
                damageMultiplier = gp.randomize(2, 3);
                break;
            case "TORSO":
                hitChance = (int)(85 + (luckFactor * 100));
                damageMultiplier = 1.0;
                break;
            case "LEGS":
                hitChance = (int)(65 + (luckFactor * 100));
                damageMultiplier = 1.0;

                currentEnemy.speed -= 1;
                break;
        }

        if (hitRoll <= hitChance) {
            int damage = (int) ((gp.player.attack - currentEnemy.defense) * damageMultiplier);
            damage = Math.max(damage, 1);
            currentEnemy.life -= damage;
            System.out.println("Hit! Dealt " + damage + " damage to " + currentEnemy.getName());
        } else {
            System.out.println("Missed!");
        }
    }

//    public void damage(int i, int damage){
//        damage = damage - gp.livingEntity[i].defense;
//
//        gp.livingEntity[i].health -= damage;
//    }

}
