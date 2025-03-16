package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        setName("Lars");
        speed = 1;
        maxLife = 10;
        life = maxLife;
        exp = 58*2;
        checkLevelUp();

        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();

        try {
            portrait = ImageIO.read(getClass().getResourceAsStream("/graphics/NPC_OldMan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
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
        dialogues[0] = "You think it favors you? Ha... \nIt only watches.";
        dialogues[1] = "I had everything once... Then \nit yawned.";
        dialogues[2] = "Don't bore it... Or do. Maybe it'll \nbe kinder to you.";
    }

    public void speak(){
        super.speak();
        gp.playSoundEffect(1);
    }
}
