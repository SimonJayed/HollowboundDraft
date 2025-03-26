package entity;

import main.GamePanel;

public class NPC_MamaPausy extends Entity {

    public NPC_MamaPausy(GamePanel gp) {
        super(gp);

        type = 1;

        setName("Mama Pausy");

        this.solidArea.x = 10;
        this.solidArea.y = 28;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 88;
        this.solidArea.height = 70;
        sizeIncrement = 50;

        getImage("mamapausy");
        setDefaultValues(1, 250, 250,5,5, 8, 10, 13, 9);
        setDialogue();
    }

    public void setStatIncrements(){
        this.vit += 1;
        this.pow += 1;
        this.mag += 2;
        this.agi += 3;
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 250)) {
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
                isIdling = true;
            }
            actionLockCounter = 0;
        }
        spriteAnim(2);
    }

    public void setDialogue() {
        if(gp.event.eventNum == 1){
            System.out.println(getName() + " Dialogues added.");
            gp.event.dialogues.add("MEOWR!!!");
            gp.event.dialogues.add("Meowr");
            gp.event.dialogues.add("Moewr?");
            gp.event.dialogues.add("HHIIISSS!!");
            gp.event.dialogues.add("Grrrrrrrrrr!!!");
            gp.event.dialogues.add("MEOWR!!!");
            gp.event.dialogues.add("HHIIISSS!!");
            gp.event.dialogues.add("Moewr?");
        }

    }

    public void speak(){
        super.speak();
    }
}


