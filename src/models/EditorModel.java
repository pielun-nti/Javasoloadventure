package models;

import com.sun.rmi.rmid.ExecPermission;
import config.Env;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * EditorModel class that handles all the logic for Game Editor.
 */
public class EditorModel {
    DBManager dbManager;
    User user;
    GameInfo gameInfo;
    Choices choices;

    /**
     * EditorModel constructor.
     * @param user The user
     * @param gameInfo The GameInfo
     * @param choices The Choices
     */
    public EditorModel(User user, GameInfo gameInfo, Choices choices){
        dbManager = new DBManager();
        this.user = user;
        this.gameInfo = gameInfo;
        this.choices = choices;
    }

    /**
     * EditorModel constructor two that includes DBManager argument.
     * @param user
     * @param dbManager
     * @param gameInfo
     * @param choices
     */
    public EditorModel(User user, DBManager dbManager, GameInfo gameInfo, Choices choices){
        this.gameInfo = gameInfo;
        this.dbManager = dbManager;
        this.user = user;
        this.choices = choices;
    }

    /**
     * Get all links.
     * @return ResultSet with all links from database.
     */
    public ResultSet getAllLinks(){
        ResultSet rs = dbManager.selectAll("links");
        return rs;

    }

    /**
     * Get all stories.
     * @return ResultSet with all stories from database.
     */
    public ResultSet getAllStories(){
        ResultSet rs = dbManager.selectAll("story");
        return rs;

    }


    /**
     * Edit Story ID
     * @return boolean that tells if edit was successful
     */
    public boolean editStoryID(){
        try {
            int storyId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new Story ID"
                    + "\r\n" +
                    "Doing this will also edit storyid for the links connected to the current story."
                            + "\r\nRemember that id needs to be equal to the scene/room.\r\n" +
                    "First story should always have" + " ID 1, second story ID 2 etc.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));

            if (storyId < 0) {
                JOptionPane.showMessageDialog(null, "Invalid story ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            ArrayList<String> filterc = new ArrayList<>();
            ArrayList<String> filterv = new ArrayList<>();
            ArrayList<String> setc = new ArrayList<>();
            ArrayList<String> setv = new ArrayList<>();
            filterc.add("id");
            filterv.add(Integer.toString(gameInfo.getCurrentRoom()));
            setc.add("id");
            setv.add(Integer.toString(storyId));
            boolean edited = dbManager.edit("story", filterc, filterv, setc, setv);
            if (edited){
                ArrayList<String> filterco = new ArrayList<>();
                ArrayList<String> filterva = new ArrayList<>();
                ArrayList<String> setco = new ArrayList<>();
                ArrayList<String> setva = new ArrayList<>();
                filterco.add("story_id");
                filterva.add(Integer.toString(gameInfo.getCurrentRoom()));
                setco.add("story_id");
                setva.add(Integer.toString(storyId));
                boolean editedlinks = dbManager.edit("links", filterco, filterva, setco, setva);
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Edit Story Body
     * @return boolean that tells if edit was successful
     */
    public boolean editStoryBody(){
        try {
            String storyBody = JOptionPane.showInputDialog(null, "Enter new Story Body", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
            if (storyBody != null) {
                if (storyBody.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid story Body. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                    return false;
                }
            }
            ArrayList<String> filterc = new ArrayList<>();
            ArrayList<String> filterv = new ArrayList<>();
            ArrayList<String> setc = new ArrayList<>();
            ArrayList<String> setv = new ArrayList<>();
            filterc.add("id");
            filterv.add(Integer.toString(gameInfo.getCurrentRoom()));
            setc.add("body");
            setv.add(storyBody);
            boolean edited = dbManager.edit("story", filterc, filterv, setc, setv);
            if (edited){
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }




}