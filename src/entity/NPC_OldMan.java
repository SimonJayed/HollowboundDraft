package entity;

import main.GamePanel;

import java.awt.*;


public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        name = "Gary";
        speed = 1;
        maxLife = 10;
        life = maxLife;


        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidArea.width = 32;
        this.solidArea.height = 32;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        getImage();
        setDialogue();

    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= 120) {
            int i = gp.randomize(100);

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
        spriteAnim(2);
    }

    public void collideEntity(){
        super.collideEntity();
    }

    public void setDialogue(){
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "You seem lost.";
        dialogues[2] = "I can fix that.";
        dialogues[3] = "No. Seriously, dude... \nI can.";
        dialogues[4] = "Why tf does no one believe what I \nsay?";
    }

    public void speak(){
        super.speak();
    }
}
