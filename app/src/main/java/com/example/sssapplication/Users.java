package com.example.sssapplication;

public class Users {

    String userName,gender,phone,newPassword,passwordRecovery,passRecovery;

    public Users() {
    }

    public Users(String userName, String gender, String phone, String newPassword, String passwordRecovery, String passRecovery) {
        this.userName = userName;
        this.gender = gender;
        this.phone = phone;
        this.newPassword = newPassword;
        this.passwordRecovery = passwordRecovery;
        this.passRecovery = passRecovery;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordRecovery() {
        return passwordRecovery;
    }

    public void setPasswordRecovery(String passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }

    public String getPassRecovery() {
        return passRecovery;
    }

    public void setPassRecovery(String passRecovery) {
        this.passRecovery = passRecovery;
    }
}




