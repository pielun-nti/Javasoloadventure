package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Custom JPanel class that I made so I can change what I like and paint stuff using it.
 */
public class CustomPanel extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    int fontSize = 18;
    Font mainFont;
    public static boolean allowMoveSquare;
    public static boolean drawSquare;
    public static boolean drawLamp;
    /**
     * CustomPanel constructor with no arguments. It creates the font and adds mouse and mouse motion listeners.
     */
    public CustomPanel() {

        //setBorder(BorderFactory.createLineBorder(Color.black));
        mainFont = new Font("Verdana", Font.BOLD, fontSize);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

    }

    /**
     * Repaints square at x and y position on this JPanel.
     * @param x The x position to paint at
     * @param y The y position to paint at
     */
    private void moveSquare(int x, int y) {
        if (allowMoveSquare) {
            int OFFSET = 1;
            if ((squareX != x) || (squareY != y)) {
                repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
                squareX = x;
                squareY = y;
                repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
            }
        }
    }

    /**
     * This method gets called when the Custom JPanel is created or when called repaint(). It uses graphics to
     * draw shapes.
     * @param g The graphics argument that allows to draw stuff on the screen.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.setFont(mainFont);
        g.drawString("Solo adventure made in java by Pierre",30,getHeight() - 10);
        g.setColor(Color.RED);
        if (drawSquare) {
            g.fillRect(squareX, squareY, squareW, squareH);
            g.setColor(Color.BLACK);
            g.drawRect(squareX, squareY, squareW, squareH);
        }
        g.fillOval(10, getHeight() - 20, 10, 10);
        g.setColor(Color.black);
        g.drawOval(10,getHeight() - 20,10,10);
    }
}