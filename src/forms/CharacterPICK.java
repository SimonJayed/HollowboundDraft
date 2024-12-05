package forms;

import anim.shrik;
import entity.Entity;
import main.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterPICK extends JFrame implements Forms{
    private JLabel lblNAME;
    private JTextField tfNAME;
    private JLabel lblGENDER;
    private JRadioButton rbMALE;
    private JRadioButton rbFEMALE;
    private JRadioButton rbOTHERS;
    private JRadioButton rbIT;
    private JButton bSUBMIT;
    private JLabel lblSELECT;
    private JLabel lblRACE;
    private JRadioButton rbHUMAN;
    private JRadioButton rbCOMPY;
    private JRadioButton rbCOELE;
    private JRadioButton rbPTERO;
    private JLabel imgRACE;
    public JPanel charPanel;
    private JLabel lblCPHRASE;
    private JLabel lblFNAME;

    private Entity p1;

    public CharacterPICK(){
        Forms.customizeButton(rbHUMAN);
        Forms.customizeButton(rbCOMPY);
        Forms.customizeButton(rbCOELE);
        Forms.customizeButton(rbPTERO);
        Forms.customizeButton(rbMALE);
        Forms.customizeButton(rbFEMALE);
        Forms.customizeButton(rbOTHERS);
        Forms.customizeButton(rbIT);
        Forms.customizeButton(bSUBMIT);

        setContentPane(charPanel);
        setSize(570, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        tfNAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = tfNAME.getText();
                lblFNAME.setText(name + ":");
            }
        });

        rbHUMAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/human.png");
                String name = tfNAME.getText();
                p1 = new Entity.Human(name, rbMALE.isSelected() ? "Male" : "Female");
                lblCPHRASE.setText(p1.toString());
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbCOMPY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/compy.png");
                String name = tfNAME.getText();
                p1 = new Entity.Compy(name, rbMALE.isSelected() ? "Male" : "Female");
                lblCPHRASE.setText(p1.toString());
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbCOELE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/coele.png");
                String name = tfNAME.getText();
                p1 = new Entity.Coelacanth(name, rbMALE.isSelected() ? "Male" : "Female");
                lblCPHRASE.setText(p1.toString());
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbPTERO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/ptero.png");
                String name = tfNAME.getText();
                p1 = new Entity.Pterosaur(name, rbMALE.isSelected() ? "Male" : "Female");
                lblCPHRASE.setText(p1.toString());
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        bSUBMIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new shrik();
//                JFrame window = new JFrame();
//                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                window.setResizable(false);
//                window.setSize(768, 576);
//                window.setTitle("REMAIN");
//
//                GamePanel gamePanel = new GamePanel();
//                window.add(gamePanel);
//
//                window.pack();
//
//                window.setLocationRelativeTo(null);
//                window.setVisible(true);
//
//                gamePanel.startGameThread();
                dispose();
            }
        });
    }

    private void updateImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        imgRACE.setIcon(icon);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imgRACE = new JLabel(new ImageIcon("./img/def.png"));
    }
}
