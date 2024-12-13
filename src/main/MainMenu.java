package main;

import forms.ButtonHoverEffect;
import forms.CharacterPICK;
import interfaces.Forms;
import forms.GameWindowForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends GameWindowForm implements Forms {
    private JPanel pnlMAIN;
    private JLabel lblTITLE;
    private JButton btnSTART;
    private JButton btnGALLERY;
    private JButton btnEXIT;

    public MainMenu(GamePanel gp){
        Forms.customizeButton(btnSTART, 20);
        Forms.customizeButton(btnGALLERY, 20);
        Forms.customizeButton(btnEXIT, 20);

        Color hoverColor = Color.WHITE;
        Color defaultColor = UIManager.getColor("Button.foreground");

        ButtonHoverEffect hoverEffect = new ButtonHoverEffect(hoverColor, defaultColor);

        btnSTART.addMouseListener(hoverEffect);
        btnGALLERY.addMouseListener(hoverEffect);
        btnEXIT.addMouseListener(hoverEffect);

            btnSTART.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CharacterPICK charPick = new CharacterPICK(gp);
                    charPick.setVisible(true);
                    dispose();
                }
            });

            btnGALLERY.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                }
            });

            btnEXIT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        }

        public static void main(String[] args) {
            GamePanel gp = new GamePanel();

            MainMenu app = new MainMenu(gp);

            app.setContentPane(app.pnlMAIN);
            app.setVisible(true);
        }
}
