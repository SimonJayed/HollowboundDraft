package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lipoden extends Entity {

    public OBJ_Lipoden(GamePanel gp){
        super(gp);

        setName("Lipodendron");
        down1 = setup("/objects/Lipoden", gp.tileSize, gp.tileSize+8);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 35;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
