package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_Fort extends Entity {

    public NPC_Fort(GamePanel gp) {
        super(gp);

        type = 2;
        setName("Fort");
        speed = 1;
        maxLife = 10;
        life = maxLife;

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        getImage("fort");
        setDialogue();
    }


    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 750)) {
            int i = gp.randomize(1, 150);

            if (i <= 25) {
                direction = "up";
                isIdling = false;
            } else if (i <= 50) {
                direction = "down";
                isIdling = false;
            } else if (i <= 75) {
                direction = "left";
                isIdling = false;
            } else if (i <= 100) {
                direction = "right";
                isIdling = false;
            } else if (i <= 125) {
                idling();
            }
            actionLockCounter = 0;
        }
        spriteAnim(2);
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


