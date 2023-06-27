package com.example.sssapplication;

public class Password_Update {
    String NewPassword,ConformPassword;

    public Password_Update() {
    }

    public Password_Update(String newPassword, String conformPassword) {
        NewPassword = newPassword;
        ConformPassword = conformPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConformPassword() {
        return ConformPassword;
    }

    public void setConformPassword(String conformPassword) {
        ConformPassword = conformPassword;
    }
}
