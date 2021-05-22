package com.example.myapplication.model;

public class Categories {
    String id,studio,idCategory;

    public Categories(String id, String studio, String idCategory) {
        this.id = id;
        this.studio = studio;
        this.idCategory = idCategory;
    }

    public Categories() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
}
