package com.boylegu.springboot_vue.entities;

public class editPassword {
    private String username;
    private String password;
    private String newPassword;
    private String aginpassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAginpassword() {
        return aginpassword;
    }

    public void setAginpassword(String aginpassword) {
        this.aginpassword = aginpassword;
    }
}
