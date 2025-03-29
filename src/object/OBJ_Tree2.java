package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tree2 extends Entity {

    public OBJ_Tree2(GamePanel gp){
        super(gp);

        setName("House1");
        down1 = setup("/objects/treeForeground2", gp.tileSize*6, gp.tileSize*6);
        down2 = setup("/objects/treeForeground2", gp.tileSize*6, gp.tileSize*6);
        collision = false;

        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 0;
        this.solidArea.height = 0;
    }
}