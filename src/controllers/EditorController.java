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
    /**
     * Controls both the Editor view and Editor model. EditorController adds ActionListener and FrameWindowListener.
     * @param view
     * @param model
     * @param user
     * @param gameInfo
     */
    public EditorController(EditorView view, EditorModel model, User user, GameInfo gameInfo){
        this.view = view;
        this.model = model;
        this.user = user;
        this.view.addListeners(new EditorListener());
        this.view.addFrameWindowListener(new FrameWindowListener());
        this.gameInfo = gameInfo;
        refreshStoryAndLinksFromDatabase();
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
     * Gets story and links from database.
     */
    void refreshStoryAndLinksFromDatabase(){
        try {
            ResultSet rs = model.getAllLinks();
            ArrayList<Link> allLinks = new ArrayList<>();
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
            gameInfo.setLinks(allLinks);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error when adding all links: " + ex.toString(), Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
        }
        try {
            ResultSet rs = model.getAllStories();
            ArrayList<Story> stories = new ArrayList<>();

            while (rs.next()) {
                try {
                    Story story = new Story();
                    story.setID(rs.getInt("id"));
                    story.setBody(rs.getString("body"));
                    stories.add(story);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            gameInfo.setStories(stories);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error when adding all stories: " + ex.toString(), Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Show story and links for current scene/room on jtextarea.
     */
    void addStoryAndLinksToTextArea() {
        try {
            Story story = gameInfo.getStories().get(gameInfo.getCurrentRoom() - 1);
            view.gettxtStory().setText("Body for this story:\r\n" + story.getBody());
            view.gettxtStory().append("\r\n\r\nLinks for this story:\r\n----------------------------\r\n");
            for (int i = 0; i < gameInfo.getLinks().size(); i++) {
                Link link = gameInfo.getLinks().get(i);
                if (link.getStoryID() == gameInfo.getCurrentRoom()) {
                    view.getTxtStory().append("Link ID: " + link.getID() + "\r\nStory ID:" + link.getStoryID() + "\r\nTarget ID: " + link.getTargetID() + "\r\nDescription: " + link.getDescription() + "\r\n----------------------------\r\n");
                }
            }
        } catch (Exception ex){
            System.err.println("Error occured when getting story and links for current scene/room.\r\n" +
                    "Maybe the story ID is incorrect.");
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
                try {
                    String scene = (String) event.getItem();
                    String[] args = scene.split("\\|");
                    int storyId = Integer.parseInt(args[0].trim());
                    gameInfo.setCurrentRoom(storyId);
                    addStoryAndLinksToTextArea();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
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
     * EditorListener class that is a actionlistener that detects jmenuitems clicks.
     */
    private class EditorListener implements ActionListener {
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
                if (command.equalsIgnoreCase("Edit Story ID")){
                    editStoryID();
                }
                if (command.equalsIgnoreCase("Edit Story Body")){
                    editStoryBody();
                }
                if (command.equalsIgnoreCase("Add New Story")){
                    addNewStory();
                }
                if (command.equalsIgnoreCase("Add New Link")){
                    addNewLink();
                }
                if (command.equalsIgnoreCase("Refresh stories and links")){
                    //get all stories from db again and add to combobox.
                    refreshStoryAndLinksFromDatabase();
                }
                if (command.equalsIgnoreCase("Edit Link")){
                    //ask user for link target id and then allow edit.
                    editLink();
                }
                if (command.equalsIgnoreCase("Delete Link")){
                    deleteLink();
                }
                if (command.equalsIgnoreCase("Delete Story")){
                    deleteStory();
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
     * Tells the EditorModel to delete a story from database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void deleteStory() {
        boolean success = model.deleteStory();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }
    /**
     * Tells the EditorModel to delete a link from database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void deleteLink() {
        boolean success = model.deleteLink();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }

    /**
     * Tells the EditorModel to add a new link to database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void addNewLink() {
        boolean success = model.addNewLink();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }
    /**
     * Tells the EditorModel to edit existing link to database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void editLink() {
       boolean success = model.editLink();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }
    /**
     * Tells the EditorModel to add a new story to database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void addNewStory() {
        boolean success = model.addNewStory();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }
    /**
     * Tells the EditorModel to edit existing story body to database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void editStoryBody() {
        boolean success = model.editStoryBody();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }
    /**
     * Tells the EditorModel to edit existing story id to database. If success then it updates the JComboBox
     * with the new stories.
     */
    private void editStoryID() {
        boolean success = model.editStoryID();
        if (success){
            //update stories in combobox etc.
            refreshStoryAndLinksFromDatabase();
            view.getSceneSelector().removeAllItems();
            addScenesToComboBox();
        } else {

        }
    }


}
