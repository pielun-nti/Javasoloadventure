package views;

import config.Env;
import models.Choices;
import models.GameInfo;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.nio.charset.StandardCharsets;

/**
 * EditorView class that extends Swing JFrame. It is the view for the game editor. It has a jmenubar, jmenus, jmenuitems
 * and image and jcombobox etc.
 */
public class EditorView extends javax.swing.JFrame {
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenu editMenu;
    JMenu graphicsMenu;
    JMenu aboutMenu;
    JScrollPane scroll;
    JMenuItem menuItemEditStoryBody;
    JMenuItem menuItemEditStoryID;
    JMenuItem menuItemAddStory;
    JMenuItem menuItemRefreshStoriesLinks;
    JMenuItem menuitemEditLink;
    JMenuItem menuItemAddLink;
    JMenuItem menuItemDeleteLink;
    JMenuItem menuItemExit;
    JMenuItem menuItemDeleteStory;
    JTextArea txtStory;
    JMenuItem menuItemChangeFontSize;
    JMenuItem menuItemAbout;
    private Font mainFont;
    int fontSize = 18;
    User user;
    JLabel storyPicture;
    private JPanel mainPanel;
    GameInfo gameInfo;
    JComboBox sceneSelector;

    /**
     * EditorView constructor.
     * @param user The user
     * @param gameInfo The gameinfo
     */
    public EditorView(User user, GameInfo gameInfo){
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
        menuItemEditStoryBody.setForeground(Color.BLUE);
        menuItemEditStoryBody.setBackground(Color.GREEN);
        menuItemEditStoryID.setForeground(Color.BLUE);
        menuItemEditStoryID.setBackground(Color.GREEN);
        menuItemAddStory.setForeground(Color.BLUE);
        menuItemAddStory.setBackground(Color.GREEN);
        menuItemRefreshStoriesLinks.setForeground(Color.BLUE);
        menuItemRefreshStoriesLinks.setBackground(Color.GREEN);
        menuItemAbout.setForeground(Color.BLUE);
        menuItemAbout.setBackground(Color.GREEN);
        menuItemChangeFontSize.setForeground(Color.BLUE);
        menuItemChangeFontSize.setBackground(Color.GREEN);
        menuitemEditLink.setBackground(Color.GREEN);
        menuitemEditLink.setForeground(Color.BLUE);
        menuItemAddLink.setBackground(Color.GREEN);
        menuItemAddLink.setForeground(Color.BLUE);
        menuItemRefreshStoriesLinks.setBackground(Color.GREEN);
        menuItemRefreshStoriesLinks.setForeground(Color.BLUE);
        menuItemDeleteStory.setBackground(Color.GREEN);
        menuItemDeleteStory.setForeground(Color.BLUE);
        menuItemDeleteLink.setForeground(Color.BLUE);
        menuItemDeleteLink.setBackground(Color.GREEN);
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
        menuItemEditStoryBody.setFont(mainFont);
        menuItemAddStory.setFont(mainFont);
        menuItemRefreshStoriesLinks.setFont(mainFont);
        menuItemEditStoryID.setFont(mainFont);
        menuitemEditLink.setFont(mainFont);
        menuItemAddLink.setFont(mainFont);
        menuItemDeleteLink.setFont(mainFont);
        menuItemExit.setFont(mainFont);
        sceneSelector.setFont(mainFont);
        menuItemChangeFontSize.setFont(mainFont);
        menuItemAbout.setFont(mainFont);
        menuItemDeleteStory.setFont(mainFont);
    }

    /**
     * Sets location of jframe components.
     */
    void setLocations(){
        scroll.setLocation(0, 60);
        scroll.setSize(1180, 670);
        sceneSelector.setSize(500, 50);
        sceneSelector.setLocation(5, 5);
    }

    /**
     * Add item listeners.
     * @param itemListener Listening for item change events.
     */
    public void addItemListeners(ItemListener itemListener){
        sceneSelector.addItemListener(itemListener);
    }


