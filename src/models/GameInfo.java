package models;

import java.util.ArrayList;

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

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public ArrayList<String> getStories() {
        return stories;
    }

    public void setStories(ArrayList<String> stories) {
        this.stories = stories;
    }
}