package models;

/**
 * User class with username and admin variables.
 */
public class User {
    String username;
    boolean admin;

    /**
     * User constructor with no arguments.
     */
    public User(){
        this.username = "Unknown";
        this.admin = false;
    }

    /**
     * User constructor with arguments.
     * @param username The users name
     * @param admin If the user is admin or not
     */
    public User(String username, boolean admin){
        this.username = username;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
