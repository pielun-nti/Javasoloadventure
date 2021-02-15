package models;

/**
 * Link class.
 */
public class Link {
    private int ID;
    private String description;
    private int targetID;
    private int storyID;

    @Override
    public String toString() {
        return "Link{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", targetID=" + targetID +
                ", storyID=" + storyID +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTargetID() {
        return targetID;
    }

    public void setTargetID(int targetID) {
        this.targetID = targetID;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }
}
