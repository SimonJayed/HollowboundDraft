package forms;

import javax.swing.*;

public interface Forms {
    static void customizeButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
    static void customizeButton(JRadioButton button) {
        button.setBorderPainted(false);
//        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
}
