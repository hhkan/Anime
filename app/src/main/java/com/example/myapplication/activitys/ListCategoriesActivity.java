package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListCategoriesActivity extends AppCompatActivity {
    private final String JSON_URL = "https://json-server-15.herokuapp.com/api/dstruyen?idcategory=";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RecyclerViewAdapter adapter;
    private ArrayList<Anime> listAnime;
    RecyclerView recyclerViewListCategories;
    TextView tvNameCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        init();
        set();
        onClicck();
        jsonrequest();
        listAnime = new ArrayList<>();
    }

    private void init() {
        tvNameCategories = findViewById(R.id.tv_name_listcategoties);
        recyclerViewListCategories = findViewById(R.id.rcv_list_categories);
    }

    private void set() {
        String nameCategories = getIntent().getExtras().getString("name_categories");
        tvNameCategories.setText(nameCategories);

    }

    private void onClicck() {
        ImageButton backCategories = findViewById(R.id.imgb_back_listcategories);
        backCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void jsonrequest() {
        String idCategories = getIntent().getExtras().getString("id_categories");
        request = new JsonArrayRequest(JSON_URL+idCategories, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getString("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));

                        listAnime.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerviewCategories(listAnime);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue = Volley.newRequestQueue(ListCategoriesActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerviewCategories(ArrayList<Anime> lsAnime) {
        adapter = new RecyclerViewAdapter(this, lsAnime);
        recyclerViewListCategories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListCategories.setAdapter(adapter);
    }

    public void back(View view) {
        ListCategoriesActivity.super.finish();
    }
}