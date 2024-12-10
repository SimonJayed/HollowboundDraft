package entity;

import main.GamePanel;

public class NPC_Human extends Entity {

    public NPC_Human(GamePanel gp) {
        super(gp);

        name = "Guy";
        speed = 1;
        maxLife = 7;
        life = maxLife;

        getImage();
        setDialogue();

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidArea.width = 32;
        this.solidArea.height = 32;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }

    public void getImage() {
        up1 = setup("/player/human/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/human/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/human/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/human/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/human/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/human/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/human/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/human/right2", gp.tileSize, gp.tileSize);
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
    }

    public void collideEntity(){
        if (collisionOn || gp.player.collisionOn){
            buffer++;


            if (buffer >= 60) {
                collideCounter++;
                buffer = 0;
                System.out.println("Human Collide Counter: " + collideCounter);
            }
        }

        if (collideCounter <= 5) {
            gp.ui.showMessage("Dude", worldX, worldY);
        } else if (collideCounter <= 20) {
            if (collideCounter >= 5 && collideCounter <= 10) {
                gp.ui.showMessage("This is the last warning.", worldX, worldY);
            } else if (collideCounter >= 15) {
                gp.ui.showMessage("...", worldX, worldY);
            } else {
                gp.ui.showMessage("Dude, stop.", worldX, worldY);
            }
        } else {
            collideCounter = 25;
            gp.ui.showMessage("I'M GONNA TOUCH YOU!!", worldX, worldY);
        }
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
