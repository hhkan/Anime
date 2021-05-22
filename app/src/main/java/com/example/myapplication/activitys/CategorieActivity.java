package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.model.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategorieActivity extends AppCompatActivity {
    private final String JSON_URL = "https://json-server-15.herokuapp.com/api/categories";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    CategoryAdapter adapter;
    RecyclerView recyclerViewCategpries;
    ArrayList<Categories> arrayList;
    ImageButton imgbBackCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        init();
        jsonrequest();
        onClick();
    }

    private void init() {
        arrayList = new ArrayList<>();
        recyclerViewCategpries = findViewById(R.id.rcv_categories);
        imgbBackCategories = findViewById(R.id.imgb_categories);


    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Categories categories = new Categories();
                        categories.setStudio(jsonObject.getString("studio"));
                        categories.setIdCategory(jsonObject.getString("idcategory"));
                        arrayList.add(categories);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue = Volley.newRequestQueue(CategorieActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(ArrayList<Categories> arrayList) {
        adapter = new CategoryAdapter(this, arrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
//        recyclerViewCategpries.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategpries.setLayoutManager(gridLayoutManager);
        recyclerViewCategpries.setAdapter(adapter);
    }
    private void onClick() {
        imgbBackCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategorieActivity.super.finish();
            }
        });
    }
}