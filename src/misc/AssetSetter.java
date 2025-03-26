package misc;

import entity.*;
import main.GamePanel;
import object.*;

public class AssetSetter {
    GamePanel gp;

    int buffer = 0;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }


    public void setPlayer(int choice){
        switch(choice){
            case 0:{
                gp.player.playing = "fort";
                break;
            }
            case 1:{
                gp.player.playing = "amaryllis";
                break;
            }
            case 2: {
                gp.player.playing = "sylvie";
                break;
            }
        }
        gp.player.setDefaultValues();
    }

    public void setObjectEntity(){
        int mapNum = 0;
        int i= 0;
        gp.objectEntity[0][i] = new OBJ_Boots(gp);
        gp.objectEntity[0][i].worldX = 23 * gp.tileSize;
        gp.objectEntity[0][i].worldY = 10 * gp.tileSize;
        i++;

        //MAP2
        gp.objectEntity[2][i] = new OBJ_Door(gp);
        gp.objectEntity[2][i].worldX = 29 * gp.tileSize;
        gp.objectEntity[2][i].worldY = 22 * gp.tileSize;
        i++;
    }

    public void setLivingEntity(){
        int mapNum = 0;
        int i = 0;
        //MAP1
        gp.livingEntity[0][i] = new NPC_OldMan(gp);
        gp.livingEntity[0][i].worldX = gp.livingEntity[0][i].spawnPointX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = gp.livingEntity[0][i].spawnPointY = 31 * gp.tileSize;
        gp.livingEntity[0][i].setLevel(100);
        i++;
        gp.livingEntity[0][i] = new NPC_Sylvie(gp);
        gp.livingEntity[0][i].worldX = gp.livingEntity[0][i].spawnPointX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = gp.livingEntity[0][i].spawnPointY = 28 * gp.tileSize;
        gp.livingEntity[0][i].setLevel(5);
        i++;
        gp.livingEntity[0][i] = new NPC_Fort(gp);
        gp.livingEntity[0][i].worldX = gp.livingEntity[0][i].spawnPointX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = gp.livingEntity[0][i].spawnPointY = 30 * gp.tileSize;
        gp.livingEntity[0][i].setLevel(10);
        i++;
        gp.livingEntity[0][i] = new NPC_Amaryllis(gp);
        gp.livingEntity[0][i].worldX = gp.livingEntity[0][i].spawnPointX = 8 * gp.tileSize;
        gp.livingEntity[0][i].worldY = gp.livingEntity[0][i].spawnPointY = 15 * gp.tileSize;
        gp.livingEntity[0][i].setLevel(50);
        i++;


        //MAP 2
        gp.livingEntity[1][i] = new NPC_Amaryllis(gp);
        gp.livingEntity[1][i].worldX = gp.livingEntity[1][i].spawnPointX = 19 * gp.tileSize;
        gp.livingEntity[1][i].worldY = gp.livingEntity[1][i].spawnPointY = 47 * gp.tileSize;
        gp.livingEntity[1][i].hasEvent = true;
        gp.livingEntity[1][i].direction = "up";
        gp.livingEntity[1][i].setLevel(10);
        System.out.println(gp.livingEntity[1][i].getName() +" has event in Map " + gp.currentMap);

        //MAP 3
        gp.livingEntity[3][0] = new NPC_MamaPausy(gp);
        gp.livingEntity[3][0].worldX = gp.livingEntity[3][0].spawnPointX = 46 * gp.tileSize;
        gp.livingEntity[3][0].worldY = gp.livingEntity[3][0].spawnPointY = 39 * gp.tileSize;
        gp.livingEntity[3][0].hasEvent = true;

        //MAP 4
        i = 0;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 32 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 18 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 33 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 18 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 34 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 19 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 34 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 21 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 40 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 18 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 41 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 19 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 20 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 15 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 21 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 15 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 22 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 15 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 33 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 15 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 34 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 15 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 34 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 32 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 35 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 32 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 36 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 32 * gp.tileSize;
        i++;
        gp.livingEntity[4][i] = new NPC_Miming(gp);
        gp.livingEntity[4][i].worldX = gp.livingEntity[4][i].spawnPointX = 36 * gp.tileSize;
        gp.livingEntity[4][i].worldY = gp.livingEntity[4][i].spawnPointY = 31 * gp.tileSize;
        i++;
    }

}
