package com.example.myapplication.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.PrefenConfig;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.adapters.ReadAdapter;
import com.example.myapplication.api.ReadApi;
import com.example.myapplication.interfaces.GetRead;
import com.example.myapplication.model.Anime;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Read;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class ReadActivity extends AppCompatActivity implements GetRead {
    Toolbar toolbar;
    JustifyTextView justifyTextView;
    BottomSheetDialog dialog;
    TextView tvChap;
    ImageButton imgBack;
    String idChap, tenChap;

    int position = -1;
    int numChap = 0;
    int textSize = 24;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        position = getIntent().getIntExtra("POSITION", -1);
        numChap = getIntent().getIntExtra("numChap", 0);



        onClick();

        new ReadApi(this, (position + 1),idChap).execute();

    }


    private void init() {
        Bundle b = getIntent().getBundleExtra("data");
        idChap = b.getString("idChap");
        tenChap = b.getString("nameChap");

        tvChap = findViewById(R.id.tv_Numchap);
        toolbar = findViewById(R.id.tb_toolbar);
        justifyTextView = findViewById(R.id.jt_textview);
        imgBack = findViewById(R.id.imgb_back);
        linearLayout = findViewById(R.id.ll_read);
    }

    private void onClick() {
        justifyTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (toolbar.isShown() & tvChap.isShown()) {
                    toolbar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadActivity.super.finish();
            }
        });

    }


    public void font(View view) {
        dialog = new BottomSheetDialog(ReadActivity.this, R.style.BottomSheetTheme);
        View v = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_bottom_sheet, (ViewGroup) findViewById(R.id.ln_bottom_sheet));

        v.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSize += 2;
                PrefenConfig.savaSizeText(getApplicationContext(), textSize);
                if (textSize <= 30) {
                    justifyTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }
            }
        });
        v.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSize -= 2;
                PrefenConfig.savaSizeText(getApplicationContext(), textSize);
                if (textSize >= 18) {
                    justifyTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }
            }
        });
        dialog.setContentView(v);
        dialog.show();
    }

    @Override
    public void start() {

    }

    @Override
    public void end(String data) {

        try {

            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject employee = array.getJSONObject(i);
                String firstName = employee.getString("truyen");
                justifyTextView.setText(firstName);
                tvChap.setText(tenChap);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void err() {

    }


}


