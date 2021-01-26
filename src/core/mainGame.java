package core;

import config.Env;
import models.DBManager;
import models.GameInfo;
import models.Link;
import models.Story;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Java solo adventure gjord av Pierre
 * Innan du startar programmet, fyll i dina databasvariabler i src/config/Env.java filen
 */
public class mainGame {

    /**
     * Main method. Starts the game. Uses EventQueue.invokeLater to run after other UI swing events have been run to prevent UI lag. Tries to make GUI look more alike windows programs GUIs by using UIManager.
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                try {
                    GameInfo gameInfo = new GameInfo();
                    gameInfo.setCurrentRoom(1);
                    DBManager dbManager = new DBManager();
                    ArrayList<String> co = new ArrayList<>();
                    ArrayList<String> va = new ArrayList<>();
                    co.add("id");
                    va.add(Integer.toString(gameInfo.getCurrentRoom()));
                    ResultSet rs = dbManager.selectAllWhere("story", co, va);
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "No story for first scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                        System.exit(2);
                    } else {
                        ArrayList<Story> stories = gameInfo.getStories();
                        if (stories == null){
                            stories = new ArrayList<>();
                        }
                        Story story = new Story();
                        story.setID(gameInfo.getCurrentRoom());
                        story.setBody(rs.getString("body"));
                        stories.add(story);
                        ArrayList<String> col= new ArrayList<>();
                        ArrayList<String> val = new ArrayList<>();
                        co.add("story_id");
                        va.add(Integer.toString(gameInfo.getCurrentRoom()));
                        ResultSet linksrs = dbManager.selectAllWhere("links", co, va);
                        if (!linksrs.next()){
                            JOptionPane.showMessageDialog(null, "No story for first scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                            System.exit(2);
                        } else {
                            ArrayList<Link> links = gameInfo.getLinks();
                            if (links == null){
                                links = new ArrayList<>();
                            }
                            Link link = new Link();
                            link.setID(gameInfo.getCurrentRoom());
                            link.setDescription(linksrs.getString("description"));
                            link.setStoryID(gameInfo.getCurrentRoom());
                            link.setTargetID(Integer.parseInt(linksrs.getString("target_id")));
                            links.add(link);
                            System.out.println(link);
                            System.out.println(story);
                                    /*LoginView loginView = new LoginView();
                LoginModel loginModel = new LoginModel();
                LoginController loginController = new LoginController(loginView, loginModel);
                loginView.setVisible(true);
                loginView.getTxtUsername().requestFocus();*/
                        }
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}