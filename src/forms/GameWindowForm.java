package forms;

import javax.swing.*;

public class GameWindowForm extends JFrame {
    ImageIcon logo = new ImageIcon("./img/dino1.png");


    public GameWindowForm() {
        setTitle("REMAIN");
        setIconImage(logo.getImage());
        setSize(570, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
