package entity;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_Human extends Entity {

    public NPC_Human(GamePanel gp) {
        super(gp);

        type = gp.randomize(1, 2);
        name = "Gary";
        speed = 1;
        maxLife = 7;
        life = maxLife;

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidArea.width = 32;
        this.solidArea.height = 32;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {
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

    public void getAttackImage() {
        attackUp1 = setup("/player/human/punchUp1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/human/punchUp2", gp.tileSize, gp.tileSize * 2);
        attackUp3 = setup("/player/human/punchUp3", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/human/punchDown1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/human/punchDown2", gp.tileSize, gp.tileSize * 2);
        attackDown3 = setup("/player/human/punchDown1", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/human/punchLeft1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/human/punchLeft2", gp.tileSize * 2, gp.tileSize);
        attackLeft3 = setup("/player/human/punchLeft3", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/human/punchRight1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/human/punchRight2", gp.tileSize * 2, gp.tileSize);
        attackRight3 = setup("/player/human/punchRight3", gp.tileSize * 2, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 350)) {
            int i = gp.randomize(1, 100);

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
        collideEntity();
        if (collisionOn){
            if (gp.cChecker.checkEntity(this, gp.npc) != 999 || gp.cChecker.checkPlayer(this) == true){
                if (type == 2){
                    attacking = true;
                }
            }
            else if (gp.cChecker.checkEntity(this, gp.monster) != 999){
                attacking = true;
            }
        }
        else{
            attacking = false;
        }
        spriteAnim(3);
    }

    public void collideEntity() {
        super.collideEntity();
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "You seem lost.";
        dialogues[2] = "I can fix that.";
        dialogues[3] = "No. Seriously, dude... \nI can.";
        dialogues[4] = "Why tf does no one believe what I \nsay?";
    }

    public void speak() {
        super.speak();
    }

    public void draw(Graphics2D g2) {
//        if(gp.gameState == 1){
//            System.out.println("Direction: " + direction + " | SpriteNum: " + spriteNum + " | SpriteCounter: " + spriteCounter);
//
//        }
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Check if the NPC is within the visible screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {


            switch (direction) {
                case "up": {
                    if (attacking == false && running == false) {
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
                    if (attacking == true) {
                        screenY -= gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                        if (spriteNum == 3) {
                            image = attackUp3;
                        }
                    }
                    if (running == true) {
                        if (spriteNum == 1) {
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
                    if (attacking == false && running == false) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if (spriteNum == 3) {
                            image = down3;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                        if (spriteNum == 3) {
                            image = attackDown3;
                        }
                    }
                    if (running == true) {
                        if (spriteNum == 1) {
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
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if (spriteNum == 3) {
                            image = left3;
                        }
                    }
                    if (attacking == true) {
                        screenX -= gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackLeft3;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                        if (spriteNum == 3) {
                            image = attackLeft3;
                        }
                    }
                    if (running == true) {
                        if (spriteNum == 1) {
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
                    if (attacking == false && running == false) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if (spriteNum == 3) {
                            image = right3;
                        }
                    }
                    if (attacking == true && running == false) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                        if (spriteNum == 3) {
                            image = attackRight3;
                        }
                    }
                    if (running == true) {
                        if (spriteNum == 1) {
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
            if (invincible == true) {
                changeAlpha(g2, 0.4f);
            }
            g2.drawImage(image, screenX, screenY, null);

            changeAlpha(g2, 1f);
        }
    }
}



