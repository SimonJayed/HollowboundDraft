package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,
            enterPressed, shiftPressed, tabPressed, ePressed,
            qPressed, mPressed, spacePressed, zeroPressed;


    boolean showDebugTest = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLESTATE
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
                gp.playSoundEffect(3);
                gp.sound.setVolume(-20.0f);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
                gp.playSoundEffect(3);
                gp.sound.setVolume(-20.0f);
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                if (gp.ui.commandNum == 0) {
                    gp.aSetter.setPlayer(2);
                    gp.gameState = gp.playState;
                    System.out.println(gp.gameState);
                    gp.playMusic(2);
                    gp.sound.setVolume(-25.0f);
                }
                if (gp.ui.commandNum == 1) {

                }
                if (gp.ui.commandNum == 2) {

                }
                if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        }
        //BATTLESTATE
        else if (gp.gameState == gp.battleState) {
            if(!gp.battleScreen.isAttacking){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.battleScreen.commandNum--;
                    if (gp.battleScreen.commandNum < 0) {
                        gp.battleScreen.commandNum = 3;
                    }
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.battleScreen.commandNum++;
                    if (gp.battleScreen.commandNum > 3) {
                        gp.battleScreen.commandNum = 0;
                    }
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                    if (gp.battleScreen.commandNum == 0) {
                        gp.battleScreen.attack();
                    }
                    if (gp.battleScreen.commandNum == 3) {
                        if(gp.battleScreen.canEscape){
                            int num = gp.randomize(1, 8);
                            if(num == 8){
                                gp.gameState = gp.playState;
                            }
                            else {
                                System.out.println("Escape Failed.");
                            }
                        }
                        else{
                            System.out.println("Can't Escape.");
                        }
                        gp.battleScreen.isAttacking = false;
                        gp.battleScreen.enemyTurn();
                    }
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
            }
            else if(gp.battleScreen.isAttacking){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.battleScreen.commandNum--;
                    if (gp.battleScreen.commandNum < 0) {
                        gp.battleScreen.commandNum = 2;
                    }
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.battleScreen.commandNum++;
                    if (gp.battleScreen.commandNum > 2) {
                        gp.battleScreen.commandNum = 0;
                    }
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                    if(gp.battleScreen.commandNum == 0){
                        gp.battleScreen.damage("HEAD");
                    }
                    if(gp.battleScreen.commandNum == 1){
                        gp.battleScreen.damage("TORSO");
                    }
                    if(gp.battleScreen.commandNum == 2){
                        gp.battleScreen.damage("LEGS");
                    }
                    gp.battleScreen.isAttacking = false;
                    gp.battleScreen.enemyTurn();
                    gp.playSoundEffect(3);
                    gp.sound.setVolume(-20.0f);
                }
                if(code == KeyEvent.VK_ESCAPE){
                    gp.battleScreen.isAttacking = false;
                }
            }
        }
        //PLAYSTATE
        else if (gp.gameState == gp.playState && !gp.player.isDefeated) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
            if (code == KeyEvent.VK_TAB) {
                tabPressed = !tabPressed;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = !enterPressed;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_0) {
                zeroPressed = !zeroPressed;
            }
            if (code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_Q) {
                gp.gameState = gp.inventoryState;
                System.out.println("Inventory opened");
            }
            if (code == KeyEvent.VK_M ) {
                gp.gameState = gp.mapState;
                gp.map.miniMapOn = false;
                System.out.println("Map opened");
            }
        }
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.inventoryState) {
            if (code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
                System.out.println("Inventory closed");
            }
        }
        else if (gp.gameState == gp.mapState) {
            if (code == KeyEvent.VK_M || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
                System.out.println("Map closed");
            }
        }



        if (code == KeyEvent.VK_T) {
            if (!showDebugTest) {
                showDebugTest = true;
            } else {
                showDebugTest = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q){
            qPressed = false;
        }
        if (code == KeyEvent.VK_E){
            ePressed = false;
        }
        if (code == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
