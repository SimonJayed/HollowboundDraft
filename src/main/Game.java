package main;

import javax.swing.*;
import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(768, 576);
        window.setTitle("REMAIN");

        ImageIcon logo = new ImageIcon("./img/dino1.png");
        window.setIconImage(logo.getImage());

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}