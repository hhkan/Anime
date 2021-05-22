package com.example.myapplication.sqlite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.FavoriteAnime;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(FavoriteAnime anime);

    @Query("SELECT * FROM favorite")
    List<FavoriteAnime> getListUser();

    @Query("SELECT * FROM favorite where name= :name")
    List<FavoriteAnime> checkUser(String name);
}
