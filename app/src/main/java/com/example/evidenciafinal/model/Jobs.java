package com.example.evidenciafinal.model;

public class Jobs {
    private String name_Op;
    private String name_Enter;
    private String des_Enter;
    private String comp_Enter;
    private String id_Open;
    private String id_UserPost;

    public Jobs(){}

    public Jobs(String name_Op, String name_Enter, String des_Enter, String comp_Enter, String id_Open, String id_UserPost) {
        this.name_Op = name_Op;
        this.name_Enter = name_Enter;
        this.des_Enter = des_Enter;
        this.comp_Enter = comp_Enter;
        this.id_Open = id_Open;
        this.id_UserPost = id_UserPost;
    }

    public String getName_Op() {
        return name_Op;
    }

    public String getName_Enter() {
        return name_Enter;
    }

    public String getDes_Enter() {
        return des_Enter;
    }

    public String getComp_Enter() {
        return comp_Enter;
    }

    public void setName_Op(String name_Op) {
        this.name_Op = name_Op;
    }

    public void setName_Enter(String name_Enter) {
        this.name_Enter = name_Enter;
    }

    public void setDes_Enter(String des_Enter) {
        this.des_Enter = des_Enter;
    }

    public void setComp_Enter(String comp_Enter) {
        this.comp_Enter = comp_Enter;
    }

    public String getId_Open() {
        return id_Open;
    }

    public void setId_Open(String id_Open) {
        this.id_Open = id_Open;
    }

    public String getId_UserPost() {
        return id_UserPost;
    }

    public void setId_UserPost(String id_UserPost) {
        this.id_UserPost = id_UserPost;
    }
}
