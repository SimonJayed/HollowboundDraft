package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Image extends Entity {

    public OBJ_Image(GamePanel gp){
        super(gp);

        setName("Background");
        image1 = setup("/background/titleBackground", gp.screenWidth, gp.screenHeight);
        image2 = setup("/background/tempBackground", gp.screenWidth, gp.screenHeight);
        image3 = setup("/background/veyraScene", gp.screenWidth, gp.screenHeight);
        image4 = setup("/background/catCaveScene1", gp.screenWidth, gp.screenHeight);

    }
}