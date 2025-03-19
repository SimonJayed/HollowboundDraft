package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public GamePanel gp;

    public int level = 1;
    private String name;
    public int worldX, worldY;
    public int speed = 1;
    public int strength = 2;
    public int defense = 1;
    public double exp = 1;
    public double nextLevelExp = level*2;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackLeft1, attackLeft2, attackLeft3, attackRight1, attackRight2, attackRight3;
    public BufferedImage runUp1, runUp2, runUp3, runDown1, runDown2, runDown3, runLeft1, runLeft2, runLeft3, runRight1, runRight2, runRight3;
    public BufferedImage image1, image2, image3;
    public BufferedImage portrait;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 48, 48);
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 2;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int type;


    public String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public boolean collision = false;
    public boolean invincible = false;
    public boolean collisionOn = false;
    public boolean isAttacking = false;
    public boolean isIdling = false;
//    public boolean isDashing = false;
    public boolean isRunning = false;
    public boolean isAlive = true;
    public boolean isDying = false;
    public boolean hpBarOn = true;

    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    public double maxLife;
    public double life;
    public double maxEnergy = 10;
    public double energy = 10;
    public double energyRegen = maxEnergy*0.1;

    public int buffer = 0;


    public Entity(GamePanel gp){
        this.gp = gp;
        level = gp.randomize(1, 20);
    }

    public String getName() {return name;}

    public void setName(String name) {this.name =  name;}

    public void getImage(String folder) {
        up1 = setup("/" + folder + "/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/" + folder + "/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/" + folder + "/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/" + folder + "/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/" + folder + "/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/" + folder + "/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/" + folder + "/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/" + folder + "/right2", gp.tileSize, gp.tileSize);
        portrait = setup("/" + folder + "/portrait", gp.tileSize, gp.tileSize);
    }


    public void speak(){
        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        gp.playSoundEffect(1);

        switch (gp.player.direction){
            case "up": {
                direction = "down";
                break;
            }
            case "down": {
                direction = "up";
                break;
            }
            case "left": {
                direction = "right";
                break;
            }
            case "right": {
                direction = "left";
                break;
            }
            default:{
                direction = "down";
            }
        }
    }

    public void setAction(){}

    public void regen(){
        buffer++;
        if(buffer >= 250 && energy < maxEnergy && isRunning == false){
            energy += energyRegen;
            buffer = 0;
            System.out.println(name + " is regenerating.");
        }


    }

    public void update(){
        setAction();
        regen();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (type == 2 && contactPlayer) {
            if (!gp.player.invincible){
//                gp.gameState = gp.battleState;
                System.out.println("FIGHT!!!");
            }
        }

        if (!collisionOn && isIdling == false) {
            switch (direction) {
                case "up": {
                    worldY -= speed;
                    break;
                }
                case "down": {
                    worldY += speed;
                    break;
                }
                case "left": {
                    worldX -= speed;
                    break;
                }
                case "right": {
                    worldX += speed;
                    break;
                }
            }
        }
        else{
            idling();
        }

        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void spriteAnim(int spriteQuantity){
        spriteCounter++;
        if (spriteCounter > spriteQuantity * 13) {
            spriteCounter = 1;
        }
        spriteNum = (spriteCounter - 1) / 13 + 1;
    }


    public void idling(){
        isIdling = true;
    }

    public void getAttackImage() {
    }

    public void contactMonster(int i){

        if (i != 999){
            gp.ui.addMessage("Yuck.");
            if (invincible == false){
                int damage = strength - gp.monster[i].defense;

                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageEntity(int i){
        if (i != 999){
            if (gp.monster[i] != null  && !gp.monster[i].invincible){
                type = 2;
                int damage = this.strength - gp.monster[i].defense;
                if (damage < 0){
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].actionLockCounter = 0;
                direction = gp.monster[i].direction;

                if (gp.monster[i].life <= 0){
                    gp.monster[i].isDying = true;
                    exp += gp.monster[i].exp * ((double) gp.monster[i].level/2);
                    gp.ui.addMessage(getName() + " killed the " + gp.monster[i].name + "!");
                    checkLevelUp();
                }
            }
            else if (gp.npc[i] != null  && !gp.npc[i].invincible){
                gp.npc[i].type = 2;
                System.out.println(name + " gets mad.");
                int damage = this.strength - gp.npc[i].defense;
                if (damage < 0){
                    damage = 0;
                }
                gp.npc[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.npc[i].invincible = true;
                gp.npc[i].actionLockCounter = 0;
                direction = gp.npc[i].direction;

                if (gp.npc[i].life <= 0){
                    gp.npc[i].isDying = true;
                    exp += gp.npc[i].exp * ((double) gp.npc[i].level /2);
                    gp.ui.addMessage(getName() + " killed the " + gp.npc[i].name + "!");
                    checkLevelUp();
                }
            }
        }
    }

    public void displayEntityStats(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

//        g2.setColor(new Color(172, 69, 69));
//        g2.fillRect(screenX, screenY , attackArea.width, attackArea.height);

        if (type == 2 && hpBarOn && gp.gameState == gp.battleState ){

            double oneScale = (double) gp.tileSize/maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(0, 0,0));
            g2.fillRect(screenX - 1, screenY - solidArea.y, gp.tileSize+2, 12);

            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX, screenY - solidArea.y, (int) hpBarValue, 11);

            hpBarCounter++;

            if (hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

    }

    public void checkLevelUp(){
        while (exp >= nextLevelExp){
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            strength++;
            defense++;
            if (this.level % 3 == 0){
                this.speed++;
                gp.ui.addMessage(this.name + " speed increased!");
            }

            gp.ui.addMessage(name + " has levelled up! (Lvl " + level + ")");
        }
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 120;

        if (dyingCounter <= i){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*2){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*3){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*4){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*5){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*6){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*7){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i){
            isDying = false;
            isAlive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image1 = null;

        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image1 = uTool.scaleImage(image1, width, height);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image1;
    }

    public void draw(Graphics2D g2){
        BufferedImage image1 = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            switch (direction) {
                case "up": {
                    if (spriteNum == 1){
                        image1 = up1;
                    }
                    if (spriteNum == 2) {
                        image1 = up2;
                    }
                    break;
                }
                case "down": {
                    if (spriteNum == 1){
                        image1 = down1;
                    }
                    if (spriteNum == 2) {
                        image1 = down2;
                    }
                    break;
                }
                case "left": {
                    if (spriteNum == 1){
                        image1 = left1;
                    }
                    if (spriteNum == 2) {
                        image1 = left2;
                    }
                    break;
                }
                case "right": {
                    if (spriteNum == 1){
                        image1 = right1;
                    }
                    if (spriteNum == 2) {
                        image1 = right2;
                    }
                    break;
                }
            }
        }
        displayEntityStats(g2);

        if (invincible){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }
        if (isDying){
            dyingAnimation(g2);
        }

        g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize,  null);

        changeAlpha(g2,1f);
    }

    public String toString() {
        return null;
    }
}
