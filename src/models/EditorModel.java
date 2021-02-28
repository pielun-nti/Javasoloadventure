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

    /**
     * EditorModel constructor.
     * @param user The user
     * @param gameInfo The GameInfo
     */
    public EditorModel(User user, GameInfo gameInfo){
        dbManager = new DBManager();
        this.user = user;
        this.gameInfo = gameInfo;
    }

    /**
     * EditorModel constructor two that includes DBManager argument.
     * @param user
     * @param dbManager
     * @param gameInfo
     */
    public EditorModel(User user, DBManager dbManager, GameInfo gameInfo){
        this.gameInfo = gameInfo;
        this.dbManager = dbManager;
        this.user = user;
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
     * Add new Link to database
     * @return boolean that tells if insert was successful
     */
    public boolean addNewLink(){
        try {
            int linkId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID for new Link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));

            if (linkId < 0) {
                JOptionPane.showMessageDialog(null, "Invalid Link ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(linkId));
            ResultSet rs = dbManager.selectAllWhere("links", co, va);
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "A link with that ID already exist.\r\nYou cannot create duplicate links.", Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String desc = JOptionPane.showInputDialog(null, "Enter Description for new Link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
            if (desc == null) {
                JOptionPane.showMessageDialog(null, "Invalid Link Description. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            if (desc.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid Link Description. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            int newStoryID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Story ID for new link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));
            if (newStoryID < 0) {
                JOptionPane.showMessageDialog(null, "Invalid Story ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            int newTargetID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Target ID for new link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));
            if (newTargetID < 0) {
                JOptionPane.showMessageDialog(null, "Invalid Target ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            ArrayList<String> setc = new ArrayList<>();
            ArrayList<String> setv = new ArrayList<>();
            setc.add("id");
            setv.add(Integer.toString(linkId));
            setc.add("description");
            setv.add(desc);
            setc.add("target_id");
            setv.add(Integer.toString(newTargetID));
            setc.add("story_id");
            setv.add(Integer.toString(newStoryID));
            boolean inserted = dbManager.insert("links", setc, setv);
            if (inserted){
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     *
     * Edit Link
     * @return boolean that tells if edit was successful
     */
    public boolean editLink(){
        int linkId = -1;
        int newLinkID = -1;
        int newStoryID = -1;
        int newTargetID = -1;
        String newDesc = null;
        try {
            linkId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Link ID of the link you want to edit", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));
            if (linkId < 0) {
                JOptionPane.showMessageDialog(null, "Invalid Link ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(linkId));
            ResultSet rs = dbManager.selectAllWhere("links", co, va);
            if (!rs.next()){
                JOptionPane.showMessageDialog(null, "A link with that ID does not exist.", Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            newLinkID = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Enter new ID for this link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE, null, null, Integer.toString(rs.getInt("id"))));
            if (newLinkID < 0) {
                JOptionPane.showMessageDialog(null, "Invalid New Link ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            newStoryID = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Enter new Story ID for this link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE, null, null, Integer.toString(rs.getInt("story_id"))));
            if (newStoryID < 0) {
                JOptionPane.showMessageDialog(null, "Invalid New Story ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            newTargetID = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Enter new Target ID for this link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE, null, null, Integer.toString(rs.getInt("target_id"))));
            if (newTargetID < 0) {
                JOptionPane.showMessageDialog(null, "Invalid New Target ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            newDesc = (String) JOptionPane.showInputDialog(null, "Enter new Description for this link", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE, null, null, rs.getString("description"));
            if (newDesc == null){
                JOptionPane.showMessageDialog(null, "New Link Description cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (newDesc.trim().equals("")){
                JOptionPane.showMessageDialog(null, "New Link Description cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            ArrayList<String> filterc = new ArrayList<>();
            ArrayList<String> filterv = new ArrayList<>();
            ArrayList<String> setc = new ArrayList<>();
            ArrayList<String> setv = new ArrayList<>();
            filterc.add("id");
            filterv.add(Integer.toString(linkId));
            setc.add("id");
            setv.add(Integer.toString(newLinkID));
            setc.add("description");
            setv.add(newDesc);
            setc.add("target_id");
            setv.add(Integer.toString(newTargetID));
            setc.add("story_id");
            setv.add(Integer.toString(newStoryID));
            boolean edited = dbManager.edit("links", filterc, filterv, setc, setv);
            if (edited){
                return true;
            } else {
                //another backup method, delete and then create new link
                ArrayList<String> c = new ArrayList<>();
                ArrayList<String> v = new ArrayList<>();
                c.add("id");
                v.add(Integer.toString(linkId));
                boolean deleted = dbManager.delete("links", c, v);
                if (deleted) {
                    ArrayList<String> setca = new ArrayList<>();
                    ArrayList<String> setva = new ArrayList<>();
                    setca.add("id");
                    setva.add(Integer.toString(linkId));
                    setca.add("description");
                    setva.add(newDesc);
                    setca.add("target_id");
                    setva.add(Integer.toString(newTargetID));
                    setca.add("story_id");
                    setva.add(Integer.toString(newStoryID));
                    boolean inserted = dbManager.insert("links", setca, setva);
                    if (inserted) {
                        return true;
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            try {
                ArrayList<String> co = new ArrayList<>();
                ArrayList<String> va = new ArrayList<>();
                co.add("id");
                va.add(Integer.toString(linkId));
                ResultSet rs = dbManager.selectAllWhere("links", co, va);
                if (rs.next()) {
                    //another backup method, delete and then create new link
                    ArrayList<String> c = new ArrayList<>();
                    ArrayList<String> v = new ArrayList<>();
                    c.add("id");
                    v.add(Integer.toString(linkId));
                    boolean deleted = dbManager.delete("links", c, v);
                    if (deleted) {
                        ArrayList<String> setca = new ArrayList<>();
                        ArrayList<String> setva = new ArrayList<>();
                        setca.add("id");
                        setva.add(Integer.toString(linkId));
                        setca.add("description");
                        setva.add(newDesc);
                        setca.add("target_id");
                        setva.add(Integer.toString(newTargetID));
                        setca.add("story_id");
                        setva.add(Integer.toString(newStoryID));
                        boolean inserted = dbManager.insert("links", setca, setva);
                        if (inserted) {
                            return true;
                        }
                    }
                }
            } catch (Exception ex2){
                ex2.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Add new Story to database
     * @return boolean that tells if insert was successful
     */
    public boolean addNewStory(){
        try {
            int storyId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID for new Story", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE));

            if (storyId < 0) {
                JOptionPane.showMessageDialog(null, "Invalid story ID. Must be 0 or greater.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            //check here if a story exist with that id. if it does then show error and return false;
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(storyId));
            ResultSet rs = dbManager.selectAllWhere("story", co, va);
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "A story with that ID already exist.\r\nYou cannot create duplicate stories.", Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String body = JOptionPane.showInputDialog(null, "Enter Body for new Story", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
            if (body == null) {
                JOptionPane.showMessageDialog(null, "Invalid story Body. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            if (body.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid story Body. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            ArrayList<String> setc = new ArrayList<>();
            ArrayList<String> setv = new ArrayList<>();
            setc.add("id");
            setv.add(Integer.toString(storyId));
            setc.add("body");
            setv.add(body);
            boolean inserted = dbManager.insert("story", setc, setv);
            if (inserted){
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
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
            if (storyBody == null) {
                JOptionPane.showMessageDialog(null, "Invalid story Body. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
            }
            if (storyBody.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid story Body. Cannot be empty.", Env.EditorMessageBoxTitle, JOptionPane.QUESTION_MESSAGE);
                return false;
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