package entity.race;

import entity.Entity;
import interfaces.Swimmer;
import main.GamePanel;

import javax.swing.*;

public class coele extends Entity  implements Swimmer {

    public coele(GamePanel gp) {
        super(gp);

        type = 1;
        canInsult = 2;

        setGender(gp.randomName("res/text/names/genders/genders"));
        setRace("Coelacanth");
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
        up1 = setup("/player/coelacanth/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/coelacanth/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/coelacanth/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/coelacanth/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/coelacanth/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/coelacanth/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/coelacanth/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/coelacanth/right2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        swim();
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

    @Override
    public void swim() {
        final boolean b1 = false;
        collisionOn = b1;
    }
}



