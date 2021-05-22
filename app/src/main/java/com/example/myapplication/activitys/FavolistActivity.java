package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.FavoriteAdapter;
import com.example.myapplication.model.FavoriteAnime;
import com.example.myapplication.sqlite.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavolistActivity extends AppCompatActivity {


    FavoriteAdapter adapter;
    RecyclerView recyclerViewFavorite;
    List<FavoriteAnime> favoriteAnimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favolist);

        recyclerViewFavorite = findViewById(R.id.rcv_favorite);

        favoriteAnimes = new ArrayList<>();
        adapter = new FavoriteAdapter();


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewFavorite.setLayoutManager(manager);

        recyclerViewFavorite.setAdapter(adapter);

        favoriteAnimes = UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(favoriteAnimes);

    }


    public void bacFave(View view) {
        FavolistActivity.super.finish();
    }
}