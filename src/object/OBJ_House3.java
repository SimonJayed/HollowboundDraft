package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House3 extends Entity {

    public OBJ_House3(GamePanel gp){
        super(gp);

        setName("House3");
        down1 = setup("/objects/balay3", gp.tileSize*6, gp.tileSize*5);
        down2 = setup("/objects/balay3", gp.tileSize*6, gp.tileSize*5);
        collision = false;

    }
}