package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightPanel extends CharacterPICK{
    private JButton attackButton;
    private JButton itemsButton;
    private JButton dinosaursButton;
    private JButton runButton;
    private JPanel jpanel;
    private JLabel lPlayer2;
    private JLabel lPlayer1;
    private JLabel lHP1;
    private JLabel lHP2;
    private JLabel dino2;
    private JLabel dino1;

    public FightPanel(){
        attackButton.setFocusPainted(false);
        itemsButton.setFocusPainted(false);
        dinosaursButton.setFocusPainted(false);
        runButton.setFocusPainted(false);


        setContentPane(jpanel);
        setSize(970, 750);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon boom = new ImageIcon("./img/boom.jpg");

                JLabel boomLbl = new JLabel(boom);
                jpanel.add(boomLbl);
                jpanel.revalidate();
                jpanel.repaint();
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        dino2 = new JLabel(new ImageIcon("./img/dino2.png"));
        dino1 = new JLabel(new ImageIcon("./img/dino1.png"));
    }
}
