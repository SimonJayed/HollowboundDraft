package entity;

import main.GamePanel;
import misc.QuestGiver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class NPC_OldMan extends Entity implements QuestGiver {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        type = 2;

        setName("Lars");
        speed = 1;
        maxLife = 10;
        life = maxLife;
        exp = 58*2;
        checkLevelUp();

        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

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
        idling();
        spriteAnim(2);
    }

    public void setDialogue(){
        dialogues[0] = "You think it favors you? Ha... \nIt only watches.";
        dialogues[1] = "I had everything once... Then \nit yawned.";
        dialogues[2] = "Don't bore it... Or do. Maybe it'll \nbe kinder to you.";
    }

    public void speak(){
        super.speak();
    }

    @Override
    public void giveQuest() {

    }
}
