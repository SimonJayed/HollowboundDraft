package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    int tempSpeed = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerRunImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 2;
        tempSpeed = speed;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        up1 = setup("/player/human/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/human/up2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/human/up1", gp.tileSize, gp.tileSize);
        down1 = setup("/player/human/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/human/down2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/human/down1", gp.tileSize, gp.tileSize);
        left1 = setup("/player/human/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/human/left2", gp.tileSize, gp.tileSize);
        left3 = setup("/player/human/left3", gp.tileSize, gp.tileSize);
        right1 = setup("/player/human/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/human/right2", gp.tileSize, gp.tileSize);
        right3 = setup("/player/human/right3", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/human/punchUp1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/human/punchUp2", gp.tileSize, gp.tileSize*2);
        attackUp3 = setup("/player/human/punchUp3", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/human/punchDown1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/human/punchDown2", gp.tileSize, gp.tileSize*2);
        attackDown3 = setup("/player/human/punchDown1", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/human/punchLeft1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/human/punchLeft2", gp.tileSize*2, gp.tileSize);
        attackLeft3 = setup("/player/human/punchLeft3", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/human/punchRight1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/human/punchRight2", gp.tileSize*2, gp.tileSize);
        attackRight3 = setup("/player/human/punchRight3", gp.tileSize*2, gp.tileSize);
    }
    public void getPlayerRunImage(){
        runUp1 = setup("/player/human/up1", gp.tileSize, gp.tileSize);
        runUp2 = setup("/player/human/up2", gp.tileSize, gp.tileSize);
        runUp3 = setup("/player/human/up1", gp.tileSize, gp.tileSize);
        runDown1 = setup("/player/human/down1", gp.tileSize, gp.tileSize);
        runDown2 = setup("/player/human/down2", gp.tileSize, gp.tileSize);
        runDown3 = setup("/player/human/down2", gp.tileSize, gp.tileSize);
        runLeft1 = setup("/player/human/runLeft1", gp.tileSize, gp.tileSize);
        runLeft2 = setup("/player/human/runLeft2", gp.tileSize, gp.tileSize);
        runLeft3 = setup("/player/human/runLeft3", gp.tileSize, gp.tileSize);
        runRight1 = setup("/player/human/runRight1", gp.tileSize, gp.tileSize);
        runRight2 = setup("/player/human/runRight2", gp.tileSize, gp.tileSize);
        runRight3 = setup("/player/human/runRight3", gp.tileSize, gp.tileSize);
    }

    public void update(){
//        System.out.println(tempSpeed + " and " + this.speed);
        if (attacking) {
            attacking();
        }
        if (keyH.ctrPressed == true){
            running = true;
        }
        if (running == true) {
            running();
        }

        actionLockCounter++;
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true || keyH.enterPressed == true) {
            if (gp.keyH.upPressed == true) {
                direction = "up";
            }
            if (gp.keyH.downPressed == true) {
                direction = "down";
            }
            if (gp.keyH.rightPressed == true) {
                direction = "right";
            }
            if (gp.keyH.leftPressed == true) {
                direction = "left";
            }
            if (gp.keyH.shiftPressed == true) {
                this.speed = tempSpeed + 2;
            } else {
                this.speed = tempSpeed;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;
            gp.mouseH.lmbPressed = false;

            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up": {
                        if (collisionOn) {
                            worldY += speed;
                        } else {
                            worldY -= speed;
                        }
                        if (keyH.ctrPressed == true && actionLockCounter >= 300) {
                            worldY -= gp.tileSize;
                            actionLockCounter = 0;
                        }
                        break;
                    }
                    case "down": {
                        if (collisionOn) {
                            worldY -= speed;
                        } else {
                            worldY += speed;
                        }
                        if (keyH.ctrPressed == true && actionLockCounter >= 300) {
                            worldY += gp.tileSize;
                            actionLockCounter = 0;
                        }
                        break;
                    }
                    case "left": {
                        if (collisionOn) {
                            worldX += speed;
                        } else {
                            worldX -= speed;
                        }
                        if (keyH.ctrPressed == true && actionLockCounter >= 300) {
                            worldX -= gp.tileSize;
                            actionLockCounter = 0;
                        }
                        break;
                    }
                    case "right": {
                        if (collisionOn) {
                            worldX -= speed;
                        } else {
                            worldX += speed;
                        }
                        if (keyH.ctrPressed == true && actionLockCounter >= 300) {
                            worldX += gp.tileSize;
                            actionLockCounter = 0;
                        }
                        break;
                    }
                    default: {
                        direction = "down";
                    }
                }
            }

            if (actionLockCounter >= 300) {
                actionLockCounter = 300;
            }

            gp.keyH.enterPressed = false;
            gp.mouseH.lmbPressed = false;
            keyH.ctrPressed = false;

            spriteCounter++;
            int i = 13;
            if (spriteCounter <= i) {
                spriteNum = 1;
            } else if (spriteCounter <= i * 2) {
                spriteNum = 2;
            } else if (spriteCounter <= i * 3) {
                spriteNum = 3;
            } else if (spriteCounter <= i * 4) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
                spriteCounter = 0;
            }

            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }

    }


public void running(){
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
        }
        else if (spriteCounter <= i*4){
            spriteNum = 2;
        }
        else{
            spriteNum = 1;
            spriteCounter = 0;
        }
    }


    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":{
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Obtained KEY!");
                    break;
                }
                case "Door":{
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door opened");
                    }
                    else{
                        gp.ui.showMessage("Key required");
                    }
                    break;
                }
                case "Boots":{
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed increased!");
                    break;
                }
                case "Chest":{
                    gp.obj[i] = null;
                    gp.ui.showMessage("Chest opened!");

                    gp.ui.gameFinished = true;
                    break;
                }
            }
        }

    }

    public void draw(Graphics2D g2){
//        if(gp.gameState == 1){
//            System.out.println("Direction: " + direction + " | SpriteNum: " + spriteNum + " | SpriteCounter: " + spriteCounter);
//
//        }
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;


        switch (direction) {
            case "up": {
                if (attacking == false && running == false){
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                }
                if (attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1){
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                    if (spriteNum == 3) {
                        image = attackUp3;
                    }
                }
                if (running == true){
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up1;
                    }
                }
                break;
            }
            case "down": {
                if (attacking == false && running == false){
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                }
                if (attacking == true){
                    if (spriteNum == 1){
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                    if (spriteNum == 3) {
                        image = attackDown3;
                    }
                }
                if (running == true){
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down1;
                    }
                }
                break;
            }
            case "left": {
                if (attacking == false){
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                }
                if (attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1){
                        image = attackLeft3;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                    if (spriteNum == 3) {
                        image = attackLeft3;
                    }
                }
                if (running == true){
                    if (spriteNum == 1){
                        image = runLeft1;
                    }
                    if (spriteNum == 2) {
                        image = runLeft2;
                    }
                    if (spriteNum == 3) {
                        image = runLeft3;
                    }
                }
                break;
            }
            case "right": {
                if (attacking == false && running == false){
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                }
                if (attacking == true && running == false){
                    if (spriteNum == 1){
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                    if (spriteNum == 3) {
                        image = attackRight3;
                    }
                }
                if (running == true){
                    if (spriteNum == 1){
                        image = runRight1;
                    }
                    if (spriteNum == 2) {
                        image = runRight2;
                    }
                    if (spriteNum == 3) {
                        image = runRight3;
                    }
                }
                break;
            }
        }
        if (invincible == true){
            changeAlpha(g2,0.4f);
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        changeAlpha(g2,1f);
    }
}
