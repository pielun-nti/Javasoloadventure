package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Custom JPanel class that I made so I can change what I like and paint stuff using it.
 */
public class CustomPanel extends JPanel {

    public static int redSquareX = 50;
    public static int redSquareY = 50;
    public static int redSquareW = 20;
    public static int redSquareH = 20;
    int blackSquareX = 20;
    int blackSquareY = 500;
    int blackSquareW = 80;
    int blackSquareH = 80;
    int fontSize = 18;
    Font mainFont;
    public static boolean allowMoveRedSquare;
    public static boolean drawSquares;
    public static boolean drawLamp;
    GameView view;
    /**
     * CustomPanel constructor with no arguments. It creates the font and adds mouse and mouse motion listeners.
     */
    public CustomPanel(GameView view) {
        this.view = view;
        //setBorder(BorderFactory.createLineBorder(Color.black));
        mainFont = new Font("Verdana", Font.BOLD, fontSize);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveRedSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveRedSquare(e.getX(),e.getY());
            }
        });

    }

    /**
     * Repaints redSquare at x and y position on this JPanel.
     * If red square is at black square location then continue playing and show jmenuitems and story text etc.
     * @param x The x position to paint at
     * @param y The y position to paint at
     */
    private void moveRedSquare(int x, int y) {
        if (allowMoveRedSquare) {
            int OFFSET = 1;
            if (x <= (blackSquareX + 50) & x > (blackSquareX - 50) & y >= (blackSquareY - 50) & y <= (blackSquareY + 50)){
                drawSquares = false;
                allowMoveRedSquare = false;
                repaint();
                view.getmenuItemChoiceA().setVisible(true);
                view.getmenuItemChoiceB().setVisible(true);
                view.getmenuItemChoiceC().setVisible(true);
                view.gettxtStory().setText(view.getGameInfo().getStories().get(view.getGameInfo().getCurrentRoom() - 1).getBody());
                view.getScroll().setSize(view.getWidth() - 50, view.getHeight() - 100);

            }
            if ((redSquareX != x) || (redSquareY != y)) {
                repaint(redSquareX, redSquareY, redSquareW + OFFSET, redSquareH + OFFSET);
                redSquareX = x;
                redSquareY = y;
                repaint(redSquareX, redSquareY, redSquareW + OFFSET, redSquareH + OFFSET);
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
        if (drawSquares) {
            g.setColor(Color.BLACK);
            g.fillRect(blackSquareX, blackSquareY, blackSquareW, blackSquareH);
            g.drawRect(blackSquareX, blackSquareY, blackSquareW, blackSquareH);
            g.setColor(Color.RED);
            g.fillRect(redSquareX, redSquareY, redSquareW, redSquareH);
            g.drawRect(redSquareX, redSquareY, redSquareW, redSquareH);
        }
        if (drawLamp){
        try {
            Image firstImg = ImageIO.read(
                    getClass().getResource("./img/lampblack.jpg"));
            Image resizedImg = firstImg.getScaledInstance(view.getWidth()/2, view.getHeight()/2,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon firstIcon = new ImageIcon(resizedImg);
            view.getStoryPicture().setLocation(460, 330);
            view.getStoryPicture().setSize((view.getWidth()/2) - 20, (view.getHeight()/2) - 20);
            view.getStoryPicture().setIcon(firstIcon);
            view.getStoryPicture().addMouseListener(new MyMouseListener());
        } catch (Exception e) {
            e.printStackTrace();
            drawLamp = false;
            view.gettxtStory().setText(view.getGameInfo().getStories().get(view.getGameInfo().getCurrentRoom() - 1).getBody());
            view.gettxtStory().setBackground(Color.WHITE);
            view.getmenuItemChoiceA().setVisible(true);
            view.getmenuItemChoiceB().setVisible(true);
            view.getmenuItemChoiceC().setVisible(true);
            repaint();
        }
        }
        g.setColor(Color.BLUE);
        g.drawLine(5, getHeight() - 5, 420, getHeight() - 5);

        g.setColor(Color.black);
        g.fillOval(10, getHeight() - 20, 10, 10);
        g.drawOval(10,getHeight() - 20,10,10);
    }

    /**
     * MouseListener class for do stuff when an object is clicked with mouse.
     */
    private class MyMouseListener implements MouseListener {

        /**
         * When user clicks on the lamp image it will show the story text and show an image of an angry wolf
         * and allow user to select choices from menu items.
         * @param e The mouse event
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            drawLamp = false;
            view.gettxtStory().setText(view.getGameInfo().getStories().get(view.getGameInfo().getCurrentRoom() - 1).getBody());
            new Thread(new Runnable() { //new thread to fix lag/wait
                @Override
                public void run() {
                    try {
                        view.getScroll().setLocation(0, 10);
                        view.getScroll().setSize(1170, 210);
                        Image firstImg = ImageIO.read(
                                getClass().getResource("./img/argvarg.jpg"));
                        Image resizedImg = firstImg.getScaledInstance(view.getWidth()/2, view.getHeight()/2,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        ImageIcon firstIcon = new ImageIcon(resizedImg);
                        view.getStoryPicture().setLocation(10, 230);
                        view.getStoryPicture().setSize(view.getWidth()/2, view.getHeight()/2);
                        view.getStoryPicture().setIcon(firstIcon);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
            view.getmenuItemChoiceA().setVisible(true);
            view.getmenuItemChoiceB().setVisible(true);
            view.getmenuItemChoiceC().setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}