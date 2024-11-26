package models;

public class User {
    private int userID;
    private String username;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(int userID, String username, String email, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStoreOwner() {
        return "STORE_OWNER".equals(role);
    }

    public boolean isCustomer() {
        return "CUSTOMER".equals(role);
    }

    @Override
    public String toString() {
        return "userID: " + userID + ", username: " + username + ", email: " + email + ", role: " + role;
    }
}