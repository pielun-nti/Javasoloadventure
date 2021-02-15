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


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
