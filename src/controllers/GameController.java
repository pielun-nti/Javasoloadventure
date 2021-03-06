package controllers;

import config.Env;
import models.*;
import views.CustomPanel;
import views.GameView;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * GameController class that handles both the gamemodel and gameview, it acts like a controller and controls these.
 */
public class GameController {
    GameView view;
    GameModel model;
    User user;
    GameInfo gameInfo;
     Choices choices;
    /**
     * GameController Constructor that controls both the Game view and Game model. GameController adds ActionListener and FrameWindowListener.
     * @param view The GameView
     * @param model The GameModel
     * @param user The User
     * @param gameInfo The GameInfo
     * @param choices The Choices
     */
    public GameController(GameView view, GameModel model, User user, GameInfo gameInfo, Choices choices){
        this.choices = choices;
        this.view = view;
        this.model = model;
        this.user = user;
        this.view.addListeners(new GameListener());
        this.view.addFrameWindowListener(new FrameWindowListener());
        this.gameInfo = gameInfo;
        view.getmenuItemChoiceC().setVisible(false);
        view.getmenuItemChoiceA().setText(choices.getChoiceA());
        view.getmenuItemChoiceB().setText(choices.getChoiceB());
        view.getmenuItemChoiceC().setText(choices.getChoiceC());
        view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1).getBody());
    }
    /**
     * FrameWindowListener class that listens for windowevents like open, closing, closed etc.
     */
    private class FrameWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent windowEvent) {

        }

        /**
         * Closes window without terminating process. Hides window without terminating process on windowclosing.
         * @param windowEvent
         */
        @Override
        public void windowClosing(WindowEvent windowEvent) {
                    view.dispose();
            }

        @Override
        public void windowClosed(WindowEvent windowEvent) {

        }

        @Override
        public void windowIconified(WindowEvent windowEvent) {

        }

        @Override
        public void windowDeiconified(WindowEvent windowEvent) {

        }

        @Override
        public void windowActivated(WindowEvent windowEvent) {

        }

        @Override
        public void windowDeactivated(WindowEvent windowEvent) {


        }
    }
    /**
     * GameListener class that is a actionlistener that detects jmenuitems clicks.
     */
    private class GameListener implements ActionListener {
        /**
         * Listens for JMenuItem and JCheckBoxMenuItem clicks and then executes methods. Onactionperformed gets called
         * when a JMenuItem or JCheckBoxMenuItem gets clicked by user and then it executes methods.
         * @param actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            System.out.println("Executed command: " + command);
            if (command != null){
                if (command.equalsIgnoreCase("Exit application")){
                    view.dispose();
                }
                if (command.equalsIgnoreCase("Restart Game")){
                    //change current room to 1 and then get all links, stories and current story and connected
                    //links.
                }
                if (command.equalsIgnoreCase(choices.getChoiceA())) {
                    getChoiceA();
                }
                if (command.equalsIgnoreCase(choices.getChoiceB())){
                    getChoiceB();
                }
                if (command.equalsIgnoreCase(choices.getChoiceC())){
                    getChoiceC();
                }
                System.out.println("Current room: " + gameInfo.getCurrentRoom());
                if (gameInfo.getCurrentRoom() != 1 & gameInfo.getCurrentRoom() != 3){
                    view.getStoryPicture().setIcon(null);
                    view.getStoryPicture().setSize(0, 0);
                    view.getScroll().setSize(view.getWidth() - 50, view.getHeight() - 100);
                }
                if (gameInfo.getCurrentRoom() == 2){
                    view.getmenuItemChoiceA().setVisible(false);
                    view.getmenuItemChoiceB().setVisible(false);
                    view.getmenuItemChoiceC().setVisible(false);
                    CustomPanel.redSquareX = view.getWidth()/2;
                    CustomPanel.redSquareY = view.getHeight()/2;
                    CustomPanel.redSquareH = 40;
                    CustomPanel.redSquareW = 40;
                    CustomPanel.drawSquares = true;
                    CustomPanel.allowMoveRedSquare = true;
                    view.getScroll().setLocation(0, 10);
                    view.getScroll().setSize(1170, 210);
                    view.gettxtStory().setText("Move the red square to within the black square in order to continue playing.");
                    view.repaint();
                } else if (gameInfo.getCurrentRoom() == 3) {
                    CustomPanel.drawSquares = false;
                    CustomPanel.allowMoveRedSquare = false;
                    CustomPanel.drawLamp = true;
                    view.getScroll().setLocation(0, 10);
                    view.getScroll().setSize(1170, 210);
                    view.getContentPane().setBackground(Color.BLACK);
                    view.gettxtStory().setText("Click on the lamp if you dare.");
                    view.gettxtStory().setBackground(Color.GREEN);
                    view.repaint();
                    view.getmenuItemChoiceA().setVisible(false);
                    view.getmenuItemChoiceB().setVisible(false);
                    view.getmenuItemChoiceC().setVisible(false);
                }
                if ( CustomPanel.allowMoveRedSquare & CustomPanel.drawSquares & gameInfo.getCurrentRoom() != 2){
                    CustomPanel.drawSquares = false;
                    CustomPanel.allowMoveRedSquare = false;
                    view.repaint();
                    view.getmenuItemChoiceA().setVisible(true);
                    view.getmenuItemChoiceB().setVisible(true);
                    view.getmenuItemChoiceC().setVisible(true);
                }
                if (view.getContentPane().getBackground() != Color.YELLOW & gameInfo.getCurrentRoom() != 3){
                    view.getContentPane().setBackground(Color.YELLOW);
                }
                if (view.gettxtStory().getBackground() != Color.WHITE & gameInfo.getCurrentRoom() != 3){
                    view.gettxtStory().setBackground(Color.WHITE);
                }
                if (command.equalsIgnoreCase("Change font size")){
                    int fontSize = view.getFontSize();
                    try {
                        fontSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new font size ( current = " + view.getFontSize() + ")", Env.GameMessageBoxTitle, JOptionPane.INFORMATION_MESSAGE));
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        view.displayErrorMsg(ex.toString());
                    }
                    Font mainFont = new Font("Verdana", Font.BOLD, fontSize);
                    view.setMainFont(mainFont);
                    view.gettxtStory().setFont(mainFont);
                    view.setFont(mainFont);
                    view.setFontSize(fontSize);
                }
                if (command.equalsIgnoreCase("About")){
                    JOptionPane.showMessageDialog(null, "Made by Pierre Lundström", Env.GameMessageBoxTitle, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        }


    /**
     * Sets menu item text to choices. Also hides / show jmenuitem depending on if choice exist.
     */
    void setMenuItemsText(){
            if (!choices.getChoiceA().equalsIgnoreCase("a") & !choices.getChoiceA().equalsIgnoreCase(choices.getOldChoiceA())){
                view.getmenuItemChoiceA().setText(choices.getChoiceA());
                view.getmenuItemChoiceA().setVisible(true);
            } else {
                view.getmenuItemChoiceA().setVisible(false);
            }
            if (!choices.getChoiceB().equalsIgnoreCase("b") & !choices.getChoiceB().equalsIgnoreCase(choices.getOldChoiceB())){
                view.getmenuItemChoiceB().setText(choices.getChoiceB());
                view.getmenuItemChoiceB().setVisible(true);
            } else {
                view.getmenuItemChoiceB().setVisible(false);
            }
            if (!choices.getChoiceC().equalsIgnoreCase("c") & !choices.getChoiceC().equalsIgnoreCase(choices.getOldChoiceC())){
                view.getmenuItemChoiceC().setText(choices.getChoiceC());
                view.getmenuItemChoiceC().setVisible(true);
            } else {
                view.getmenuItemChoiceC().setVisible(false);
            }
        }

    /**
     * Sets current room, then tells model to get story and links, then set view story and choice in as jmenuitem text.
     */
    void getChoiceA() {
        choices.setOldChoiceA(choices.getChoiceA());
        choices.setOldChoiceB(choices.getChoiceB());
        choices.setOldChoiceC(choices.getChoiceC());
        //gameInfo.setCurrentRoom(gameInfo.getCurrentRoom() + 1);
        ArrayList<Link> links = gameInfo.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
            if (link.getDescription().equalsIgnoreCase(choices.getChoiceA())) {
                gameInfo.setCurrentRoom(link.getTargetID());
            }
        }
        boolean done = model.getChoiceA();
        if (done) {
            setMenuItemsText();
            //view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1).getBody());
            view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1).getBody());
        }

    }

    /**
     * Sets current room, then tells model to get story and links, then set view story and choice in as jmenuitem text.
     */
    void getChoiceB() {
        choices.setOldChoiceA(choices.getChoiceA());
        choices.setOldChoiceB(choices.getChoiceB());
        choices.setOldChoiceC(choices.getChoiceC());
        //gameInfo.setCurrentRoom(gameInfo.getCurrentRoom() + 1);
        ArrayList<Link> links = gameInfo.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
            if (link.getDescription().equalsIgnoreCase(choices.getChoiceB())) {
                gameInfo.setCurrentRoom(link.getTargetID());
            }
        }
        boolean done = model.getChoiceB();
        if (done) {
            setMenuItemsText();
            view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1).getBody());
        }

    }

    /**
     * Sets current room, then tells model to get story and links, then set view story and choice in as jmenuitem text.
     */
    void getChoiceC() {
        choices.setOldChoiceA(choices.getChoiceA());
        choices.setOldChoiceB(choices.getChoiceB());
        choices.setOldChoiceC(choices.getChoiceC());
        //gameInfo.setCurrentRoom(gameInfo.getCurrentRoom() + 1);
        ArrayList<Link> links = gameInfo.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
            if (link.getDescription().equalsIgnoreCase(choices.getChoiceC())) {
                gameInfo.setCurrentRoom(link.getTargetID());
            }
        }
        boolean done = model.getChoiceC();
        if (done) {
            setMenuItemsText();
            view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1).getBody());
        }

    }





}
