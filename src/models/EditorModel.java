package models;

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

    public ResultSet getAllLinks(){
        ResultSet rs = dbManager.selectAll("links");
        return rs;

    }


}