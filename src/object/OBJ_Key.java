package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){
        super(gp);
        canInsult = 0;

        setName("Key");
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
    }
}
