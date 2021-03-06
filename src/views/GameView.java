package views;

import config.Env;
import models.Choices;
import models.GameInfo;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.nio.charset.StandardCharsets;

/**
 * GameView class that extends Swing JFrame. It is the view for the game. It has a jmenubar, jmenus, jmenuitems
 * and jlabel with image in it etc.
 */
public class GameView extends javax.swing.JFrame {
    BackgroundMenuBar menuBar;
    JMenu optionsMenu;
    JMenu editMenu;
    JMenu graphicsMenu;
    JMenu aboutMenu;
    JScrollPane scroll;
    JMenuItem menuItemChoiceA;
    JMenuItem menuItemChoiceB;
    JMenuItem menuItemChoiceC;
    JMenuItem menuItemExit;
    JTextArea txtStory;
    JMenuItem menuItemChangeFontSize;
    JMenuItem menuItemAbout;
    private Font mainFont;
    int fontSize = 18;
    User user;
    Choices choices;
    JLabel storyPicture;
    private CustomPanel mainPanel;
    GameInfo gameInfo;
    /**
     * GameView constructor.
     * @param user
     */
    public GameView(User user, Choices choices, GameInfo gameInfo){
        this.choices = choices;
        this.user = user;
        this.gameInfo = gameInfo;
        initComponents();
        setFonts();
        setColors();
        setLocations();
        initKeystrokes();
        addComponents();
        Dimension res = new Dimension(1200, 800);
        setPreferredSize(res);
        setSize(res);
        setContentPane(mainPanel);
        txtStory.setEditable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    /**
     * Sets colors of jmenus and jmenuitems.
     */
    void setColors(){
        optionsMenu.setBackground(Color.YELLOW);
        optionsMenu.setForeground(Color.MAGENTA);
        editMenu.setBackground(Color.YELLOW);
        editMenu.setForeground(Color.MAGENTA);
        graphicsMenu.setBackground(Color.YELLOW);
        graphicsMenu.setForeground(Color.MAGENTA);
        aboutMenu.setBackground(Color.YELLOW);
        aboutMenu.setForeground(Color.MAGENTA);
        menuItemExit.setForeground(Color.BLUE);
        menuItemExit.setBackground(Color.GREEN);
        menuItemChoiceA.setForeground(Color.BLUE);
        menuItemChoiceA.setBackground(Color.GREEN);
        menuItemChoiceB.setForeground(Color.BLUE);
        menuItemChoiceB.setBackground(Color.GREEN);
        menuItemChoiceC.setForeground(Color.BLUE);
        menuItemChoiceC.setBackground(Color.GREEN);
        menuItemAbout.setForeground(Color.BLUE);
        menuItemAbout.setBackground(Color.GREEN);
        menuItemChangeFontSize.setForeground(Color.BLUE);
        menuItemChangeFontSize.setBackground(Color.GREEN);
    }

    /**
     * Set fonts method.
     */
    void setFonts(){
        setFont(mainFont);
        txtStory.setFont(mainFont);
        storyPicture.setFont(mainFont);
        menuBar.setFont(mainFont);
        graphicsMenu.setFont(mainFont);
        optionsMenu.setFont(mainFont);
        editMenu.setFont(mainFont);
        aboutMenu.setFont(mainFont);
        menuItemChoiceA.setFont(mainFont);
        menuItemChoiceC.setFont(mainFont);
        menuItemChoiceB.setFont(mainFont);
        menuItemExit.setFont(mainFont);
        menuItemChangeFontSize.setFont(mainFont);
        menuItemAbout.setFont(mainFont);
    }

    /**
     * Sets location of jframe components.
     */
    void setLocations(){
        scroll.setLocation(0, 10);
        scroll.setSize(1170, 310);
    }


    /**
     * Initializes Keystrokes.
     */
    void initKeystrokes(){
        menuItemChoiceA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemChoiceB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuItemChoiceC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * Initializes all gui components.
     */
    void initComponents() {
        setTitle("Solo adventure - logged in as: " + user.getUsername());
        menuBar = new BackgroundMenuBar();
        mainPanel = new CustomPanel(this);
        mainPanel.setLayout(null);
        storyPicture = new JLabel("pic");
        optionsMenu = new JMenu("Options");
        editMenu = new JMenu("Edit");
        graphicsMenu = new JMenu("Graphics");
        aboutMenu = new JMenu("About");
        mainFont = new Font("Verdana", Font.BOLD, fontSize);
        txtStory = new JTextArea();
        scroll = new JScrollPane(txtStory,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        menuItemExit = new JMenuItem("Exit application");
        menuItemChoiceC = new JMenuItem("Choice C");
        menuItemChoiceB = new JMenuItem("Choice B");
        menuItemChoiceA = new JMenuItem("Choice A");
        menuItemChangeFontSize = new JMenuItem("Change Font Size");
        menuItemAbout = new JMenuItem("About");
    }

    /**
     * Adds all items to menu and then menus to menubar and then to JFrame.
     */
    void addComponents(){
        optionsMenu.add(menuItemChoiceA);
//        optionsMenu.add(menuItemDeleteLog);
        optionsMenu.add(menuItemChoiceB);
        optionsMenu.add(menuItemChoiceC);
        /*optionsMenu.add(menuItemShowLogHistory);
        optionsMenu.add(menuItemDeleteAllLogs);
        optionsMenu.add(menuItemDeleteLogHistory);
        optionsMenu.add(menuItemFilterLogs);
        optionsMenu.add(menuItemSaveAs);
        optionsMenu.add(menuItemOpen);
        optionsMenu.add(menuItemLogout);
        optionsMenu.add(menuItemOpenSecurity);*/
        optionsMenu.add(menuItemExit);
        editMenu.add(menuItemChangeFontSize);
        aboutMenu.add(menuItemAbout);
        menuBar.add(optionsMenu);
        menuBar.add(editMenu);
        menuBar.add(graphicsMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);
        mainPanel.add(scroll);
        mainPanel.add(storyPicture);
    }


    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public CustomPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(CustomPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JMenu getOptionsMenu() {
        return optionsMenu;
    }

    public void setOptionsMenu(JMenu optionsMenu) {
        this.optionsMenu = optionsMenu;
    }

    public JMenu getGraphicsMenu() {
        return graphicsMenu;
    }

    public void setGraphicsMenu(JMenu graphicsMenu) {
        this.graphicsMenu = graphicsMenu;
    }

    public JMenuItem getMenuItemChoiceA() {
        return menuItemChoiceA;
    }

    public void setMenuItemChoiceA(JMenuItem menuItemChoiceA) {
        this.menuItemChoiceA = menuItemChoiceA;
    }

    public JMenuItem getMenuItemChoiceB() {
        return menuItemChoiceB;
    }

    public void setMenuItemChoiceB(JMenuItem menuItemChoiceB) {
        this.menuItemChoiceB = menuItemChoiceB;
    }

    public JMenuItem getMenuItemChoiceC() {
        return menuItemChoiceC;
    }

    public void setMenuItemChoiceC(JMenuItem menuItemChoiceC) {
        this.menuItemChoiceC = menuItemChoiceC;
    }

    public JTextArea getTxtStory() {
        return txtStory;
    }

    public void setTxtStory(JTextArea txtStory) {
        this.txtStory = txtStory;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }

    public JLabel getStoryPicture() {
        return storyPicture;
    }

    public void setStoryPicture(JLabel storyPicture) {
        this.storyPicture = storyPicture;
    }

    public void setMenuBar(BackgroundMenuBar menuBar) {
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





    public JMenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public void setMenuItemExit(JMenuItem menuItemExit) {
        this.menuItemExit = menuItemExit;
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

    /**
     * Adds ActionListeners to each JMenuItem
     * @param listener The ActionListener
     */
    public void addListeners(ActionListener listener){
        menuItemChoiceA.addActionListener(listener);
        menuItemChoiceC.addActionListener(listener);
        menuItemChoiceB.addActionListener(listener);
        menuItemExit.addActionListener(listener);
        menuItemChangeFontSize.addActionListener(listener);
        menuItemAbout.addActionListener(listener);
    }

    /**
     * Adds window listener to the jframe (exit listener).
     * @param listener
     */
    public void addFrameWindowListener(WindowListener listener){
        addWindowListener(listener);
    }

    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, Env.GameMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
    }
    

}
