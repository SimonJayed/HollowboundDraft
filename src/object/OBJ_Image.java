package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Image extends Entity {

    public OBJ_Image(GamePanel gp){
        super(gp);

        setName("Heart");
        image1 = setup("/background/background_nature", gp.screenWidth, gp.screenHeight);
        image2 = setup("/ui/portrait", gp.screenWidth, gp.screenHeight);
        image3 = setup("/objects/heart_full", gp.screenWidth, gp.screenHeight);

    }
}