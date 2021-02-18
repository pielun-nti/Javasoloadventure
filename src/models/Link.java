package models;

/**
 * Link class that contains id, description, targetid and storyid for the current link. Targetid is the id which story scene the
 * game will go to if the user click this link. The description is the usage info the user sees about this link.
 * Storyid is the current storyid for this link, it connects a story to this link.
 */
public class Link {
    private int ID;
    private String description;
    private int targetID;
    private int storyID;

    /**
     *
     * @return description of link
     */
    @Override
    public String toString() {
        return "Link{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", targetID=" + targetID +
                ", storyID=" + storyID +
                '}';
    }

    /**
     *
     * @return Id of this link
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id of this link
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return Description of this link
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return Target id of this link. The target id is used to know which story it points to.
     */
    public int getTargetID() {
        return targetID;
    }

    /**
     * Sets target id for this link.
     * @param targetID This is used to know which story this link points to.
     */
    public void setTargetID(int targetID) {
        this.targetID = targetID;
    }

    /**
     *
     * @return Story id for this link. It is just used to know which story that had this link.
     */
    public int getStoryID() {
        return storyID;
    }

    /**
     * Sets story id for this link.
     * @param storyID This is used to know which story that had this link.
     */
    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }
}
