package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,
            enterPressed, shiftPressed, ctrPressed, tabPressed, ePressed,
            qPressed, mPressed, spacePressed, zeroPressed, minusPressed;


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

        if (gp.gameState == gp.playState || gp.gameState == gp.flowState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
            if (code == KeyEvent.VK_M) {
                mPressed = !mPressed;
            }
            if (code == KeyEvent.VK_Q) {
                qPressed = true;
            }
        }

        if (gp.gameState == gp.playState) {
//            if (code == KeyEvent.VK_R){
//                gp.tileM.loadMap("/maps/islandmap1.txt");
//            }
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
        }


            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                System.out.println("P pressed and " + gp.gameState);
            }
            if(code == KeyEvent.VK_MINUS){
                gp.gameState = gp.flowState;
            }
            if (code == KeyEvent.VK_T) {
                if (!showDebugTest) {
                    showDebugTest = true;
                } else {
                    showDebugTest = false;
                }
            }


        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_SPACE){
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
        if (code == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
//        if (code == KeyEvent.VK_M) {
//            mPressed = false;
//        }
    }
}
