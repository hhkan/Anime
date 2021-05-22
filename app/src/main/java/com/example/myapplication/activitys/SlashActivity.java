package com.example.myapplication.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;


public class SlashActivity extends AppCompatActivity {

    LinearLayout constrainMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);

        constrainMain = findViewById(R.id.layout_main);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actiNetWorl = manager.getActiveNetworkInfo();
        if (null != actiNetWorl) {
            if (actiNetWorl.getType() == ConnectivityManager.TYPE_WIFI) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        constrainMain.setVisibility(View.GONE);
                        finish();
                    }
                }, 1500);
//                Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            }
            if (actiNetWorl.getType() == ConnectivityManager.TYPE_MOBILE) {
//                Toast.makeText(this, "Data network Enabled", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new
                                Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }, 1500);
            }
        } else {
            Toast.makeText(this, "No Internet Conection", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }
}