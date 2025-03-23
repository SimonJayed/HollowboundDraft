package entity;

import main.GamePanel;
import misc.QuestGiver;


public class NPC_OldMan extends Entity implements QuestGiver {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        type = 2;

        setName("Lars");

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        getImage("lars");
        setDialogue();
        setDefaultValues(1, 500, 700,2, 15, 5, 15, 10,  5);
    }

    public void setStatIncrements(){
        this.vit += 3;
        this.pow += 3;
        this.mag += 3;
        this.agi += 3;
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter >= gp.randomize(120, 1500)) {
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
        dialogues[0] = "You think it favors you? Ha... \nIt only watches.";
        dialogues[1] = "I had everything once... Then \nit yawned.";
        dialogues[2] = "Don't bore it... Or do. Maybe it'll \nbe kinder to you.";
    }

    @Override
    public void giveQuest() {

    }
}
