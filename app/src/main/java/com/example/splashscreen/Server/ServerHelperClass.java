package com.example.splashscreen.Server;

public class ServerHelperClass {
    String FullName, Username, Email, Password;

    public ServerHelperClass() {

    }

    public ServerHelperClass(String fullName, String username, String email, String password) {
        FullName = fullName;
        Username = username;
        Email = email;
        Password = password;


    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
