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
        gp.objectEntity[0][i] = new OBJ_Lipoden(gp);
        gp.objectEntity[0][i].worldX = 25 * gp.tileSize;
        gp.objectEntity[0][i].worldY = 24 * gp.tileSize;
        i++;
        gp.objectEntity[0][i] = new OBJ_Lipoden(gp);
        gp.objectEntity[0][i].worldX = 35 * gp.tileSize;
        gp.objectEntity[0][i].worldY = 12 * gp.tileSize;
    }

    public void setLivingEntity(){
        int mapNum = 0;
        int i = 0;
        //MAP1
        gp.livingEntity[0][i] = new NPC_OldMan(gp);
        gp.livingEntity[0][i].worldX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = 31 * gp.tileSize;
        gp.livingEntity[0][i].hasEvent = true;
        gp.livingEntity[0][i].level = 10;
        System.out.println(gp.livingEntity[0][i].getName() +" has event in Map " + gp.currentMap);
        i++;
        gp.livingEntity[0][i] = new NPC_Sylvie(gp);
        gp.livingEntity[0][i].worldX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = 32 * gp.tileSize;
        gp.livingEntity[0][i].level = 5;
        i++;
        gp.livingEntity[0][i] = new NPC_Fort(gp);
        gp.livingEntity[0][i].worldX = 11 * gp.tileSize;
        gp.livingEntity[0][i].worldY = 30  * gp.tileSize;
        gp.livingEntity[0][i].level = 10;
        i++;
        gp.livingEntity[0][i] = new NPC_Amaryllis(gp);
        gp.livingEntity[0][i].worldX = 8 * gp.tileSize;
        gp.livingEntity[0][i].worldY = 15  * gp.tileSize;
        gp.livingEntity[0][i].level = 50;
        i++;


        //MAP 2
        gp.livingEntity[1][i] = new NPC_Amaryllis(gp);
        gp.livingEntity[1][i].worldX = 19 * gp.tileSize;
        gp.livingEntity[1][i].worldY = 45 * gp.tileSize - gp.livingEntity[1][i].solidArea.y;
        gp.livingEntity[1][i].hasEvent = true;
        gp.livingEntity[1][i].level = 10;
        System.out.println(gp.livingEntity[1][i].getName() +" has event in Map " + gp.currentMap);
    }
}
