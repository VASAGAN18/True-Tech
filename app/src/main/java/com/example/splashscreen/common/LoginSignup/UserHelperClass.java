package com.example.splashscreen.common.LoginSignup;

public class UserHelperClass {
    String FullName, Username, Email, Password, Gender, DOB, PhoneNo, State, Nationality;


    public UserHelperClass() {

    }

    public UserHelperClass(String fullName, String username, String email, String password, String gender,String DOB, String phoneNo, String state, String nationality) {
        FullName = fullName;
        Username = username;
        Email = email;
        Password = password;
        Gender = gender;
        this.DOB = DOB;
        PhoneNo = phoneNo;
        State = state;
        Nationality = nationality;

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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }


}