    /**
     * Initializes Keystrokes.
     */
    void initKeystrokes(){
        menuItemEditStoryBody.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemEditStoryID.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAddStory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemDeleteStory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuItemRefreshStoriesLinks.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuitemEditLink.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAddLink.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuItemDeleteLink.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * Initializes all gui components.
     */
    void initComponents() {
        setTitle("Solo adventure - logged in as: " + user.getUsername());
        menuBar = new JMenuBar();
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        sceneSelector = new JComboBox<String>();
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
        menuItemAddLink = new JMenuItem("Add New Link");
        menuItemDeleteLink = new JMenuItem("Delete Link");
        menuItemRefreshStoriesLinks = new JMenuItem("Refresh Stories And Links");
        menuItemAddStory = new JMenuItem("Add New Story");
        menuItemEditStoryID = new JMenuItem("Edit Story ID");
        menuItemEditStoryBody = new JMenuItem("Edit Story Body");
        menuItemChangeFontSize = new JMenuItem("Change Font Size");
        menuitemEditLink = new JMenuItem("Edit Link");
        menuItemAbout = new JMenuItem("About");
        menuItemDeleteStory = new JMenuItem("Delete Story");
    }

    /**
     * Adds all items to menu and then menus to menubar and then to JFrame.
     */
    void addComponents(){
        optionsMenu.add(menuItemEditStoryBody);
        optionsMenu.add(menuItemEditStoryID);
        optionsMenu.add(menuItemAddStory);
        optionsMenu.add(menuItemDeleteStory);
        optionsMenu.add(menuItemRefreshStoriesLinks);
        optionsMenu.add(menuitemEditLink);
        optionsMenu.add(menuItemAddLink);
        optionsMenu.add(menuItemDeleteLink);
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
        mainPanel.add(sceneSelector);
    }


    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
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

    public JMenuItem getMenuItemEditStoryBody() {
        return menuItemEditStoryBody;
    }

    public void setMenuItemEditStoryBody(JMenuItem menuItemEditStoryBody) {
        this.menuItemEditStoryBody = menuItemEditStoryBody;
    }

    public JMenuItem getMenuItemEditStoryID() {
        return menuItemEditStoryID;
    }

    public void setMenuItemEditStoryID(JMenuItem menuItemEditStoryID) {
        this.menuItemEditStoryID = menuItemEditStoryID;
    }

    public JMenuItem getMenuItemAddStory() {
        return menuItemAddStory;
    }

    public void setMenuItemAddStory(JMenuItem menuItemAddStory) {
        this.menuItemAddStory = menuItemAddStory;
    }

    public JTextArea getTxtStory() {
        return txtStory;
    }

    public void setTxtStory(JTextArea txtStory) {
        this.txtStory = txtStory;
    }


    public JLabel getStoryPicture() {
        return storyPicture;
    }

    public void setStoryPicture(JLabel storyPicture) {
        this.storyPicture = storyPicture;
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

    public JMenuItem getmenuItemEditStoryBody() {
        return menuItemEditStoryBody;
    }

    public void setmenuItemEditStoryBody(JMenuItem menuItemEditStoryBody) {
        this.menuItemEditStoryBody = menuItemEditStoryBody;
    }

    public JMenuItem getmenuItemEditStoryID() {
        return menuItemEditStoryID;
    }

    public void setmenuItemEditStoryID(JMenuItem menuItemEditStoryID) {
        this.menuItemEditStoryID = menuItemEditStoryID;
    }

    public JMenuItem getmenuItemAddStory() {
        return menuItemAddStory;
    }

    public void setmenuItemAddStory(JMenuItem menuItemAddStory) {
        this.menuItemAddStory = menuItemAddStory;
    }

    public JMenuItem getmenuItemRefreshStoriesLinks() {
        return menuItemRefreshStoriesLinks;
    }

    public void setmenuItemRefreshStoriesLinks(JMenuItem menuItemRefreshStoriesLinks) {
        this.menuItemRefreshStoriesLinks = menuItemRefreshStoriesLinks;
    }

    public JMenuItem getMenuitemEditLink() {
        return menuitemEditLink;
    }

    public void setMenuitemEditLink(JMenuItem menuitemEditLink) {
        this.menuitemEditLink = menuitemEditLink;
    }

    public JMenuItem getmenuItemAddLink() {
        return menuItemAddLink;
    }

    public void setmenuItemAddLink(JMenuItem menuItemAddLink) {
        this.menuItemAddLink = menuItemAddLink;
    }

    public JMenuItem getmenuItemDeleteLink() {
        return menuItemDeleteLink;
    }

    public void setmenuItemDeleteLink(JMenuItem menuItemDeleteLink) {
        this.menuItemDeleteLink = menuItemDeleteLink;
    }


    public JMenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public void setMenuItemExit(JMenuItem menuItemExit) {
        this.menuItemExit = menuItemExit;
    }

    public JMenuItem getmenuItemDeleteStory() {
        return menuItemDeleteStory;
    }

    public void setmenuItemDeleteStory(JMenuItem menuItemDeleteStory) {
        this.menuItemDeleteStory = menuItemDeleteStory;
    }


    public JComboBox getSceneSelector() {
        return sceneSelector;
    }

    public void setSceneSelector(JComboBox sceneSelector) {
        this.sceneSelector = sceneSelector;
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
        menuItemEditStoryBody.addActionListener(listener);
        menuItemAddStory.addActionListener(listener);
        menuItemRefreshStoriesLinks.addActionListener(listener);
        menuItemEditStoryID.addActionListener(listener);
        menuitemEditLink.addActionListener(listener);
        menuItemAddLink.addActionListener(listener);
        menuItemDeleteLink.addActionListener(listener);
        menuItemDeleteStory.addActionListener(listener);
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
        JOptionPane.showMessageDialog(this, msg, Env.EditorMessageBoxTitle, JOptionPane.ERROR_MESSAGE);
    }


}
