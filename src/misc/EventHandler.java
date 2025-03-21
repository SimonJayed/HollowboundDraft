package misc;


import main.GamePanel;

import java.util.Objects;

public class EventHandler{
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;

    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent && gp.currentMap == 0){
            //GOING TO MAP 2
            if (hit(0,48, 42, "right")){
                teleport(gp.playState, 2, 45, "right");
            }
            if (hit(0,48, 43, "right")){
                teleport(gp.playState, 2, 46, "right");
            }
            if (hit(0,48, 44, "right")){
                teleport(gp.playState, 2, 46, "right");
            }
            if (hit(0,48, 45, "right")){
                teleport(gp.playState, 2, 47, "right");
            }
            if (hit(0,48, 46, "right")){
                teleport(gp.playState, 2, 48, "right");
            }
        }
        if (canTouchEvent && gp.currentMap == 1){
            //GOING BACK TO MAP 1
            if (hit(0,1, 45, "left")){
                teleport(gp.playState, 48, 42, "left");
            }
            if (hit(0,1, 46, "left")){
                teleport(gp.playState, 48, 44, "left");
            }
            if (hit(0,1, 47, "left")){
                teleport(gp.playState, 48, 44, "left");
            }
            if (hit(0,1, 48, "left")){
                teleport(gp.playState, 48, 46, "left");
            }

            //GOING TO MAP 3
            if (hit(0,48, 45, "right")){
                teleport(gp.playState, 2, 1, "right");
            }
            if (hit(0,48, 46, "right")){
                teleport(gp.playState, 2, 3, "right");
            }
            if (hit(0,48, 47, "right")){
                teleport(gp.playState, 2, 4, "right");
            }
            if (hit(0,48, 48, "right")){
                teleport(gp.playState, 2, 5, "right");
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
        eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

        if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
        eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        return hit;
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

