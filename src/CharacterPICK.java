import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterPICK extends JFrame{
    private JLabel lblNAME;
    private JTextField tfNAME;
    private JLabel lblGENDER;
    private JRadioButton rbMALE;
    private JRadioButton rbFEMALE;
    private JRadioButton rbOthers;
    private JRadioButton rbIT;
    private JLabel lblPHRASE;
    private JTextField textField1;
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

    public CharacterPICK(){
        rbHUMAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/human.png");
            }
        });

        rbCOMPY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/compy.png");
            }
        });

        rbCOELE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/coele.png");
            }
        });

        rbPTERO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("./img/ptero.png");
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
        imgRACE = new JLabel(new ImageIcon("./img/dino1.png"));
    }
}
