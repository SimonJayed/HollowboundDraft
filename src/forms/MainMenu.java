package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JFrame implements Forms{
    private JPanel pnlMAIN;
    private JLabel lblTITLE;
    private JButton btnSTART;
    private JButton btnGALLERY;
    private JButton btnEXIT;

    public MainMenu(){
        Forms.customizeButton(btnSTART);
        Forms.customizeButton(btnGALLERY);
        Forms.customizeButton(btnEXIT);

        Color hoverColor = Color.WHITE;
        Color defaultColor = UIManager.getColor("Button.foreground");

        // Create a reusable MouseListener
        ButtonHoverEffect hoverEffect = new ButtonHoverEffect(hoverColor, defaultColor);

        btnSTART.addMouseListener(hoverEffect);
        btnGALLERY.addMouseListener(hoverEffect);
        btnEXIT.addMouseListener(hoverEffect);

        btnSTART.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterPICK app = new CharacterPICK();
                dispose();
            }
        });

        btnGALLERY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Add an ActionListener to the "Exit" button
        btnEXIT.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        MainMenu app = new MainMenu();

        app.setContentPane(app.pnlMAIN);
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
