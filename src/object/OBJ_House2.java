package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House2 extends Entity {

    public OBJ_House2(GamePanel gp){
        super(gp);

        setName("House2");
        down1 = setup("/objects/balay2", gp.tileSize*6, gp.tileSize*5);
        down2 = setup("/objects/balay2", gp.tileSize*6, gp.tileSize*5);
        collision = false;

    }
}