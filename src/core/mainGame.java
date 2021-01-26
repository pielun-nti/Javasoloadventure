package core;

import config.Env;
import models.DBManager;
import models.GameInfo;

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
                        ArrayList<String>
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