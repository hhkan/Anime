package com.example.myapplication.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.adapters.RecyclerViewAdapter2;
import com.example.myapplication.dialog.ExampleDialog;
import com.example.myapplication.model.Anime;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.R.menu.search;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL = "https://json-server-15.herokuapp.com/api/dstruyen";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    //    private ImageButton navOpMain;
//    private NavigationView navigationMain;
//    private DrawerLayout drawerMain;
    SNavigationDrawer sNavigationDrawer;

    private ArrayList<Anime> listAnime;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter2 adapter2;
    SearchView searchMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAnime = new ArrayList<>();

        init();
        search();
        jsonrequest();
        snavigationdrawer();
    }

    private void snavigationdrawer() {

        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Home", R.drawable.white));
        menuItems.add(new MenuItem("Danh mục", R.drawable.white));
        menuItems.add(new MenuItem("Favorite", R.drawable.white));
        menuItems.add(new MenuItem("FaceBook", R.drawable.white));
        menuItems.add(new MenuItem("Thoát", R.drawable.white));
        sNavigationDrawer.setMenuItemList(menuItems);




        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);

                switch (position) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(MainActivity.this, CategorieActivity.class));
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(MainActivity.this, FavolistActivity.class));
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(MainActivity.this, ContactActivity.class));
                        break;
                    }
                    case 4: {
                        logout(MainActivity.this);

                        break;
                    }

                }
            }
        });
        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {

            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

            private void init() {
                sNavigationDrawer = findViewById(R.id.navigationDrawer);
                searchMain = findViewById(R.id.seach_main);
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView2 = findViewById(R.id.recyclerview1);

            }



            private void jsonrequest() {
                request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
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
                                anime.setIdchap(jsonObject.getString("idchap"));
                                listAnime.add(anime);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setuprecyclerview(listAnime);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );
                requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(request);
            }

            private void setuprecyclerview(ArrayList<Anime> lsAnime) {
                adapter = new RecyclerViewAdapter(this, lsAnime);
                adapter2 = new RecyclerViewAdapter2(this,lsAnime);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

                ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
                        Intent i = new Intent(MainActivity.this, FavolistActivity.class);
                        startActivity(i);


                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView2.setAdapter(adapter2);
            }

            private void search() {
                searchMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            }


            private void logout(MainActivity mainActivity) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mainActivity);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainActivity.finishAffinity();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }


        }