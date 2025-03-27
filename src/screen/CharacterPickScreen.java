package screen;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CharacterPickScreen implements Screen{
    GamePanel gp;
    Graphics2D g2;

    BufferedImage background, selectFort, selectSylvie, selectAmaryllis;

    public int commandNum = 1;

    public CharacterPickScreen(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;

        try{
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/titleBackground.png")));
            selectAmaryllis = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/amaryllisPick.png")));
            selectFort = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/fortPick.png")));
            selectSylvie = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/sylviePick.png")));
        }catch(Exception e){
            e.printStackTrace();
        }

        g2.drawImage(background, x, y,gp.screenWidth, gp. screenHeight, null);

        if(commandNum == 0){
            g2.drawImage(selectAmaryllis, x, y,gp.screenWidth, gp. screenHeight, null);
        }
        else if(commandNum == 1){
            g2.drawImage(selectFort, x, y,gp.screenWidth, gp. screenHeight, null);
        }
        else{
            g2.drawImage(selectSylvie, x, y,gp.screenWidth, gp. screenHeight, null);
        }
    }
}
