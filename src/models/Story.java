package models;

/**
 * Story model class.
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
