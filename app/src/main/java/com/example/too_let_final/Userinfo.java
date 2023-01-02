package com.example.too_let_final;

public class Userinfo {
    String textName,textEmail,textPhone,textPassword;

    public Userinfo() {
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(String textEmail) {
        this.textEmail = textEmail;
    }

    public String getTextPhone() {
        return textPhone;
    }

    public void setTextPhone(String textPhone) {
        this.textPhone = textPhone;
    }

    public String getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(String textPassword) {
        this.textPassword = textPassword;
    }

    public Userinfo(String textName, String textEmail, String textPhone, String textPassword) {


        this.textName = textName;
        this.textEmail = textEmail;
        this.textPhone = textPhone;
        this.textPassword = textPassword;
    }
}
