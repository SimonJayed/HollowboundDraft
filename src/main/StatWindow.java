package main;

import entity.Entity;
import interfaces.Forms;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;


public class StatWindow extends JFrame implements Forms {
    public Entity entity;
    GamePanel gp;
    ImageIcon logo = new ImageIcon("img/dino1.png");

    private int xOffset = 0;
    private int yOffset = 0;

    private JPanel pnlSTAT;
    private JLabel lblNAME;
    private JLabel lblRACE;
    private JLabel lblGENDER;
    private JLabel lblSTATUS;
    private JLabel lblLEVEL;
    private JLabel lblNM;
    private JLabel lblLVL;
    private JLabel lblRC;
    private JLabel lblGNDR;
    private JSeparator sep1;
    private JLabel lblEXP;
    private JLabel lblPXP;
    private JLabel lblATK;
    private JLabel lblDEF;
    private JLabel lblSPD;
    private JLabel lblATKVal;
    private JLabel lblDEFVal;
    private JLabel lblSPDVal;
    private JLabel lblHP;
    private JLabel lblHPVal;
    private JSlider slider1;

    public StatWindow(GamePanel gp) {
        this.gp = gp;
        Forms.customizeButton(lblNAME, 22);
        Forms.customizeButton(lblLEVEL, 22);
        Forms.customizeButton(lblRACE, 22);
        Forms.customizeButton(lblGENDER, 22);
        Forms.customizeButton(lblNAME, 22);
        Forms.customizeButton(lblSTATUS, 33);
        Forms.customizeButton(lblNM, 26);
        Forms.customizeButton(lblLVL, 26);
        Forms.customizeButton(lblRC, 26);
        Forms.customizeButton(lblGNDR, 26);
        Forms.customizeButton(lblEXP, 26);
        Forms.customizeButton(lblPXP, 22);
        Forms.customizeButton(lblHP, 22);
        Forms.customizeButton(lblATK, 22);
        Forms.customizeButton(lblDEF, 22);
        Forms.customizeButton(lblSPD, 22);
        Forms.customizeButton(lblATKVal, 22);
        Forms.customizeButton(lblHPVal, 22);
        Forms.customizeButton(lblDEFVal, 22);
        Forms.customizeButton(lblSPDVal, 22);

        setUndecorated(true);
        setBackground(new Color(0,0,10, 20));
//        pnlSTAT.setOpaque(false);

        setTitle("STATUS WINDOW");

//        setAlwaysOnTop(true);
        setContentPane(pnlSTAT);
        setIconImage(logo.getImage());
        setSize(500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlSTAT.setBackground(new Color(34, 123, 219, 255));
        sep1.setBackground(new Color(217, 255, 254, 255));

        lblNAME.setText(gp.player.getName());
        lblLEVEL.setText(String.valueOf(gp.player.level));
        lblPXP.setText(gp.player.exp + "/" + gp.player.nextLevelExp);


        lblHPVal.setText(gp.player.life + "/" + gp.player.maxLife);
        lblATKVal.setText(String.valueOf(gp.player.strength));
        lblDEFVal.setText(String.valueOf(gp.player.defense));
        lblSPDVal.setText(String.valueOf(gp.player.speed));


        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
        pnlSTAT.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        pnlSTAT.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });


        pnlSTAT.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xOffset, y - yOffset);
            }
        });
    }

    public StatWindow(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;

        Forms.customizeButton(lblNAME, 22);
        Forms.customizeButton(lblLEVEL, 22);
        Forms.customizeButton(lblRACE, 22);
        Forms.customizeButton(lblGENDER, 22);
        Forms.customizeButton(lblNAME, 22);
        Forms.customizeButton(lblSTATUS, 33);
        Forms.customizeButton(lblNM, 26);
        Forms.customizeButton(lblLVL, 26);
        Forms.customizeButton(lblRC, 26);
        Forms.customizeButton(lblGNDR, 26);
        Forms.customizeButton(lblEXP, 26);
        Forms.customizeButton(lblPXP, 22);
        Forms.customizeButton(lblHP, 22);
        Forms.customizeButton(lblATK, 22);
        Forms.customizeButton(lblDEF, 22);
        Forms.customizeButton(lblSPD, 22);
        Forms.customizeButton(lblATKVal, 22);
        Forms.customizeButton(lblHPVal, 22);
        Forms.customizeButton(lblDEFVal, 22);
        Forms.customizeButton(lblSPDVal, 22);

        setUndecorated(true);
        setBackground(new Color(0, 0, 10, 20));
//        pnlSTAT.setOpaque(false);

        setTitle("STATUS WINDOW");

//        setAlwaysOnTop(true);
        setContentPane(pnlSTAT);
        setIconImage(logo.getImage());
        setSize(500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlSTAT.setBackground(new Color(34, 123, 219, 255));
        sep1.setBackground(new Color(217, 255, 254, 255));


        lblNAME.setText(entity.getName());
        lblLEVEL.setText(String.valueOf(entity.level));
        lblPXP.setText(entity.exp + "/" + entity.nextLevelExp);

        lblHPVal.setText(entity.life + "/" + entity.maxLife);
        lblATKVal.setText(String.valueOf(entity.strength));
        lblDEFVal.setText(String.valueOf(entity.defense));
        lblSPDVal.setText(String.valueOf(entity.speed));


        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
        pnlSTAT.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        pnlSTAT.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });
        new Timer(100, e -> updateLabels()).start();
    }

    private void updateLabels() {
        lblNAME.setText(entity.getName());
        lblLEVEL.setText(String.valueOf(entity.level));
        lblPXP.setText(entity.exp + "/" + entity.nextLevelExp);

        lblHPVal.setText(entity.life + "/" + entity.maxLife);
        lblATKVal.setText(String.valueOf(entity.strength));
        lblDEFVal.setText(String.valueOf(entity.defense));
        lblSPDVal.setText(String.valueOf(entity.speed));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (getShape() != null) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
}

