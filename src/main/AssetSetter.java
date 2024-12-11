package main;

import entity.NPC_Dino;
import entity.NPC_Human;
import entity.NPC_OldMan;
import entity.NPC_Stalker;
import entity.monster.MON_GreenSlime;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Boots(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Lipoden(gp);
        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY = 24 * gp.tileSize;

        gp.obj[2] = new OBJ_Lipoden(gp);
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_Human(gp);
        gp.npc[0].worldX = gp.randomize(24, 39) * gp.tileSize;
        gp.npc[0].worldY = gp.randomize(21, 25) * gp.tileSize;

        gp.npc[1] = new NPC_Human(gp);
        gp.npc[1].worldX = gp.randomize(24, 39) * gp.tileSize;
        gp.npc[1].worldY = gp.randomize(21, 25) * gp.tileSize;

        gp.npc[2] = new NPC_Human(gp);
        gp.npc[2].worldX = gp.randomize(24, 39) * gp.tileSize;
        gp.npc[2].worldY = gp.randomize(21, 25) * gp.tileSize;

        gp.npc[3] = new NPC_Human(gp);
        gp.npc[3].worldX = gp.randomize(24, 39) * gp.tileSize;
        gp.npc[3].worldY = gp.randomize(21, 25) * gp.tileSize;

        gp.npc[4] = new NPC_Human(gp);
        gp.npc[4].worldX = gp.randomize(21, 31) * gp.tileSize;
        gp.npc[4].worldY = gp.randomize(10, 16) * gp.tileSize;

        gp.npc[5] = new NPC_Human(gp);
        gp.npc[5].worldX = gp.randomize(21, 31) * gp.tileSize;
        gp.npc[5].worldY = gp.randomize(10, 16) * gp.tileSize;


//        gp.npc[3] = new NPC_Dino(gp);
//        gp.npc[3].worldX = 12 * gp.tileSize;
//        gp.npc[3].worldY = 25 * gp.tileSize;
    }

    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 24 * gp.tileSize;
        gp.monster[0].worldY = 21 * gp.tileSize;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 21 * gp.tileSize;
        gp.monster[1].worldY = 22 * gp.tileSize;

        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = 20 * gp.tileSize;
        gp.monster[2].worldY = 11 * gp.tileSize;

    }
}
