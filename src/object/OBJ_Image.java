package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Image extends Entity {

    public OBJ_Image(GamePanel gp){
        super(gp);

        setName("Heart");
        image1 = setup("/background/background_nature", gp.screenWidth, gp.screenHeight);
        image2 = setup("/background/tempBackground", gp.screenWidth, gp.screenHeight);
        image3 = setup("/objects/heart_full", gp.screenWidth, gp.screenHeight);
        image4 = setup("/graphics/ui", gp.screenWidth, gp.screenHeight);

    }
}