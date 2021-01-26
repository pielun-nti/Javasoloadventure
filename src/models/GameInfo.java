package models;

import java.util.ArrayList;

public class GameInfo {
    private int currentRoom;
    private ArrayList<String> links;
    private ArrayList<String> story;

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

    public ArrayList<String> getStory() {
        return story;
    }

    public void setStory(ArrayList<String> story) {
        this.story = story;
    }
}
