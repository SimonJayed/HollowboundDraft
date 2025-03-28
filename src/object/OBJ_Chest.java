package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {


    public OBJ_Chest(GamePanel gp){
        super(gp);

        setName("Chest");
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }
}
