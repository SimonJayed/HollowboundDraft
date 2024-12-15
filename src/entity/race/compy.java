package entity.race;

import entity.Entity;
import main.GamePanel;

import javax.swing.*;

public class compy extends Entity {

    public compy(GamePanel gp) {
        super(gp);

        type = 1;
        setGender(gp.randomName("res/text/names/genders/genders"));
        setRace("Compy");
        setGender(gp.randomName("res/text/names/genders/genders"));
        try{
            if (getGender().equals("Male")){setName(gp.randomName("res/text/names/namesMale.txt"));}
            else if (getGender().equals("Female")){setName(gp.randomName("res/text/names/namesFemale.txt"));}
            else{setName(gp.randomName("res/text/names/namesAll.txt"));}
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Name File Missing.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        speed = 1;
        maxLife = 5;
        life = maxLife;

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        getImage();

    }

    public void getImage() {
        up1 = setup("/player/compy/up1", gp.tileSize/2, gp.tileSize/2);
        up2 = setup("/player/compy/up2", gp.tileSize/2, gp.tileSize/2);
        down1 = setup("/player/compy/down1", gp.tileSize/2, gp.tileSize/2);
        down2 = setup("/player/compy/down2", gp.tileSize/2, gp.tileSize/2);
        left1 = setup("/player/compy/left1", gp.tileSize/2, gp.tileSize/2);
        left2 = setup("/player/compy/left2", gp.tileSize/2, gp.tileSize/2);
        right1 = setup("/player/compy/right1", gp.tileSize/2, gp.tileSize/2);
        right2 = setup("/player/compy/right2", gp.tileSize/2, gp.tileSize/2);
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
    }

    public void speak(){
        super.speak();
    }
}

