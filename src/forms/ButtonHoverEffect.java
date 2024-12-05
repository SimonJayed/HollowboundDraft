package forms;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class ButtonHoverEffect implements MouseListener {
    private final Color hoverColor; // Color when mouse enters
    private final Color defaultColor; // Original button color

    public ButtonHoverEffect(Color hoverColor, Color defaultColor) {
        this.hoverColor = hoverColor;
        this.defaultColor = defaultColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No specific behavior on click
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No specific behavior on press
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No specific behavior on release
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Change the button's background color when hovered
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setForeground(hoverColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Reset the button's background color when the mouse exits
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setForeground(defaultColor);
        }
    }
}
