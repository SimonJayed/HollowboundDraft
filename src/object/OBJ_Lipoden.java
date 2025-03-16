package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lipoden extends Entity {

    public OBJ_Lipoden(GamePanel gp){
        super(gp);

        setName("Lipodendron");
        down1 = setup("/objects/Lipoden", gp.tileSize*23, gp.tileSize*23);
        down2 = setup("/objects/Lipoden", gp.tileSize*23, gp.tileSize*23);
        collision = true;

//        solidArea.y = 1;
//        solidArea.width = 12;
//        solidArea.height = 12;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;

    }
}
