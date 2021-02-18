package models;

/**
 * Story model class that contains of id and body. Id is the story id that is unique for each story.
 * The body is the story text that the user can see.
 */
public class Story {
    int ID;
    String body;

    @Override
    public String toString() {
        return "Story{" +
                "ID=" + ID +
                ", body='" + body + '\'' +
                '}';
    }

    /**
     *
     * @return Id of story that can be used to know which story is currently being shown.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id of story
     * @param ID An unique id for this story
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return The story text.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets text for this story
     * @param body The story text to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
