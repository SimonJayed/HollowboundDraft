package interfaces;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public interface Forms {


    static void customizeButton(JButton button, float fontSize) {
        Font customFont;

        try {
            InputStream fontStream = Forms.class.getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
        }

        button.setFont(customFont);

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
    static void customizeButton(JRadioButton button, float fontSize) {
        Font customFont;

        try {
            InputStream fontStream = Forms.class.getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
        }

        button.setFont(customFont);


        button.setBorderPainted(false);
//        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
    static void customizeButton(JLabel label, float fontSize) {
        Font customFont;

        try {
            InputStream fontStream = Forms.class.getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
        }

        label.setFont(customFont);
    }
    static void customizeButton(JTextField tField, float fontSize) {
        Font customFont;

        try {
            InputStream fontStream = Forms.class.getResourceAsStream("/fonts/font1.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
        }

        tField.setFont(customFont);
    }
}
