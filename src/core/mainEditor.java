package core;

import config.Env;
import controllers.EditorController;
import controllers.GameController;
import models.*;
import views.EditorView;
import views.GameView;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Java solo adventure editor gjord av Pierre
 * Innan du startar programmet, fyll i dina databasvariabler i src/config/Env.java filen
 */
public class mainEditor {
    /**
     * Main method. Starts the game editor. Uses EventQueue.invokeLater to run after other UI swing events have been run to prevent UI lag. Tries to make GUI look more alike windows programs GUIs by using UIManager.
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
                    DBManager dbManager = new DBManager();
                    ResultSet rs = dbManager.selectAll("story");
                    ArrayList<Story> stories = gameInfo.getStories();
                    if (stories == null){
                        stories = new ArrayList<>();
                    }
                    while (rs.next()) {
                        Story story = new Story();
                        story.setID(rs.getInt("id"));
                        story.setBody(rs.getString("body"));
                        stories.add(story);
                    }
                    gameInfo.setStories(stories);
                    User user = new User();
                    user.setUsername("test");
                    user.setAdmin(true);
                        EditorView editorView = new EditorView(user, gameInfo);
                        EditorModel editorModel = new EditorModel(user, dbManager, gameInfo);
                        EditorController editorController = new EditorController(editorView, editorModel, user, gameInfo);
                        editorView.setVisible(true);
                                    /*LoginView loginView = new LoginView();
                LoginModel loginModel = new LoginModel();
                LoginController loginController = new LoginController(loginView, loginModel);
                loginView.setVisible(true);
                loginView.getTxtUsername().requestFocus();*/
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}