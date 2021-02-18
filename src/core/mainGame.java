package core;

import config.Env;
import controllers.GameController;
import models.*;
import views.GameView;

import javax.imageio.ImageIO;
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
                    DB db = new DB();
                    db.initDB();
                    DBManager dbManager = new DBManager(db);
                   /* ArrayList<String> co = new ArrayList<>();
                    ArrayList<String> va = new ArrayList<>();
                    co.add("id");
                    va.add(Integer.toString(gameInfo.getCurrentRoom()));*/
                    ResultSet rs = dbManager.selectAll("story");
                    int storyamount = 0;
                    ArrayList<Story> stories = gameInfo.getStories();
                    if (stories == null){
                        stories = new ArrayList<>();
                    }
                    while (rs.next()) {
                        Story story = new Story();
                        story.setID(rs.getInt("id"));
                        story.setBody(rs.getString("body"));
                        stories.add(story);
                        storyamount++;
                    }
                    gameInfo.setStories(stories);
                    if (storyamount == 0) {
                        JOptionPane.showMessageDialog(null, "No story for first scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                        System.exit(2);
                    }
                        ArrayList<String> col= new ArrayList<>();
                        ArrayList<String> val = new ArrayList<>();
                        col.add("story_id");
                        val.add(Integer.toString(gameInfo.getCurrentRoom()));
                        ResultSet linksrs = dbManager.selectAllWhere("links", col, val);
                        if (!linksrs.next()){
                            JOptionPane.showMessageDialog(null, "No story for first scene exists.", Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
                            System.exit(2);
                        } else {
                            ArrayList<Link> links = gameInfo.getLinks();
                            if (links == null){
                                links = new ArrayList<>();
                            }
                            User user = new User();
                            user.setUsername("test");
                            user.setAdmin(true);
                            int numberOfChoices = 0;
                            Choices choices = new Choices();
                            ResultSet linksrsn = dbManager.selectAllWhere("links", col, val);
                            while(linksrsn.next()) {
                                Link link = new Link();
                                link.setID(numberOfChoices + 1);
                                link.setDescription(linksrs.getString("description"));
                                link.setStoryID(gameInfo.getCurrentRoom());
                                link.setTargetID(Integer.parseInt(linksrs.getString("target_id")));
                                links.add(link);
                                System.out.println(link.toString());
                                if (numberOfChoices == 0) {
                                    choices.setChoiceA(linksrs.getString("description"));
                                } else if (numberOfChoices == 1){
                                    choices.setChoiceB(linksrs.getString("description"));
                                } else if (numberOfChoices == 2){
                                    choices.setChoiceC(linksrs.getString("description"));
                                }
                                System.out.println("choice: " + linksrs.getString("description"));
                                numberOfChoices++;
                            }
                            gameInfo.setLinks(links);
                            choices.setNumberOfChoices(numberOfChoices);
                            int count = 0;
                            while (linksrs.next()) {
                                if (count == 0) {
                                    choices.setChoiceA(linksrs.getString("description"));
                                } else if (count == 1){
                                    choices.setChoiceB(linksrs.getString("description"));
                                } else if (count == 2){
                                    choices.setChoiceC(linksrs.getString("description"));
                                }
                                count++;
                            }
                            GameView gameView = new GameView(user, choices, gameInfo);
                            GameModel gameModel = new GameModel(user, dbManager, gameInfo, choices);
                            GameController gameController = new GameController(gameView, gameModel, user, gameInfo, choices);
                            try {
                                Image firstImg = ImageIO.read(
                                        getClass().getResource("../views/img/red_riding_hood.jpg"));
                                Image resizedImg = firstImg.getScaledInstance(gameView.getWidth()/2, gameView.getHeight()/2,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                                ImageIcon firstIcon = new ImageIcon(resizedImg);
                                gameView.getStoryPicture().setLocation(560, 330);
                                gameView.getStoryPicture().setSize(gameView.getWidth()/2, gameView.getHeight()/2);
                                gameView.getStoryPicture().setIcon(firstIcon);
                            } catch (Exception e) {
                                e.printStackTrace();
                                gameView.getScroll().setSize(gameView.getWidth() - 50, gameView.getHeight() - 100);
                            }
                            gameView.getContentPane().setBackground(Color.YELLOW);
                            gameView.setVisible(true);
                                    /*LoginView loginView = new LoginView();
                LoginModel loginModel = new LoginModel();
                LoginController loginController = new LoginController(loginView, loginModel);
                loginView.setVisible(true);
                loginView.getTxtUsername().requestFocus();*/
                        }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}