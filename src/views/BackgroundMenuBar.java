package views;

import javax.swing.*;
import java.awt.*;

/**
 * Custom jmenubar with background color.
 */
public class BackgroundMenuBar extends JMenuBar {
    Color bgColor=Color.YELLOW;

    /**
     * Sets background color
     * @param color
     */
    public void setColor(Color color) {
        bgColor=color;
    }

    /**
     * Sets color of jmenubar and paints it.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
}
