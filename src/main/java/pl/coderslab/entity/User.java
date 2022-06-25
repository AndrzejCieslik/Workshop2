package pl.coderslab.entity;

public class User {

    private int user_id;
    private String user_name;
    private String user_email;
    private String user_password;

    public void setEmail(String user_email) {
        this.user_email = user_email;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String user_password) {

        this.user_password = user_password;
    }

    public String getPassword() {
        return user_password;
    }

    public String getEmail() {
        return user_email;
    }

    public int getId() {
        return user_id;
    }

    public String getUserName() {
        return user_name;
    }

}
