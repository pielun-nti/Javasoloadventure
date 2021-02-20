package controllers;

import config.Env;
import models.*;
import views.EditorView;
import views.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
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
    ArrayList<Link> allLinks;
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
        if (allLinks == null) {
            try {
                ResultSet rs = model.getAllLinks();
                allLinks = new ArrayList<>();
                while (rs.next()) {
                    try {
                        Link link = new Link();
                        link.setDescription(rs.getString("description"));
                        link.setStoryID(rs.getInt("story_id"));
                        link.setTargetID(rs.getInt("target_id"));
                        link.setID(rs.getInt("id"));
                        allLinks.add(link);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error when adding all links: " + ex.toString(), Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
            }
        }
        Story story = gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1);
        view.gettxtStory().setText("Story:\r\n" + story.getBody());
        view.gettxtStory().append("\r\n\r\nLinks:\r\n");
        for (int i = 0; i < allLinks.size(); i++){
            Link link = allLinks.get(i);
            if (link.getStoryID() == gameInfo.getCurrentRoom()) {
                view.getTxtStory().append("Story ID:" + link.getStoryID() + "\r\nTarget ID: " + link.getTargetID() + "\r\nDescription: " + link.getDescription() + "\r\n----------------------------\r\n");
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







}
