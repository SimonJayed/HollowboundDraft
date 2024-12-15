package main;

import entity.NPC_OldMan;
import entity.characters.Rex;
import entity.race.coele;
import entity.race.compy;
import entity.race.human;
import entity.monster.MON_GreenSlime;
import forms.CharacterPICK;
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
        buffer++;
        System.out.println(buffer);
        int i = 0;

        boolean npcSpawned = false;


        for(; i < 5; i++){
            if (gp.npc[i] == null) {
                gp.npc[i] = new human(gp);
                gp.npc[i].level = gp.randomize(1, 10);
                gp.npc[i].worldX = 22 * gp.tileSize;
                gp.npc[i].worldY = (12 + i) * gp.tileSize;
                System.out.println("Human spawned.");

                npcSpawned = true;
            }

            if (npcSpawned) {
                buffer = 0;
            }
        }

        for(; i < 10; i++){
            if (gp.npc[i] == null){
                gp.npc[i] = new human(gp);
                gp.npc[i].level = gp.randomize(1,10);
                gp.npc[i].worldX = 20 * gp.tileSize;
                gp.npc[i].worldY = (10 + i) * gp.tileSize;
                System.out.println("Human spawned.");

                npcSpawned = true;
            }

            if (npcSpawned) {
                buffer = 0;
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
        int i = 0;

        boolean monsterSpawned = false;

        for (; i < 10; i++) {
            if (gp.monster[i] == null) {
                gp.monster[i] = new compy(gp);
                gp.monster[i].type = 2;
                gp.monster[i].level = gp.randomize(1, 10);
                gp.monster[i].worldX = 74 * gp.tileSize;
                gp.monster[i].worldY = (17 + i) * gp.tileSize;
                System.out.println("Dino spawned.");

                monsterSpawned = true;
            }


            if (monsterSpawned) {
                buffer = 0;
            }
        }

        gp.monster[10] = new Rex(gp);
        gp.monster[10].type = 2;
        gp.monster[10].level = gp.randomize(1, 10);
        gp.monster[10].worldX = 53 * gp.tileSize;
        gp.monster[10].worldY = 55 * gp.tileSize;

    }
}
