package controllers;

import config.Env;
import models.*;
import views.EditorView;
import views.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Editorcontroller class that handles both the gamemodel and gameview, it acts like a controller and controls these.
 */
public class EditorController {
    EditorView view;
    EditorModel model;
    User user;
    GameInfo gameInfo;
     Choices choices;
    /**
     * Controls both the Editor view and Editor model. EditorController adds ActionListener and FrameWindowListener.
     * @param view
     * @param model
     * @param user
     * @param gameInfo
     * @param choices
     */
    public EditorController(EditorView view, EditorModel model, User user, GameInfo gameInfo, Choices choices){
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
        addScenesToComboBox();
        addStoryAndLinksToTextArea();
        view.addItemListeners(new ItemChangeListener());
    }

    /**
     * Adds scenes/stories text and id to jcombobox.
     */
    void addScenesToComboBox(){
        for (int i = 0; i < gameInfo.getStories().size(); i++){
            Story story = gameInfo.getStories().get(i);
            if (story.getBody() != null){
                if (!story.getBody().trim().equals("") & !story.getBody().trim().equalsIgnoreCase("Kommer sen")) {
                    view.getSceneSelector().addItem(story.getID() + " | " + story.getBody());
                }
            }
        }
    }

    /**
     * Show story and links for current scene/room on jtextarea.
     */
    void addStoryAndLinksToTextArea(){
        Story story = gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1);
        view.gettxtStory().setText("Story:\r\n" + story.getBody());
        view.gettxtStory().append("\r\n\r\nLinks:\r\n");
        for (int i = 0; i < gameInfo.getLinks().size(); i++){
            Link link = gameInfo.getLinks().get(i);
            if (link.getStoryID() == gameInfo.getCurrentRoom()) {
                view.getTxtStory().append("Story ID:" + link.getStoryID() + "\r\nTarget ID: " + link.getTargetID() + "\r\nDescription: " + link.getDescription() + "\r\n----------------------------\r\n");
            } else {
                System.out.println("Konstigt: " + link.getStoryID());
            }
        }
    }

    /**
     * ItemListener that listens for JComboBox item clicks.
     */
    private class ItemChangeListener implements ItemListener{
        /**
         * Sets current room depending on story id for clicked item and then shows story and links for the current scene selected when user clicks on a scene in the JComboBox.
         * I get the story id from the whole item string by splitting it and parsing it to an int. Using trim() to remove blank spaces.
         * @param event The itemevent that happens when user click on a JComboBox item.
         */
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                String scene = (String) event.getItem();
                String[] args = scene.split("\\|");
                int storyId = Integer.parseInt(args[0].trim());
                gameInfo.setCurrentRoom(storyId);
                addStoryAndLinksToTextArea();
            }
        }
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
                if (command.equalsIgnoreCase(choices.getChoiceA())) {
                    getChoiceA();

                }
                if (command.equalsIgnoreCase(choices.getChoiceB())){
                    getChoiceB();
                }
                if (command.equalsIgnoreCase(choices.getChoiceC())){
                    getChoiceC();
                }
                if (command.equalsIgnoreCase("Select scene")) {
                    //selectScene();

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
