package com.example.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Aws on 11/03/2018.
 */
@Entity(tableName = "favorite")
public class FavoriteAnime {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String name ;
    private String Description;
    private String rating;
    private String nb_episode;
    private String categorie;
    private String studio ;
    private String image_url;
    private String idchap;

    public FavoriteAnime(String name, String description, String rating, String categorie, String studio, String img, String idchap) {

        this.name = name;
        Description = description;
        this.rating = rating;
        this.categorie = categorie;
        this.studio = studio;
        this.image_url = image_url;
        this.idchap = this.idchap;
    }
    public FavoriteAnime() {
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdchap() {
        return idchap;
    }

    public void setIdchap(String idchap) {
        this.idchap = idchap;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getRating() {
        return rating;
    }

    public String getNb_episode() {
        return nb_episode;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getStudio() {
        return studio;
    }

    public String getImage_url() {
        return image_url;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setNb_episode(String nb_episode) {
        this.nb_episode = nb_episode;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
