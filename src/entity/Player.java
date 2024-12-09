package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Boots;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

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

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("/player/human/up1");
        up2 = setup("/player/human/up2");
        down1 = setup("/player/human/down1");
        down2 = setup("/player/human/down2");
        left1 = setup("/player/human/left1");
        left2 = setup("/player/human/left2");
        right1 = setup("/player/human/right1");
        right2 = setup("/player/human/right2");
    }



    public void update(){
        if (keyH.upPressed == true){
            direction = "up";
        }
        if (keyH.downPressed == true){
            direction = "down";
        }
        if (keyH.rightPressed == true){
            direction = "right";
        }
        if (keyH.leftPressed == true){
            direction = "left";
        }
//        if (keyH.tPressed == true) {
//
//        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        if (collisionOn == false && (keyH.downPressed || keyH.leftPressed || keyH.upPressed || keyH.rightPressed)) {
            switch (direction) {
                case "up": {
                    if(collisionOn){
                        worldY += speed;
                    }
                    else{
                        worldY -= speed;
                    }
                    break;
                }
                case "down": {
                    if(collisionOn){
                        worldY -= speed;
                    }
                    else{
                        worldY += speed;
                    }
                    break;
                }
                case "left": {
                    if(collisionOn){
                        worldX += speed;
                    }
                    else{
                        worldX -= speed;
                    }
                    break;
                }
                case "right": {
                    if(collisionOn){
                        worldX -= speed;
                    }
                    else{
                        worldX += speed;
                    }
                    break;
                }
            }
        }



        spriteCounter++;
        if (spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
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

    public void interactNPC(int i){

        if (i != 999){
            if (keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up": {
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            }
            case "down": {
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            }
            case "left": {
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            }
            case "right": {
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
