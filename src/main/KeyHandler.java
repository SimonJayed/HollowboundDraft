package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,
            enterPressed, shiftPressed, ctrPressed, tabPressed, ePressed,
            qPressed;


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

        if (gp.gameState == gp.playState){
            if (code == KeyEvent.VK_W){
                upPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT){
                if(shiftPressed == false){
                    shiftPressed = true;
                    gp.player.running = true;
                }
                else{
                    shiftPressed = false;
                    gp.player.running = false;
                }

            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
                System.out.println("p pressed and " + gp.gameState);
            }
//            if (code == KeyEvent.VK_R){
//                gp.tileM.loadMap("/maps/islandmap1.txt");
//            }
            if (code == KeyEvent.VK_TAB){
                tabPressed = true;
            }

            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

//            if (code == KeyEvent.VK_CONTROL){
//                ctrPressed = true;
//            }

            if (code == KeyEvent.VK_T){
                if (showDebugTest == false){
                    showDebugTest = true;
                }

                else if(showDebugTest == true){
                    showDebugTest = false;
                }
            }
        }
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q){
            qPressed = false;
        }
        if (code == KeyEvent.VK_E){
            ePressed = false;
        }
        if (code == KeyEvent.VK_CONTROL){
            ctrPressed = false;
        }
    }
}
