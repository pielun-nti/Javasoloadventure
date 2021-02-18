package models;

import java.util.ArrayList;

/**
 * GameInfo class with info about current room and all links and stories that exist in the game.
 */
public class GameInfo {
    private int currentRoom;
    private ArrayList<Link> links;
    private ArrayList<Story> stories;

    /**
     *
     * @return The current room/scene the user is in
     */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room that the user is in
     * @param currentRoom
     */
    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     *
     * @return An array of links that contains all the links in the database.
     */
    public ArrayList<Link> getLinks() {
        return links;
    }

    /**
     * Sets links arraylist.
     * @param links Arraylist containing links.
     */
    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    /**
     *
     * @return An array of stories that contains all stories in database.
     */
    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }
}