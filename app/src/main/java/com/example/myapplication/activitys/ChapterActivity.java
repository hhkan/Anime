package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ChapterAdapter;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.api.ChapterApi;
import com.example.myapplication.interfaces.GetChapter;
import com.example.myapplication.model.Anime;
import com.example.myapplication.model.Chapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity implements GetChapter {


    private ArrayList<Chapter> chapterArrayList;
    private ListView listViewChapter;
    private ChapterAdapter adapter;

    SearchView searchChapter;
    ImageButton imageButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String value1 = bundle.getString("idchap1", "");
        new ChapterApi(this,value1).execute();

        init();
        onClick();

    }


    private void init() {


        chapterArrayList = new ArrayList<>();
        listViewChapter = findViewById(R.id.lv_chapter);
        searchChapter = findViewById(R.id.seach_chapter);
        imageButtonBack = findViewById(R.id.imgb_back_chapter);
    }



    private void onClick() {

        searchChapter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        listViewChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int numchap = chapterArrayList.size();

                Bundle b = new Bundle();

                b.putString("idread", chapterArrayList.get(position).getIdRead());
                b.putString("nameChap", chapterArrayList.get(position).getTenChap());
                b.putString("idChap",chapterArrayList.get(position).getIdChap());

                b.putString("numChap", String.valueOf(numchap));

                Intent intent = new Intent(ChapterActivity.this, ReadActivity.class);
                intent.putExtra("data", b);
                intent.putExtra("POSITION", position);

                startActivity(intent);


            }
        });
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterActivity.super.finish();
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void end(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                Chapter chapter = new Chapter(array.getJSONObject(i));
                chapterArrayList.add(chapter);
            }
            adapter = new ChapterAdapter(this, chapterArrayList);
            listViewChapter.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void err() {

    }
}