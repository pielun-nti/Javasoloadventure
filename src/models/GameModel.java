package models;

import config.Env;
import sun.security.pkcs11.Secmod;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * GameModel class that does all the logic for the game like for example executing updates/queries from database and returns info to the GameController.
 */
public class GameModel {
    /**
     * The database manager.
     */
    DBManager dbManager;
    /**
     * The user.
     */
    User user;
    /**
     * The Gameinfo class.
     */
    GameInfo gameInfo;
    /**
     * The choices class.
     */
    Choices choices;

    /**
     * GameModel constructor.
     * @param user The user
     * @param gameInfo Game info class
     * @param choices All the current choices for the current scene
     */
    public GameModel(User user, GameInfo gameInfo, Choices choices){
        dbManager = new DBManager();
        this.user = user;
        this.gameInfo = gameInfo;
        this.choices = choices;
    }

    /**
     * Gamemodel constructor two.
     * @param user The user
     * @param dbManager The database manager
     * @param gameInfo Game info class
     * @param choices All the current choices for the current scene
     */
    public GameModel(User user, DBManager dbManager, GameInfo gameInfo, Choices choices){
        this.gameInfo = gameInfo;
        this.dbManager = dbManager;
        this.user = user;
        this.choices = choices;
    }


    /**
     * Get choice A from database links and get story, links for the story etc.
     * @return if the story, links and choices was found successful or not
     */
    public boolean getChoiceA(){
        try {
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(gameInfo.getCurrentRoom()));
            ResultSet rs = dbManager.selectAllWhere("story", co, va);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No story for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                //completed story
                System.exit(2);
            } else {
                ArrayList<String> col = new ArrayList<>();
                ArrayList<String> val = new ArrayList<>();
                col.add("story_id");
                val.add(Integer.toString(gameInfo.getCurrentRoom()));
                ResultSet linksrs = dbManager.selectAllWhere("links", col, val);
                ArrayList<Link> links = gameInfo.getLinks();
                if (links == null) {
                    links = new ArrayList<>();
                }
                int numberOfChoices = 0;
                while (linksrs.next()) {
                    Link link = new Link();
                    link.setID(gameInfo.getCurrentRoom());
                    link.setDescription(linksrs.getString("description"));
                    link.setStoryID(gameInfo.getCurrentRoom());
                    link.setTargetID(Integer.parseInt(linksrs.getString("target_id")));
                    links.add(link);
                    System.out.println(link.toString());
                    if (numberOfChoices == 0) {
                        choices.setChoiceA(linksrs.getString("description"));
                    } else if (numberOfChoices == 1) {
                        choices.setChoiceB(linksrs.getString("description"));
                    } else if (numberOfChoices == 2) {
                        choices.setChoiceC(linksrs.getString("description"));
                    }
                    numberOfChoices++;
                }
                if (numberOfChoices <= 0){
                    JOptionPane.showMessageDialog(null, "No choices for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                    //completed game
                    System.exit(2);
                } else {
                    choices.setNumberOfChoices(numberOfChoices);
                    System.out.println("Choice A:" + choices.getChoiceA());
                    System.out.println("Choice B:" + choices.getChoiceB());
                    System.out.println("Choice C:" + choices.getChoiceC());
                    return true;
                }

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Get choice B from database links and get story, links for the story etc.
     * @return if the story, links and choices was found successful or not
     */
    public boolean getChoiceB(){
        try {
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(gameInfo.getCurrentRoom()));
            ResultSet rs = dbManager.selectAllWhere("story", co, va);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No story for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                //completed story
                System.exit(2);
            } else {

                ArrayList<String> col = new ArrayList<>();
                ArrayList<String> val = new ArrayList<>();
                col.add("story_id");
                val.add(Integer.toString(gameInfo.getCurrentRoom()));
                ResultSet linksrs = dbManager.selectAllWhere("links", col, val);

                    ArrayList<Link> links = gameInfo.getLinks();
                    if (links == null) {
                        links = new ArrayList<>();
                    }
                    int numberOfChoices = 0;
                    while (linksrs.next()) {
                        Link link = new Link();
                        link.setID(gameInfo.getCurrentRoom());
                        link.setDescription(linksrs.getString("description"));
                        link.setStoryID(gameInfo.getCurrentRoom());
                        link.setTargetID(Integer.parseInt(linksrs.getString("target_id")));
                        links.add(link);
                        System.out.println(link.toString());
                        if (numberOfChoices == 0) {
                            choices.setChoiceA(linksrs.getString("description"));
                        } else if (numberOfChoices == 1) {
                            choices.setChoiceB(linksrs.getString("description"));
                        } else if (numberOfChoices == 2) {
                            choices.setChoiceC(linksrs.getString("description"));
                        }
                        numberOfChoices++;
                    }
                    if (numberOfChoices <= 0){
                        JOptionPane.showMessageDialog(null, "No choices for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                        //completed game
                        System.exit(2);
                    } else {
                        choices.setNumberOfChoices(numberOfChoices);
                        System.out.println("Choice A:" + choices.getChoiceA());
                        System.out.println("Choice B:" + choices.getChoiceB());
                        System.out.println("Choice C:" + choices.getChoiceC());
                        return true;
                    }

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Get choice C from database links and get story, links for the story etc.
     * @return if the story, links and choices was found successful or not
     */
    public boolean getChoiceC(){
        try {
            ArrayList<String> co = new ArrayList<>();
            ArrayList<String> va = new ArrayList<>();
            co.add("id");
            va.add(Integer.toString(gameInfo.getCurrentRoom()));
            ResultSet rs = dbManager.selectAllWhere("story", co, va);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No story for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                //completed story
                System.exit(2);
            } else {
                ArrayList<String> col = new ArrayList<>();
                ArrayList<String> val = new ArrayList<>();
                col.add("story_id");
                val.add(Integer.toString(gameInfo.getCurrentRoom()));
                ResultSet linksrs = dbManager.selectAllWhere("links", col, val);

                ArrayList<Link> links = gameInfo.getLinks();
                if (links == null) {
                    links = new ArrayList<>();
                }
                int numberOfChoices = 0;
                while (linksrs.next()) {
                    Link link = new Link();
                    link.setID(gameInfo.getCurrentRoom());
                    link.setDescription(linksrs.getString("description"));
                    link.setStoryID(gameInfo.getCurrentRoom());
                    link.setTargetID(Integer.parseInt(linksrs.getString("target_id")));
                    links.add(link);
                    System.out.println(link.toString());
                    if (numberOfChoices == 0) {
                        choices.setChoiceA(linksrs.getString("description"));
                    } else if (numberOfChoices == 1) {
                        choices.setChoiceB(linksrs.getString("description"));
                    } else if (numberOfChoices == 2) {
                        choices.setChoiceC(linksrs.getString("description"));
                    }
                    numberOfChoices++;
                }
                if (numberOfChoices <= 0){
                    JOptionPane.showMessageDialog(null, "No choices for next scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                    //completed game
                    System.exit(2);
                } else {
                    choices.setNumberOfChoices(numberOfChoices);
                    System.out.println("Choice A:" + choices.getChoiceA());
                    System.out.println("Choice B:" + choices.getChoiceB());
                    System.out.println("Choice C:" + choices.getChoiceC());
                    return true;
                }

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}