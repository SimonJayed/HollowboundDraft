package main;

import entity.NPC_OldMan;
import entity.characters.Rex;
import object.*;

public class AssetSetter {
    GamePanel gp;

    int buffer = 0;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setPlayer(){
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

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = 54 * gp.tileSize;
        gp.npc[0].worldY = 56 * gp.tileSize;


    }

    public void setMonster(){

        gp.monster[0] = new Rex(gp);
        gp.monster[0].type = 2;
        gp.monster[0].level = gp.randomize(1, 10);
        gp.monster[0].worldX = 53 * gp.tileSize;
        gp.monster[0].worldY = 55 * gp.tileSize;

    }
}
