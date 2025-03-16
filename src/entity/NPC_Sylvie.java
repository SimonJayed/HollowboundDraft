package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_Sylvie extends Entity {

    public NPC_Sylvie(GamePanel gp) {
        super(gp);

        setName("Sylvie");
        speed = 5;
        maxLife = 10;
        life = maxLife;

        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage("sylvie");
        setDialogue();
    }

    public void getImage(String folder) {
        up1 = setup("/" + folder + "/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/" + folder + "/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/" + folder + "/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/" + folder + "/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/" + folder + "/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/" + folder + "/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/" + folder + "/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/" + folder + "/right2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 350)) {
            int i = gp.randomize(1, 100);

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else if (i <= 100) {
                direction = "right";
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


