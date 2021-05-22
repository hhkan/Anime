package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.model.Anime;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimeActivity extends AppCompatActivity {
    TextView imgbNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        String name = getIntent().getExtras().getString("ani_name");
        String description = getIntent().getExtras().getString("ani_description");
        String studio = getIntent().getExtras().getString("ani_studio");
        String category = getIntent().getExtras().getString("ani_categorie");
        String rating = getIntent().getExtras().getString("ani_rating");
        int nb_episode = getIntent().getExtras().getInt("ani_nb_episode");
        String img_url = getIntent().getExtras().getString("ani_img");
        String idChap = getIntent().getExtras().getString("idchap");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#000000"));


        TextView tv_name = findViewById(R.id.ani_anime_name);
        TextView tv_studio = findViewById(R.id.ani_studio);
        TextView tv_categorie = findViewById(R.id.ani_categorie);
        TextView tv_description = findViewById(R.id.tv_ani_desciption);
        TextView tv_rating = findViewById(R.id.ani_rating);
//        Toolbar tv_name_tb = findViewById(R.id.tb_anime);
        ImageView imgThum = findViewById(R.id.ani_thumbnail);
        ImageView imgThum1 = findViewById(R.id.img_img);
        imgbNext = findViewById(R.id.imgb_review_next);

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
//        tv_name_tb.setTitle(name);
        collapsingToolbarLayout.setTitle(name);

        Glide.with(this).load(img_url).into(imgThum);
        Glide.with(this).load(img_url).into(imgThum1);
        imgbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimeActivity.this, ChapterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idchap1", idChap);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void back2(View view) {
        AnimeActivity.super.finish();
    }

    public void back1(View view) {
        AnimeActivity.super.finish();
    }
}