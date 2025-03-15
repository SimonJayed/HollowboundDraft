package forms;

import anim.anim;
import interfaces.Forms;
import main.GamePanel;
import main.MainMenu;

import javax.swing.*;
import java.awt.event.*;

public class CharacterPICK extends GameWindowForm implements Forms {

    public String name;
    public String gender;
    public String race = "Human";

    private JLabel lblNAME;
    private JTextField tfNAME;
    private JLabel lblGENDER;
    private JRadioButton rbMALE;
    private JRadioButton rbFEMALE;
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
//    private JButton bBACK;
    private ButtonGroup bgRACE;
    private ButtonGroup bgGENDER;
    private MainMenu mainMenu;


    public CharacterPICK(GamePanel gp){
        Forms.customizeButton(rbHUMAN, 12);
        Forms.customizeButton(rbCOMPY, 12);
        Forms.customizeButton(rbCOELE, 12);
        Forms.customizeButton(rbPTERO, 12);
        Forms.customizeButton(rbMALE, 12);
        Forms.customizeButton(rbFEMALE, 12);
        Forms.customizeButton(bSUBMIT, 12);
//        Forms.customizeButton(bBACK, 12);
        Forms.customizeButton(lblNAME, 12);
        Forms.customizeButton(lblGENDER, 12);
        Forms.customizeButton(lblRACE, 12);
        Forms.customizeButton(lblSELECT, 20);
        Forms.customizeButton(tfNAME, 12);
        Forms.customizeButton(lblFNAME, 12);
        Forms.customizeButton(lblCPHRASE, 12);

        setContentPane(charPanel);

        tfNAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                name = tfNAME.getText();
                gp.player.setName(name);

                tfNAME.setToolTipText("15 character/letter limit");
                if (name.length() > 15) {
                    name = name.substring(0, 15);
                    tfNAME.setText(name);
                }

                if (name.trim().isEmpty()){
                    lblFNAME.setText("");
                }
                else{
                    lblFNAME.setText(name + ":");
                }
            }
        });

        rbMALE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.player.setGender(rbMALE.getText());
            }
        });

        rbHUMAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/human.png");
                race = "Human";
                gp.player.setRace(race);
                lblCPHRASE.setText( gp.player.getRace() + " " + gp.player.getName() + " " + gp.player.getGender() );
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbCOMPY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/compy.png");
                race = "Compy";
                gp.player.setRace(race);
                lblCPHRASE.setText( gp.player.getRace() + " " + gp.player.getName() + " " + gp.player.getGender() );
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbCOELE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/coele.png");
                race = "Coelacanth";
                gp.player.setRace(race);
                lblCPHRASE.setText( gp.player.getRace() + " " + gp.player.getName() + " " + gp.player.getGender() );
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        rbPTERO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/ptero.png");
                race = "Pterosaur";
                gp.player.setRace(race);
                lblCPHRASE.setText( gp.player.getRace() + " " + gp.player.getName() + " " + gp.player.getGender() );
                charPanel.revalidate();
                charPanel.repaint();
            }
        });

        bSUBMIT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblCPHRASE.setText( gp.player.getRace() + " " + gp.player.getName() + " " + gp.player.getGender() );
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bSUBMIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name = tfNAME.getText();
                    if (rbMALE.isSelected()) {
                        gender = "Male";
                        gp.player.setGender(gender);
                    } else if (rbFEMALE.isSelected()) {
                        gender = "Female";
                        gp.player.setGender(gender);
                    }

                    if (name != null && !name.trim().isEmpty() && gender != null && race != null) {
                        gp.initializePlayer(name, gender, race);
                        System.out.println("Name: " + name);
                        System.out.println("Gender: " + gender);
                        System.out.println("Race: " + race);

                        new anim(gp);
                        dispose();
                    } else {
                        throw new Exception("Enter the fields properly, dude.");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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
