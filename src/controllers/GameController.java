package controllers;

import config.Env;
import models.Choices;
import models.GameInfo;
import models.GameModel;
import models.User;
import views.GameView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameController {
    GameView view;
    GameModel model;
    User user;
    GameInfo gameInfo;
     Choices choices;
    /**
     * Controls both the Game view and Game model. GameController adds ActionListener and FrameWindowListener.
     * @param view
     * @param model
     * @param user
     * @param gameInfo
     * @param choices
     */
    public GameController(GameView view, GameModel model, User user, GameInfo gameInfo, Choices choices){
        this.choices = choices;
        this.view = view;
        this.model = model;
        this.user = user;
        this.view.addListeners(new GameListener());
        this.view.addFrameWindowListener(new FrameWindowListener());
        this.gameInfo = gameInfo;
        view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom()).getBody());

    }

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
                if (command.equalsIgnoreCase(choices.getChoiceA())) {
                    getChoiceA();

                }
                if (command.equalsIgnoreCase(choices.getChoiceB())){

                }
                if (command.equalsIgnoreCase(choices.getChoiceC())){

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
                }
                if (command.equalsIgnoreCase("About")){
                    JOptionPane.showMessageDialog(null, "Made by Pierre Lundström", Env.GameMessageBoxTitle, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        }

    /**
     * Sets current room, then tells model to get story and links, then set story text.
     */
    void getChoiceA(){
        gameInfo.setCurrentRoom(gameInfo.getCurrentRoom() + 1);
        model.getChoiceA();
        view.gettxtStory().setText(gameInfo.getStories().get(gameInfo.getCurrentRoom()).getBody());
    }




}
