package models;

public class Customer {
    protected String CustomerID;
    private String email;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(String CustomerID, String email, String username, String password) {
        this.CustomerID = CustomerID;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + CustomerID +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}