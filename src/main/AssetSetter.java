package main;

import entity.NPC_OldMan;
import entity.race.coele;
import entity.race.compy;
import entity.race.human;
import entity.monster.MON_GreenSlime;
import object.*;

public class AssetSetter {
    GamePanel gp;

    int buffer = 0;
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

        for(int i = 0; i < 4; i++){
            if (gp.npc[i] == null){
                    gp.npc[i] = new human(gp);
                    gp.npc[i].level = gp.randomize(1,10);
                    gp.npc[i].worldX = gp.randomize(43, 49) * gp.tileSize;
                    gp.npc[i].worldY = gp.randomize(29, 34) * gp.tileSize;
                    System.out.println("Human spawned.");
            }
        }

        gp.npc[6] = new NPC_OldMan(gp);
        gp.npc[6].worldX = 54 * gp.tileSize;
        gp.npc[6].worldY = 56 * gp.tileSize;


//        gp.npc[7] = new coele(gp);
//        gp.npc[7].worldX = 24 * gp.tileSize;
//        gp.npc[7].worldY = 18 * gp.tileSize;
//
//        gp.npc[8] = new coele(gp);
//        gp.npc[8].worldX = 25 * gp.tileSize;
//        gp.npc[8].worldY = 18 * gp.tileSize;


//        gp.npc[3] = new compy(gp);
//        gp.npc[3].worldX = 12 * gp.tileSize;
//        gp.npc[3].worldY = 25 * gp.tileSize;
    }

    public void setMonster(){
        buffer++;

        if ( buffer >= 1200){
            for(int i = 0; i < 10; i++){
                if (gp.monster[i] == null){
                    if(buffer>=16000){
                        gp.monster[i] = new compy(gp);
                        gp.monster[i].level = gp.randomize(1,10);
                        gp.monster[i].worldX = gp.randomize(15, 18) * gp.tileSize;
                        gp.monster[i].worldY = gp.randomize(46, 50) * gp.tileSize;
                        System.out.println("Dino spawned.");
                    }
                }
            }
        }
    }
}
