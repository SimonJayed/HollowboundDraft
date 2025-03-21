package misc;

import main.GamePanel;

import java.util.Objects;

public class Events {
    GamePanel gp;

    boolean canTouchEvent = true;

    public Events(GamePanel gp){
        this.gp = gp;
    }

    public void teleport(int gameState, int x, int y, String reqDirection) {
        gp.gameState = gameState;
        switch(gp.currentMap){
            case 0:{
                if(reqDirection.equals("right")){
                    gp.currentMap = 1;
                }

                break;
            }
            case 1:{
                if(reqDirection.equals("left")){
                    gp.currentMap = 0;
                }if(reqDirection.equals("right")){
                    gp.currentMap = 0;
                    System.out.println("Next Map");
                }
                break;
            }
        }
        gp.player.worldX = gp.tileSize * x;
        gp.player.worldY = gp.tileSize * y;
        canTouchEvent = false;
        System.out.println("Map Changed");
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;

        if (Objects.equals(gp.player.direction, "up")) {
            gp.player.worldY += (gp.player.speed + 2);
        }
        if (Objects.equals(gp.player.direction, "down")) {
            gp.player.worldY -= (gp.player.speed + 2);
        }
        if (Objects.equals(gp.player.direction, "left")) {
            gp.player.worldX += (gp.player.speed + 2);
        }
        if (Objects.equals(gp.player.direction, "right")) {
            gp.player.worldX -= (gp.player.speed + 2);
        }
        canTouchEvent = false;
    }

    public void scene(int gameState) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. \nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
        }
    }
}
