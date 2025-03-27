package entity;

import main.GamePanel;

public class NPC_Amaryllis extends Entity {

    public NPC_Amaryllis(GamePanel gp) {
        super(gp);

        type = 2;

        setName("Amaryllis");

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        getImage("amaryllis");
        setDefaultValues(1, 250, 250,5,5, 8, 10, 13, 9);
        setDialogue();
    }

    public void setStatIncrements(){
        this.vit += 1;
        this.pow += 1;
        this.mag += 2;
        this.agi += 3;
    }

//    public void checkLevelUp(){
//        while (exp >= nextLevelExp) {
//            level++;
//            setStatIncrements();
//            calculateStats();
//            nextLevelExp += nextLevelExp + (level * 2);
//            gp.ui.addMessage(getName() + " has leveled up! (Lvl " + level + ")");
//        }
//    }

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
//        if (gp.event.eventNum == 0) {
//            System.out.println(getName() + " Dialogues added.");
//            System.out.println("LOLDialogue size: " + gp.event.dialogues.size());
//            gp.event.dialogues.add("Lol, Lmao, lmfao, rofl,, Trial Rani.");
////            gp.event.dialogues.add("Hello, bum.");
//        }
    }

    public void speak(){
        super.speak();
    }
}


