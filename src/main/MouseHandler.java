package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    GamePanel gp;
    public MouseHandler (GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int code = e.getButton();

        if(code == MouseEvent.BUTTON1){
            gp.keyH.enterPressed = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
