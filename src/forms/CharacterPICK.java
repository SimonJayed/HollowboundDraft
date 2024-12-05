package forms;

import entity.Entity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        rbHUMAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/human.png");
                String name = tfNAME.getText();
                p1 = new Entity.Human(name, rbMALE.isSelected() ? "Male" : "Female");
                lblCPHRASE.setText(p1.toString());
                lblFNAME.setText(name + ":");
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
                lblFNAME.setText(name + ":");
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
                lblFNAME.setText(name + ":");
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
                lblFNAME.setText(name + ":");
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        bSUBMIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FightPanel app = new FightPanel();
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
