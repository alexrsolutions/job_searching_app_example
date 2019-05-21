package com.example.evidenciafinal.model;

public class User {
    private String idUser;
    private String nameUser;
    private String user_Email;

    public User(){}

    public User(String idUser, String nameUser, String user_Email) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.user_Email = user_Email;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }
}
