package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/islandmap1.txt");
    }

    public void getTileImage() {
        setup(0, "grassPlain", false);
        setup(1, "grassTopLeft", true);
        setup(2, "grassTopMid", true);
        setup(3, "grassTopRight", true);
        setup(4, "grassMidLeft", true);
        setup(5, "waterPlain", true);
        setup(6, "grassMidRight", true);
        setup(7, "grassLowLeft", true);
        setup(8, "grassMidLow", true);
        setup(9, "grassLowRight", true);
        setup(10, "grassULCorner", true);
        setup(11, "grassURCorner", true);
        setup(12, "grassLLCorner", true);
        setup(13, "grassLRCorner", true);

        setup(14, "waterWow", true);
        setup(15, "grassGrass", false);
        setup(16, "Lipodendrus", true);

        setup(17, "grassPlain", true);
        setup(18, "mtgrassTopLeft", true);
        setup(19, "mtgrassTopMid", true);
        setup(20, "mtgrassTopRight", true);
        setup(21, "mtgrassMidRight", true);
        setup(22, "mtgrassMidLeft", true);
        setup(23, "mtgrassLowLeft", true);
        setup(24, "mtgrassLowMid", true);
        setup(25, "mtgrassLowRight", true);
        setup(30, "blk", true);

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e){
            System.out.println(index + ": error.");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tile map missing", "Tile Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
    }

    public void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    try{
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    } catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Tile map missing", "Tile Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }

                }

                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            try {

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            } catch(Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
