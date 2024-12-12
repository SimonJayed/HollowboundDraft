package main;

import interfaces.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PlayerBar extends JFrame implements Forms {
    GamePanel gp;
    ImageIcon logo = new ImageIcon("img/dino1.png");

    private int xOffset = 0;
    private int yOffset = 0;

    public PlayerBar (GamePanel gp){
        setUndecorated(true);
        setBackground(new Color( 0, 0, 0, 130));
        setSize(700, 400);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
    }

}