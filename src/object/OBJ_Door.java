package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp){
        super(gp);

        setName("Door");
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void speak(){
        dialogues[0] = "\"BEWEARRR!! DANGERRR!!!\"";
    }
}
