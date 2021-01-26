package views;

import config.Env;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * GameView class that extends Swing JFrame.
 */
public class GameView extends javax.swing.JFrame {
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenu editMenu;
    JMenu graphicsMenu;
    JMenu aboutMenu;
    JScrollPane scroll;
    JMenuItem menuItemChoiceA;
    JMenuItem menuItemChoiceB;
    JMenuItem menuItemChoiceC;
    JMenuItem menuItemDeleteLog;
    JMenuItem menuItemShowLogHistory;
    JMenuItem menuItemDeleteAllLogs;
    JMenuItem menuItemDeleteLogHistory;
    JMenuItem menuItemSaveAs;
    JMenuItem menuItemOpen;
    JMenuItem menuItemOpenSecurity;
    JMenuItem menuItemLogout;
    JMenuItem menuItemExit;
    JMenuItem menuItemFilterLogs;
    JTextArea txtStory;
    JMenuItem menuItemChangeFontSize;
    JMenuItem menuItemAbout;
    private Font mainFont;
    int fontSize = 18;
    User user;

    /**
     * GameView constructor with user argument.
     * @param user
     */
    public GameView(User user){
        this.user = user;
        initComponents();
        setFonts();
        initKeystrokes();
        addComponents();
        Dimension res = new Dimension(1200, 800);
        setPreferredSize(res);
        setSize(res);
        txtStory.setEditable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    /**
     * Set fonts method.
     */
    void setFonts(){
        setFont(mainFont);
        txtStory.setFont(mainFont);
        menuBar.setFont(mainFont);
        optionsMenu.setFont(mainFont);
        editMenu.setFont(mainFont);
        aboutMenu.setFont(mainFont);
        menuItemChoiceA.setFont(mainFont);
        menuItemChoiceC.setFont(mainFont);
        menuItemDeleteLog.setFont(mainFont);
        menuItemChoiceB.setFont(mainFont);
        menuItemShowLogHistory.setFont(mainFont);
        menuItemDeleteAllLogs.setFont(mainFont);
        menuItemDeleteLogHistory.setFont(mainFont);
        menuItemSaveAs.setFont(mainFont);
        menuItemOpen.setFont(mainFont);
        menuItemExit.setFont(mainFont);
        menuItemChangeFontSize.setFont(mainFont);
        menuItemAbout.setFont(mainFont);
        menuItemLogout.setFont(mainFont);
        menuItemOpenSecurity.setFont(mainFont);
        menuItemFilterLogs.setFont(mainFont);
    }

    /**
     * Initializes Keystrokes.
     */
    void initKeystrokes(){
        menuItemChoiceB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        menuItemShowLogHistory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        menuItemChoiceA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        menuItemChoiceC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        menuItemDeleteLog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuItemFilterLogs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * Initializes all gui components.
     */
    void initComponents() {
        setTitle("LogsManager - logged in as: " + user.getUsername());
        menuBar = new JMenuBar();
        optionsMenu = new JMenu("Options");
        editMenu = new JMenu("Edit");
        graphicsMenu = new JMenu("Graphics");
        aboutMenu = new JMenu("About");
        mainFont = new Font("Verdana", Font.BOLD, fontSize);
        txtStory = new JTextArea();
        scroll = new JScrollPane(txtStory,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        menuItemExit = new JMenuItem("Exit application");
        menuItemSaveAs = new JMenuItem("Save Logs As");
        menuItemOpen = new JMenuItem("Open Logs");
        menuItemDeleteAllLogs = new JMenuItem("Delete All Logs");
        menuItemDeleteLogHistory = new JMenuItem("Delete All Logs Changes History");
        menuItemDeleteLog = new JMenuItem("Delete Log");
        menuItemChoiceC = new JMenuItem("Choice C");
        menuItemChoiceB = new JMenuItem("Choice B");
        menuItemChoiceA = new JMenuItem("Choice A");
        menuItemChangeFontSize = new JMenuItem("Change Font Size");
        menuItemShowLogHistory = new JMenuItem("Get Logs Changes History");
        menuItemAbout = new JMenuItem("About");
        menuItemLogout = new JMenuItem("Logout");
        menuItemOpenSecurity = new JMenuItem("Open Security");
        menuItemFilterLogs = new JMenuItem("Filter Logs");
    }

    /**
     * Adds all items to menu and then menus to menubar and then to JFrame.
     */
    void addComponents(){
        optionsMenu.add(menuItemChoiceA);
        optionsMenu.add(menuItemChoiceC);
        optionsMenu.add(menuItemDeleteLog);
        optionsMenu.add(menuItemChoiceB);
        optionsMenu.add(menuItemShowLogHistory);
        optionsMenu.add(menuItemDeleteAllLogs);
        optionsMenu.add(menuItemDeleteLogHistory);
        optionsMenu.add(menuItemFilterLogs);
        optionsMenu.add(menuItemSaveAs);
        optionsMenu.add(menuItemOpen);
        optionsMenu.add(menuItemLogout);
        optionsMenu.add(menuItemOpenSecurity);
        optionsMenu.add(menuItemExit);
        editMenu.add(menuItemChangeFontSize);
        aboutMenu.add(menuItemAbout);
        menuBar.add(optionsMenu);
        menuBar.add(editMenu);
        menuBar.add(graphicsMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);
        add(scroll);
    }


    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getoptionsMenu() {
        return optionsMenu;
    }

    public void setoptionsMenu(JMenu optionsMenu) {
        this.optionsMenu = optionsMenu;
    }

    public JMenu getEditMenu() {
        return editMenu;
    }

    public void setEditMenu(JMenu editMenu) {
        this.editMenu = editMenu;
    }

    public JMenu getgraphicsMenu() {
        return graphicsMenu;
    }

    public void setgraphicsMenu(JMenu graphicsMenu) {
        this.graphicsMenu = graphicsMenu;
    }

    public JMenu getAboutMenu() {
        return aboutMenu;
    }

    public void setAboutMenu(JMenu aboutMenu) {
        this.aboutMenu = aboutMenu;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    public JMenuItem getmenuItemChoiceA() {
        return menuItemChoiceA;
    }

    public void setmenuItemChoiceA(JMenuItem menuItemChoiceA) {
        this.menuItemChoiceA = menuItemChoiceA;
    }

    public JMenuItem getmenuItemChoiceB() {
        return menuItemChoiceB;
    }

    public void setmenuItemChoiceB(JMenuItem menuItemChoiceB) {
        this.menuItemChoiceB = menuItemChoiceB;
    }

    public JMenuItem getmenuItemChoiceC() {
        return menuItemChoiceC;
    }

    public void setmenuItemChoiceC(JMenuItem menuItemChoiceC) {
        this.menuItemChoiceC = menuItemChoiceC;
    }

    public JMenuItem getMenuItemDeleteLog() {
        return menuItemDeleteLog;
    }

    public void setMenuItemDeleteLog(JMenuItem menuItemDeleteLog) {
        this.menuItemDeleteLog = menuItemDeleteLog;
    }

    public JMenuItem getMenuItemShowLogHistory() {
        return menuItemShowLogHistory;
    }

    public void setMenuItemShowLogHistory(JMenuItem menuItemShowLogHistory) {
        this.menuItemShowLogHistory = menuItemShowLogHistory;
    }

    public JMenuItem getMenuItemDeleteAllLogs() {
        return menuItemDeleteAllLogs;
    }

    public void setMenuItemDeleteAllLogs(JMenuItem menuItemDeleteAllLogs) {
        this.menuItemDeleteAllLogs = menuItemDeleteAllLogs;
    }

    public JMenuItem getMenuItemDeleteLogHistory() {
        return menuItemDeleteLogHistory;
    }

    public void setMenuItemDeleteLogHistory(JMenuItem menuItemDeleteLogHistory) {
        this.menuItemDeleteLogHistory = menuItemDeleteLogHistory;
    }

    public JMenuItem getMenuItemSaveAs() {
        return menuItemSaveAs;
    }

    public void setMenuItemSaveAs(JMenuItem menuItemSaveAs) {
        this.menuItemSaveAs = menuItemSaveAs;
    }

    public JMenuItem getMenuItemLogout() {
        return menuItemLogout;
    }

    public void setMenuItemLogout(JMenuItem menuItemLogout) {
        this.menuItemLogout = menuItemLogout;
    }

    public JMenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public void setMenuItemExit(JMenuItem menuItemExit) {
        this.menuItemExit = menuItemExit;
    }

    public JMenuItem getMenuItemFilterLogs() {
        return menuItemFilterLogs;
    }

    public void setMenuItemFilterLogs(JMenuItem menuItemFilterLogs) {
        this.menuItemFilterLogs = menuItemFilterLogs;
    }

    public JTextArea gettxtStory() {
        return txtStory;
    }

    public void settxtStory(JTextArea txtStory) {
        this.txtStory = txtStory;
    }

    public JMenuItem getMenuItemChangeFontSize() {
        return menuItemChangeFontSize;
    }

    public void setMenuItemChangeFontSize(JMenuItem menuItemChangeFontSize) {
        this.menuItemChangeFontSize = menuItemChangeFontSize;
    }

    public JMenuItem getMenuItemAbout() {
        return menuItemAbout;
    }

    public JMenuItem getMenuItemOpen() {
        return menuItemOpen;
    }

    public void setMenuItemOpen(JMenuItem menuItemOpen) {
        this.menuItemOpen = menuItemOpen;
    }

    public JMenuItem getMenuItemOpenSecurity() {
        return menuItemOpenSecurity;
    }

    public void setMenuItemOpenSecurity(JMenuItem menuItemOpenSecurity) {
        this.menuItemOpenSecurity = menuItemOpenSecurity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMenuItemAbout(JMenuItem menuItemAbout) {
        this.menuItemAbout = menuItemAbout;
    }

    public Font getMainFont() {
        return mainFont;
    }

    public void setMainFont(Font mainFont) {
        this.mainFont = mainFont;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }


    public void setStoryTXT(String text){
        txtStory.setText(text);
    }

    public void addListeners(ActionListener listener){
        menuItemChoiceA.addActionListener(listener);
        menuItemChoiceC.addActionListener(listener);
        menuItemDeleteLog.addActionListener(listener);
        menuItemChoiceB.addActionListener(listener);
        menuItemShowLogHistory.addActionListener(listener);
        menuItemDeleteAllLogs.addActionListener(listener);
        menuItemDeleteLogHistory.addActionListener(listener);
        menuItemFilterLogs.addActionListener(listener);
        menuItemSaveAs.addActionListener(listener);
        menuItemOpen.addActionListener(listener);
        menuItemLogout.addActionListener(listener);
        menuItemOpenSecurity.addActionListener(listener);
        menuItemExit.addActionListener(listener);
        menuItemChangeFontSize.addActionListener(listener);
        menuItemAbout.addActionListener(listener);
    }

    public void addFrameWindowListener(WindowListener listener){
        addWindowListener(listener);
    }

    public String getLogsTXT(){
        return txtStory.getText();
    }

    public void appendLogsTXT(String text){
        txtStory.append(text);
    }
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
    }
    

}
