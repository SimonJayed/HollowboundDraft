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
    private String gender;
    private String race;
    public int worldX, worldY;
    public int speed;
    public int strength;
    public int defense;
    public int stamina = 100;
    public int exp = 1;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackLeft1, attackLeft2, attackLeft3, attackRight1, attackRight2, attackRight3;
    public BufferedImage runUp1, runUp2, runUp3, runDown1, runDown2, runDown3, runLeft1, runLeft2, runLeft3, runRight1, runRight2, runRight3;
    public BufferedImage image1, image2, image3;

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
//    public boolean isDashing = false;
    public boolean isRunning = false;
    public boolean isAlive = true;
    public boolean isDying = false;
    public boolean hpBarOn = true;

    public int buffer = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int collideCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int insultBuffer = 0;
    public String currentInsult = "Tch";
    public int insultDuration = -1;
    public int canInsult = 0;
    public int calmCounter = 0;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp){
        this.gp = gp;
        level = gp.randomize(1, 20);
        direction = gp.randomName("res/text/names/directions/directions");
        name = gp.randomName("res/text/names/namesAll.txt");
    }

    public String getName() {return name;}
    public String getRace() {return race;}
    public String getGender() {return gender;}

    public void setName(String name) {this.name =  name;}
    public void setRace(String race) {this.race =  race;}
    public void setGender(String gender) {this.gender =  gender;}


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
        if (gp.cChecker.checkEntity(this, gp.npc) != 999 ||
                gp.cChecker.checkPlayer(this) ||
                gp.cChecker.checkEntity(this, gp.monster) != 999) {
            buffer++;


            if (buffer >= gp.randomize(300, 1200) / level) {
                collideCounter++;
                buffer = 0;
                System.out.println(name + " Collide Counter: " + collideCounter);
            }
        }


        if (collideCounter >= 5) {
            type = 2;
            System.out.println(name + " gets mad.");
            collideCounter = 0;
        }
    }

    public void setAction(){}

    public void update(){
        if (isAttacking) {
            attacking();
        }
        setAction();


        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if (!gp.player.invincible){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        if (!collisionOn) {
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

    public void attacking(){
        System.out.println(name + " attacks.");
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

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
            isAttacking = false;
        }
    }

    public void running(){
        if (isRunning) {
            spriteCounter++;
            stamina--;
            int i = 13;
            if (spriteCounter <= i){
                spriteNum = 1;
            }
            else if (spriteCounter <= i*2){
                spriteNum = 2;
            }
            else if (spriteCounter <= i*3){
                spriteNum = 3;
            }
            else if (spriteCounter <= i*4){
                spriteNum = 2;
            }
            else{
                spriteNum = 1;
                spriteCounter = 0;
            }

        }
    }
//
//    public void displayStamina(Graphics2D g2){
//
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        double oneScale = (double) gp.tileSize/stamina;
//        double staminaBarValue = oneScale * stamina;
//
//        if (isRunning){
//            g2.setColor(new Color(35,35,35));
//            g2.fillRect(screenX - 1, screenY + solidArea.y*2, gp.tileSize+2, 16);
//
//            g2.setColor(new Color(34, 123, 219, 180));
//            g2.fillRect(screenX, screenY + solidArea.y*2, (int) staminaBarValue, 14);
//        }
//
//    }

    public void getAttackImage() {
    }


    public void interactNPC(int i){
        if (isAttacking){
            if (i != 999 ){

                if (!gp.npc[i].invincible){
                    gp.npc[i].life -= 1;
                    gp.npc[i].invincible = true;
                    gp.npc[i].damageReaction();

                    if (gp.npc[i].life <= 0){
                        gp.npc[i].isDying = true;
                        exp += gp.npc[i].exp;
                    }
                }
            }
            else{
                isAttacking = true;
            }

        }
    }

    public void contactMonster(int i){

        if (i != 999){
            gp.ui.addMessage("Yuck.");
            if (invincible == false){
                int damage = strength - defense;

                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if (i != 999){
            if (!gp.monster[i].invincible){

                int damage = strength - gp.monster[i].defense;
                if (damage < 0){
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0){
                    gp.monster[i].isDying = true;
                    exp += gp.monster[i].exp;
                    gp.ui.addMessage(getName() + "killed the " + gp.monster[i].name + "!");
                }
            }
        }
    }


    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void displayInsults(Graphics2D g2){
        if(canInsult > 4){
            insultBuffer++;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            FontMetrics metrics = g2.getFontMetrics();
            int nameWidth = metrics.stringWidth(currentInsult);
            int centeredX = screenX+21 - nameWidth / 2;

            if (type == 2) {
                if (insultDuration == -1) {
                    insultDuration = gp.randomize(120, 450);
                }

                if (insultBuffer >= insultDuration) {
                    if (currentInsult.equals("Tch")) {
                        currentInsult = gp.randomName("res/text/names/insults/insults");
                    }

                    g2.setFont(g2.getFont().deriveFont(22f));
                    g2.setColor(new Color(255, 36, 36));
                    g2.drawString(currentInsult, centeredX, screenY - (solidArea.y *2) - 15);


                    if (insultBuffer >= insultDuration + gp.randomize(450, 750)) {
                        insultBuffer = 0;
                        currentInsult = "Tch";
                        insultDuration = -1;
                    }
                }
            }
        }

    }


    public void displayEntityStats(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        g2.setColor(new Color(157, 128, 0));
//        g2.fillRect(screenX, screenY , solidArea.width, solidArea.height);
//
//        g2.setColor(new Color(172, 69, 69));
//        g2.fillRect(screenX, screenY , attackArea.width, attackArea.height);

        if (type == 2 && hpBarOn){


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

        FontMetrics metrics = g2.getFontMetrics();
        int nameWidth = metrics.stringWidth(name);
        int centeredX = screenX+21 - nameWidth / 2;


        boolean isInstanceOfObj = false;

        for (Entity objEntity : gp.obj) {
            if (this.getClass().isInstance(objEntity)) {
                isInstanceOfObj = true;
                break;
            }
        }

        if (!isInstanceOfObj) {
            g2.setColor(new Color(35,35,35));
            g2.setFont(g2.getFont().deriveFont(23f));
            g2.drawString(name, centeredX-2, screenY - (solidArea.y +16));

            if (type == 2) {
                g2.setColor(Color.red);
            } else if (type == 1){
                g2.setColor(Color.white);
            }
            g2.setFont(g2.getFont().deriveFont(22f));

            g2.drawString(name, centeredX, screenY - (solidArea.y +15));
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
            image1 = uTool.scaleImage(image1, width+level/2, height+level/2);
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
        collideEntity();
        displayInsults(g2);

        if (invincible){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }
        if (isDying){
            dyingAnimation(g2);
        }

//        if (isRunning){
//            displayStamina(g2);
//        }
        g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize,  null);

        changeAlpha(g2,1f);
    }

    public String toString() {
        return null;
    }
}
