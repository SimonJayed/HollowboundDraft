package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Entity {
    public GamePanel gp;

    public int level = 1;
    public String name;
    public String gender;
    public String race;
    public int worldX, worldY;
    public int speed;
    public int strength;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackLeft1, attackLeft2, attackLeft3, attackRight1, attackRight2, attackRight3;
    public BufferedImage runUp1, runUp2, runUp3, runDown1, runDown2, runDown3, runLeft1, runLeft2, runLeft3, runRight1, runRight2, runRight3;
    public BufferedImage image1, image2, image3;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 48, 48);
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int type;


    public String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public boolean collision = false;
    public boolean invincible = false;
    public boolean collisionOn = false;
    public boolean attacking = false;
    public boolean dashing = false;
    public boolean running = false;
    public boolean isAlive = true;
    public boolean isDying = false;
    public boolean hpBarOn = true;

    public int buffer = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int collideCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void speak(){
        if (type != 2){
            if (dialogues[dialogueIndex] == null){
                dialogueIndex = 0;
            }
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;

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
    }

    public void collideEntity() {
        if (gp.cChecker.checkEntity(this, gp.npc) != 999 || gp.cChecker.checkPlayer(this) == true || gp.cChecker.checkEntity(this, gp.monster) != 999){
            buffer++;


            if (buffer >= 60) {
                collideCounter++;
                buffer = 0;
                System.out.println(name + " Collide Counter: " + collideCounter);
            }
        }

        if (collideCounter <= 5) {
//            gp.ui.showMessage("Dude");
        } else if (collideCounter <= 20) {
            if (collideCounter >= 5 && collideCounter <= 10) {
//                gp.ui.showMessage("This is the last warning.");
            } else if (collideCounter >= 15) {
//                gp.ui.showMessage("...");
            } else {
//                gp.ui.showMessage("Dude, stop.");
            }
        } else {
            collideCounter = 25;
            gp.ui.showMessage("I'M GONNA TOUCH YOU!!");
            type = 2;
        }
    }

    public void setAction(){

    }

    public void update(){
        if (attacking) {
            attacking();
        }
        setAction();
        if (gp.cChecker.checkEntity(this, gp.npc) != 999 || gp.player.collisionOn){
            collideEntity();
        }


        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        if (collisionOn == false) {
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

        if (invincible == true){
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

    public void attacking(){
        spriteCounter++;

        spriteCounter++;
        int i = 13;
        if (spriteCounter <= i){
            spriteNum = 1;
        }
        else if (spriteCounter <= i*2){
            spriteNum = 2;
        }

        else if (spriteCounter <= i*3){
            spriteNum = 3;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up":{
                    worldY -= attackArea.height;
                    break;
                }
                case "down":{
                    worldY += attackArea.height;
                    break;
                }
                case "left":{
                    worldY -= attackArea.width;
                    break;
                }
                case "right":{
                    worldX += attackArea.width;
                    break;
                }
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        else{
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }


    public void interactNPC(int i){
        if (gp.keyH.enterPressed == true ){
            if (i != 999 ){
                if(gp.npc[i].type != 2) {
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                }
                else{
                    if (gp.npc[i].invincible == false){
                        gp.npc[i].life -= 1;
                        gp.npc[i].invincible = true;
                        gp.npc[i].damageReaction();

                        if (gp.npc[i].life <= 0){
                            gp.npc[i].isDying = true;
                        }
                    }
                }
            }
            else{
                attacking = true;
            }

        }
    }

    public void contactMonster(int i){

        if (i != 999){
            gp.ui.showMessage("Yuck.");
//            if (invincible == false){
//                life -= 1;
//                invincible = true;
//            }
        }
    }

    public void damageMonster(int i){
        if (i != 999){
            if (gp.monster[i].invincible == false){
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0){
                    gp.monster[i].isDying = true;
                }
            }
        }
    }


    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
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
        if (type == 2 && hpBarOn == true){

            double oneScale = (double) gp.tileSize/maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX - 1, screenY + 3, gp.tileSize+2, 16);

            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX, screenY + 3, (int) hpBarValue, 14);

            hpBarCounter++;

            if (hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        FontMetrics metrics = g2.getFontMetrics();
        int nameWidth = metrics.stringWidth(name);
        int centeredX = screenX+21 - nameWidth / 2;


        if (this != null) {
            boolean isInstanceOfObj = false;

            for (Entity objEntity : gp.obj) {
                if (objEntity != null && this.getClass().isInstance(objEntity)) {
                    isInstanceOfObj = true;
                    break;
                }
            }



            if (!isInstanceOfObj) {
                g2.setColor(new Color(35,35,35));
                g2.setFont(g2.getFont().deriveFont(23f));
                g2.drawString(name, centeredX-2, screenY+1);

                if (type == 2) {
                    g2.setColor(Color.red);
                } else if (type == 1){
                    g2.setColor(Color.white);
                }
                g2.setFont(g2.getFont().deriveFont(22f));

                g2.drawString(name, centeredX, screenY);
            }
        }

        if (invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }
        if (isDying == true){
            dyingAnimation(g2);
        }
        g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize,  null);

        changeAlpha(g2,1f);
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 40;

        if (dyingCounter <= i){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i && dyingCounter <= i*2){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*2 && dyingCounter <= i*3){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*3 && dyingCounter <= i*4){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*4 && dyingCounter <= i*5){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*5 && dyingCounter <= i*6){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i*6 && dyingCounter <= i*7){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter <= i*7 && dyingCounter <= i*8){
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
            image1 = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image1 = uTool.scaleImage(image1, width+level/2, height+level/2);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image1;
    }
//    public BufferedImage setup(String imagePath, int width, int height){
//        UtilityTool uTool = new UtilityTool();
//        BufferedImage image1 = null;
//
//        try{
//            image1 = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
//            image1 = uTool.scaleImage(image1, width, height);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        return image1;
//    }

    public Entity(String name){
        this.name = name;
    }

    public String toString() {
        return null;
    }

    public static class Human extends Entity{
        public Human(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "CHAEWON NUMBER ONE!!";
        }
    }

    public static class Compy extends Entity{
        public Compy(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Compy mwew mwew krek krek!!";
        }
    }
    public static class Coelacanth extends Entity{
        public Coelacanth(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Silikent blop blop chaewonxsimon!!";
        }
    }
    public static class Pterosaur extends Entity{
        public Pterosaur(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Ptero qwak qwak cak!! cok?";
        }
    }
}
