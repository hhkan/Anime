package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefenConfig {
    private static final String MY_PREFENCES_NAME = "com.example.thieuthuyet.preferece";
    private static final String PREF_TOTAL_KEY = "pref_total_key";

    public static  void  savaSizeText(Context context, int total){
        SharedPreferences preferences = context.getSharedPreferences(MY_PREFENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_TOTAL_KEY, total);
        editor.apply();
    }
    public static int loadTotalFromPref(Context context){
        SharedPreferences preferences = context.getSharedPreferences(MY_PREFENCES_NAME,Context.MODE_PRIVATE);
        return preferences.getInt(PREF_TOTAL_KEY,0);
    }
}
