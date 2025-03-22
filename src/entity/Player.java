package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;


import java.awt.*;

import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyH;
    MouseHandler mouseH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public String playing = "";

    public int StatPoints = 0;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH){
        super(gp);
        this.keyH = keyH;
        this.mouseH = mouseH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        attackArea.width = 36;
        attackArea.height = 36;

        solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        buffer = 0;
    }


    public void setDefaultValues(){
        worldX = gp.tileSize * 18;
        worldY = gp.tileSize * 47;
        direction = "down";

        switch(playing){
            case "fort":{
                setName("Fort");
                getImage("fort");
                setDefaultValues(1, 400, 100,4, 15, 6, 4, 5,  15);
                break;
            }
            case "amaryllis":{
                setName("Amaryllis");
                getImage("amaryllis");
                setDefaultValues(1, 250, 250,5,5, 8, 10, 13, 9);
                break;
            }
            case "sylvie":{
                setName("Sylvie");
                getImage("sylvie");
                setDefaultValues(1, 100, 400,3, 7, 5, 19, 5,  9);
                break;
            }
        }
    }


    public void update() {
        if (!keyH.upPressed && !keyH.downPressed && !keyH.rightPressed && !keyH.leftPressed){
            isIdling = true;
            isRunning = false;
        }
        regen();
        checkDefeated();
        checkLevelUp();
        if (keyH.qPressed) {
            System.out.println("Q is pressed...");
        }

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
//            System.out.println(getName() + " moves but has been defeated is " + isDefeated );
            if (gp.keyH.upPressed) {
                direction = "up";
            }
            if (gp.keyH.downPressed) {
                direction = "down";
            }
            if (gp.keyH.rightPressed) {
                direction = "right";
            }
            if (gp.keyH.leftPressed) {
                direction = "left";
            }
            if (gp.keyH.shiftPressed && energy > 0) {
                buffer++;
                isRunning = true;
//                running();
                speed++;
                if (speed >= tempSpeed + 4) {
                    speed = tempSpeed + 4;
                }
                if(buffer >= 5){
                    energy--;
                    buffer = 0;
                }
            } else {
                keyH.shiftPressed = false;
                isRunning = false;
                speed = tempSpeed;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int livingEntityIndex = gp.cChecker.checkEntity(this, gp.livingEntity);
            interactEntity(livingEntityIndex);

            gp.eHandler.checkEvent();

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
            spriteAnim(2);

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
    }

    public void checkDefeated(){
        if(isDefeated && hollowCounter < 5){
            buffer++;
            isIdling = true;
            if(buffer > 100 && !hasEvent){
                isDefeated = false;
                hp = maxHP;
                energy = maxEnergy;
                buffer = 0;
                int num = 0;
                while(num <= hollowCounter){
                    setStatIncrements();
                    calculateStats();
                    num++;
                    System.out.println(getName() + " is being strengthened.");
                }
                gp.player.worldX = 5 * gp.tileSize;
                gp.player.worldY = 9 * gp.tileSize;
                System.out.println(getName() + " has respawned.");
                System.out.println(getName() + " died " + hollowCounter + " times");
            }
        }
    }

    public void interactEntity(int i){
        if(i != 999){
            if (keyH.spacePressed){
                if(gp.livingEntity[gp.currentMap][i].type != 2 || gp.livingEntity[gp.currentMap][i].isDefeated) {
                    gp.gameState = gp.dialogueState;
                    gp.livingEntity[gp.currentMap][i].speak();
                }
                else{
                    gp.battleScreen.currentEnemy = gp.livingEntity[gp.currentMap][i];
                    gp.gameState = gp.battleState;
                }
            }
            if(keyH.qPressed){
                gp.gameState = gp.inventoryState;
                gp.ui.drawInventoryScreen(gp.livingEntity[gp.currentMap][i]);
            }
        }

    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.objectEntity[gp.currentMap][i].getName();

            switch(objectName){
                case "Key":{
                    hasKey++;
                    gp.objectEntity[gp.currentMap][i] = null;
                    gp.ui.addMessage("Obtained KEY!");
                    break;
                }
                case "Door":{
                    if (hasKey > 0) {
                        gp.objectEntity[gp.currentMap][i] = null;
                        hasKey--;
                        gp.ui.addMessage("Door opened");
                    }
                    else{
                        gp.ui.addMessage("Key required");
                    }
                    break;
                }
                case "Boots":{
                    this.speed += 1;
                    gp.objectEntity[gp.currentMap][i] = null;
                    gp.ui.addMessage("Speed increased!");
                    break;
                }
                case "Chest":{
                    gp.objectEntity[gp.currentMap][i] = null;
                    gp.ui.addMessage("Chest opened!");

                    gp.ui.gameFinished = true;
                    break;
                }
            }
        }

    }


    public void draw(Graphics2D g2){
        BufferedImage image = null;

//        displayEntityStats(g2);

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up": {
                if(!isDefeated && !isRunning){
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                else if(isRunning){
                    if (spriteNum == 1){
                        image = runUp1;
                    }
                    if (spriteNum == 2) {
                        image = runUp2;
                    }
                }
                else{
                    if (spriteNum == 1){
                        image = defeated1;
                    }
                    if (spriteNum == 2) {
                        image = defeated2;
                    }
                }
                break;
            }
            case "down": {
                if(!isDefeated && !isRunning){
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                else if(isRunning){
                    if (spriteNum == 1){
                        image = runDown1;
                    }
                    if (spriteNum == 2) {
                        image = runDown2;
                    }
                }
                else{
                    if (spriteNum == 1){
                        image = defeated1;
                    }
                    if (spriteNum == 2) {
                        image = defeated2;
                    }
                }
                break;
            }
            case "left": {
                if(!isDefeated && !isRunning){
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                else if(isRunning){
                    if (spriteNum == 1){
                        image = runLeft1;
                    }
                    if (spriteNum == 2) {
                        image = runLeft2;
                    }
                }
                else{
                    if (spriteNum == 1){
                        image = defeated1;
                    }
                    if (spriteNum == 2) {
                        image = defeated2;
                    }
                }
                break;
            }
            case "right": {
                if(!isDefeated && !isRunning){
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                else if(isRunning){
                    if (spriteNum == 1){
                        image = runRight1;
                    }
                    if (spriteNum == 2) {
                        image = runRight2;
                    }
                }
                else{
                    if (spriteNum == 1){
                        image = defeated1;
                    }
                    if (spriteNum == 2) {
                        image = defeated2;
                    }
                }
                break;
            }
        }
        if (invincible){
            changeAlpha(g2,0.4f);
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        changeAlpha(g2,1f);

    }
}
