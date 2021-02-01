package models;

import java.util.ArrayList;

/**
 * GameInfo class.
 */
public class GameInfo {
    private int currentRoom;
    private ArrayList<Link> links;
    private ArrayList<Story> stories;

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }
}