package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;


import java.awt.*;

import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyH;
    MouseHandler mouseH;

//    private String name = gp.randomName("res/text/names/namesAll.txt");
//    private String gender = gp.randomName("res/text/names/genders/genders");
//    private String race = gp.randomName("res/text/names/races/races");

    private String name = "Bob";
    private String gender = "Male";
    private String race = "Human";


    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    int tempSpeed = 0;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH){
        super(gp);
        this.keyH = keyH;
        this.mouseH = mouseH;

        level = 1;
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

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerRunImage();

    }


    public void setDefaultValues(){
        worldX = gp.tileSize * 30;
        worldY = gp.tileSize * 15;
        speed = 1;
        tempSpeed = speed;
        direction = "down";

        maxLife = 2;
        life = maxLife;
    }

    public void setName(String name) {
       this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getRace() {
        return race;
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
    public void getPlayerAttackImage() {
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
    public void getPlayerRunImage(){
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


    public void update() {
//        System.out.println(tempSpeed + " and " + this.speed + " and " + gp.gameState + " and " + getName() + " and " + getRace() + " and " + getGender() );
        if (keyH.enterPressed || mouseH.lmbPressed) {
            isAttacking = true;
            attacking();
        }
        else{
            isAttacking = false;
        }
        if (!keyH.upPressed && !keyH.downPressed && !keyH.rightPressed && !keyH.leftPressed){
            isIdling = true;
            idling();
        }

        if (keyH.zeroPressed){
            gp.setFPS(5);
            System.out.println("Bullet time");
        }
        else{
            gp.setFPS(60);
        }
//
//        if (keyH.tabPressed) {
//            System.out.println("Tab is pressed...");
//        }
//        if (keyH.shiftPressed) {
//            System.out.println("Shift is pressed...");
//        }
//        if (keyH.enterPressed) {
//            System.out.println("Enter is pressed...");
//        }
        if (keyH.mPressed) {
            stopAll();
            System.out.println("M is pressed...");
            if (gp.statWindow == null) {
                System.out.println("Creating StatWindow...");
                gp.toggleStatWindow(this);
                gp.statWindow.setVisible(true);
                gp.setFocusable(true);
            }
            else{
                gp.statWindow.dispose();
                gp.statWindow = null;
            }
            keyH.mPressed = false;
        }

        if (keyH.tabPressed) {
            System.out.println("Tab is pressed...");
        }

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
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
            if (gp.keyH.shiftPressed) {
                isRunning = true;
//                running();
                speed++;
                if (speed >= tempSpeed + 4) {
                    speed = tempSpeed + 4;
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

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactEntity(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


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

//            gp.keyH.mPressed = false;
//            gp.keyH.qPressed = false;
//            gp.keyH.enterPressed = false;
//            keyH.ctrPressed = false;

            if (race.equals("human")) {
                spriteAnim(3);
            } else {
                spriteAnim(2);
            }

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
    }

    public void stopAll () {
        isAttacking = false;
        isRunning = false;
        if (gp.statWindow != null) {
            gp.statWindow.dispose();
            gp.statWindow = null;
        }
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }


//    public void running(){
//        if ( keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
//            super.running();
//        }
//    }

    public void idling(){
        isAttacking = false;
        isRunning = false;
        isIdling = false;
    }

    public void interactEntity(int i){
            if (i != 999){
                if (keyH.qPressed) {
                    if (gp.npc[i] != null) {
                        stopAll();
                        System.out.println("q is pressed...");

                        System.out.println("Creating StatWindow...");
                        gp.toggleStatWindow(gp.npc[i]);
                        gp.statWindow.setVisible(true);
                        gp.setFocusable(true);
                    }
                    else if (gp.monster[i] != null){
                        stopAll();
                        System.out.println("q is pressed...");

                        System.out.println("Creating StatWindow...");
                        gp.toggleStatWindow(gp.monster[i]);
                        gp.statWindow.setVisible(true);
                        gp.setFocusable(true);
                    }
                }

                if (keyH.spacePressed){
                    if(gp.npc[i].type != 2) {
                        stopAll();
                        gp.gameState = gp.dialogueState;
                        gp.npc[i].speak();
                    }
                }
                keyH.qPressed = false;
        }
    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.obj[i].getName();

            switch(objectName){
                case "Key":{
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.addMessage("Obtained KEY!");
                    break;
                }
                case "Door":{
                    if (hasKey > 0) {
                        gp.obj[i] = null;
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
                    gp.obj[i] = null;
                    gp.ui.addMessage("Speed increased!");
                    break;
                }
                case "Chest":{
                    gp.obj[i] = null;
                    gp.ui.addMessage("Chest opened!");

                    gp.ui.gameFinished = true;
                    break;
                }
            }
        }

    }


    public void draw(Graphics2D g2){
//      super.draw(g2);
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up": {
                if (!isAttacking && !isRunning) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                }
                if (isAttacking){
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
                if (isRunning){
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
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                if (isAttacking){
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
                if (isRunning){
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
                break;
            }
            case "left": {
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                if (isAttacking){
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
                if (isRunning){
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
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                if (isAttacking && !isRunning){
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
                if (isRunning){
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
        if (invincible){
            changeAlpha(g2,0.4f);
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        changeAlpha(g2,1f);

    }
}
