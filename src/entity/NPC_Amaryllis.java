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
//        System.out.println(getName() + " moves but has been defeated is " + isDefeated );
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 500)) {
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


