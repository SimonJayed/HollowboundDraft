package forms;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CharacterPICK app = new CharacterPICK();

        app.setContentPane(app.charPanel);
        app.setTitle("REMAIN");

        ImageIcon logo = new ImageIcon("./img/dino1.png");
        app.setIconImage(logo.getImage());


        app.setSize(570, 650);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setResizable(false);
        app.setVisible(true);
    }
}