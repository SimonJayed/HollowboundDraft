package screen;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CharacterPickScreen implements Screen{
    GamePanel gp;

    BufferedImage background, selectFort, selectSylvie, selectAmaryllis;

    public int commandNum = 1;

    public CharacterPickScreen(GamePanel gp) {
        this.gp = gp;
        loadImages();
    }

    public void loadImages() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/titleBackground2.png")));
            selectAmaryllis = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/amaryllisPick2.png")));
            selectFort = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/fortPick2.png")));
            selectSylvie = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/pick/sylviePick2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emptyImages(){
        System.out.println("Title Images Unloaded");
        background = null;
        selectSylvie = null;
        selectFort = null;
        selectAmaryllis = null;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(gp.customFont);
        g2.setColor(Color.white);

        int x = 0;
        int y = 0;

        g2.drawImage(background, x, y,gp.screenWidth, gp. screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(20f));
        g2.setColor(Color.black);
        g2.drawString("<< BACK", gp.tileSize/2+12, gp.tileSize+12);
        g2.setColor(Color.white);
        g2.drawString("<< BACK", gp.tileSize/2+10, gp.tileSize+10);
        g2.setFont(g2.getFont().deriveFont(8f));
        g2.drawString("ESC", gp.tileSize/2, gp.tileSize-5);

        if(commandNum == 0){
            g2.drawImage(selectAmaryllis, x, y,gp.screenWidth, gp. screenHeight, null);
        }
        else if(commandNum == 1){
            g2.drawImage(selectFort, x, y,gp.screenWidth, gp. screenHeight, null);
        }
        else if (commandNum == 2){
            g2.drawImage(selectSylvie, x, y,gp.screenWidth, gp. screenHeight, null);
        }
        else if(commandNum == 3){
            g2.setColor(Color.black);
            g2.fillRect(gp.tileSize/2, gp.tileSize/2, gp.tileSize*2+20, gp.tileSize);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21f));

            g2.drawString("<< BACK", gp.tileSize/2+12, gp.tileSize+12);
            g2.setColor(Color.white);
            g2.drawString("<< BACK", gp.tileSize/2+10, gp.tileSize+10);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,9f));
            g2.drawString("ESC", gp.tileSize/2, gp.tileSize-10);
        }
    }
}
