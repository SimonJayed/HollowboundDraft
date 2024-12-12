package entity.race;

import entity.Entity;
import main.GamePanel;

import javax.swing.*;

public class ptero extends Entity {

    public ptero (GamePanel gp) {
        super(gp);

        type = 1;
        canInsult = 2;

        setGender(gp.randomName("res/text/names/genders/genders"));
        setRace("Pterosaur");
        try{
            if (getGender().equals("Male")){setName(gp.randomName("res/text/names/namesMale.txt"));}
            else if (getGender().equals("Female")){setName(gp.randomName("res/text/names/namesFemale.txt"));}
            else{setName(gp.randomName("res/text/names/namesAll.txt"));}
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "File Missing.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        speed = 1;
        maxLife = 5;
        life = maxLife;


        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {
        up1 = setup("/player/pterosaur/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/pterosaur/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/pterosaur/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/pterosaur/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/pterosaur/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/pterosaur/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/pterosaur/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/pterosaur/right2", gp.tileSize, gp.tileSize);
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



