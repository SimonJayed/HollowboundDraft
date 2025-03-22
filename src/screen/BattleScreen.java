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
    public boolean canEscape = true;

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

        gp.ui.drawMessage();

        //ENEMYBAR
        double oneScale = gp.screenWidth/currentEnemy.maxHP;
        double hpBarValue = oneScale * currentEnemy.hp;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, gp.screenWidth, 20);

        g2.setColor(new Color(255,0,30));
        g2.fillRect(x, y, (int) hpBarValue, 18);

        String text = gp.df.format(currentEnemy.hp) + "/" + gp.df.format(currentEnemy.maxHP);
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

        text = gp.df.format(currentEnemy.energy) + "/" + gp.df.format(currentEnemy.maxEnergy);
        g2.setFont(g2.getFont().deriveFont( 14f));
        g2.setColor(Color.black);
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);
        y += gp.tileSize/3;
        text = "Level: " + currentEnemy.level;
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);



        //PLAYERBAR

        y = (gp.screenHeight/2)+gp.tileSize*2-8;
        double oneScale3 = gp.screenWidth/gp.player.maxHP;
        double hpBarValue2 = oneScale3 * gp.player.hp;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, gp.screenWidth, 20);

        g2.setColor(new Color(255,0,30));
        g2.fillRect(x, y, (int) hpBarValue2, 18);

        text = gp.player.hp + "/" + gp.player.maxHP;
        g2.setFont(g2.getFont().deriveFont( 14f));
        g2.setColor(Color.black);
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);

        y += gp.tileSize/3;

        double oneScale4 = gp.screenWidth/gp.player.maxEnergy;
        double energyBarValue2 = oneScale4 * gp.player.energy;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, gp.screenWidth, 18);

        g2.setColor(new Color(255, 227, 24));
        g2.fillRect(x, y, (int) energyBarValue2, 16);

        text = gp.df.format(gp.player.energy) + "/" + gp.df.format(gp.player.maxEnergy);
        g2.setFont(g2.getFont().deriveFont( 14f));
        g2.setColor(Color.black);
        g2.drawString(text, gp.ui.getXforCenteredText(g2, text), y+12);
        y += gp.tileSize*4;
        text = "Level: " + gp.player.level;
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
            gp.ui.addMessage(gp.player.getName() + " ATTACKS!");
            int damage = (int) ((gp.player.attack - currentEnemy.defense) * damageMultiplier);
            damage = Math.max(damage, 1);
            currentEnemy.hp -= damage;
            if(currentEnemy.hp < 0){
                currentEnemy.hp = 0;
            }
//            gp.ui.addMessage("Hit!" + gp.player.getName() + " deals " + damage + " damage to " + currentEnemy.getName());
        } else {
//            gp.ui.addMessage(gp.player.getName() + " missed!");
        }

        if(currentEnemy.hp == 0){
            endBattle();
        }
    }

    public void damagePlayer(String targetArea) {
        int hitRoll = gp.randomize(1, 100);
        double luckFactor = currentEnemy.luck * 0.01;

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
                gp.player.speed -= 1;
                break;
        }

        if (hitRoll <= hitChance) {
            gp.ui.addMessage(currentEnemy.getName() + " ATTACKS!");
            int damage = (int) ((currentEnemy.attack - gp.player.defense) * damageMultiplier);
            damage = Math.max(damage, 1);
            gp.player.hp -= damage;
            if(gp.player.hp < 0){
                gp.player.hp = 0;
            }
//            gp.ui.addMessage("Hit! " + currentEnemy.getName() + " deals " + damage + " damage to " + gp.player.getName());
        } else {
//            gp.ui.addMessage(currentEnemy.getName() + " missed!");
        }

        if(gp.player.hp == 0){
            enemyEndBattle();
        }
    }

    public void endBattle(){
        double levelDifference = gp.player.level-currentEnemy.level;
        double expGain = currentEnemy.nextLevelExp;

        if (levelDifference < -10) {
            expGain *=  Math.abs(levelDifference) / 2;
        } else if (levelDifference < -5) {
            expGain *= 2;
        } else if (levelDifference > 10) {
            expGain *= 0.5;
        } else if (levelDifference > 5) {
            expGain *= 0.75;
        } else {
            expGain *= 1.25;
        }

        gp.player.exp += expGain;
        currentEnemy.isDefeated = true;
        currentEnemy.hollowCounter++;
        System.out.println(currentEnemy.getName() + " has died " + currentEnemy.hollowCounter + " times");
        gp.gameState = gp.playState;
        System.out.println("Battle finished.");
    }

    public void enemyEndBattle(){
        double levelDifference = gp.player.level-currentEnemy.level;
        double expGain = gp.player.nextLevelExp;

        if (levelDifference > 10) {
            expGain *= 0.5;
        } else if (levelDifference > 5) {
            expGain *= 0.75;
        } else if (levelDifference < -10) {
            expGain *=  Math.abs(levelDifference) / 2;
        } else if (levelDifference < -5) {
            expGain *= 2;
        } else {
            expGain *= 1.25;
        }

        currentEnemy.exp += expGain;

        gp.player.isDefeated = true;
        gp.player.hollowCounter++;
        System.out.println(gp.player.getName() + " has died " + gp.player.hollowCounter + " times");
        gp.gameState = gp.playState;
        System.out.println("Battle finished.");
    }

    public void enemyTurn(){
        if(currentEnemy != null){
            int choice = gp.randomize(0,2);

            switch (choice){
                case 0:{
                    damagePlayer("HEAD");
                    break;
                }
                case 1:{
                    damagePlayer("TORSO");
                    break;
                }
                case 2:{
                    damagePlayer("LEGS");
                    break;
                }
            }
        }
    }

}
