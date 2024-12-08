package anim;

import forms.GameWindowForm;
import main.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class shrik extends GameWindowForm {
    private JPanel panel;
    private JLabel animationLabel;
    private Timer timer;
    private int frameIndex = 0;
    private String[] frames;

    public void generateFrames() {
        frames = new String[108];

        for (int i = 0; i <= 107; i++) {
            String frameNumber = String.format("%03d", i);

            frames[i] = "src/anim/shrik2anim/frame_" + frameNumber + "_delay-0.07s.png";
        }
    }

    public shrik() {
        generateFrames();

        setSize(800, 600);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        animationLabel = new JLabel();
        animationLabel.setHorizontalAlignment(JLabel.CENTER);
        animationLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(animationLabel, BorderLayout.CENTER);
        add(panel);


        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameIndex < frames.length) {
                    String currentFrame = frames[frameIndex];
                    if (isImageAvailable(currentFrame)) {
                        ImageIcon icon = new ImageIcon(currentFrame);

                        Image scaledImage = icon.getImage().getScaledInstance(
                                panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH
                        );
                        animationLabel.setIcon(new ImageIcon(scaledImage));
                    }
//                    else {
//                        System.err.println("Warning: Image not found -> " + currentFrame);
//                    }


                    frameIndex++;
                } else {
                    timer.stop();

                    JFrame window = new JFrame();
                    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setSize(768, 576);
                    window.setTitle("REMAIN");

                    ImageIcon logo = new ImageIcon("./img/dino1.png");
                    window.setIconImage(logo.getImage());

                    GamePanel gamePanel = new GamePanel();
                    window.add(gamePanel);

                    window.pack();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);

                    gamePanel.setupGame();
                    gamePanel.startGameThread();

                    dispose();
                }
            }
        });

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                if (panel.getWidth() > 0 && panel.getHeight() > 0) {
                    timer.start();
                    removeComponentListener(this);
                }
            }
        });

        setVisible(true);
    }

    private boolean isImageAvailable(String imagePath) {
        File file = new File(imagePath);
        return file.exists() && !file.isDirectory();
    }
}
