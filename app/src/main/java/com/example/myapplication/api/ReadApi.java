package com.example.myapplication.api;

import android.os.AsyncTask;

import com.example.myapplication.interfaces.GetChapter;
import com.example.myapplication.interfaces.GetRead;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReadApi extends AsyncTask<Void, Void, Void> {
    String data;
    GetRead getRead;
    int soChap;
    String tenChap;

//    public ReadApi( GetRead getRead,String chuong, String idread ,int soChap) {
//        this.getRead = getRead;
//        this.chuong = chuong;
//        this.soChap = soChap;
//        this.tenChap = idread;
//
//
//    }

    public ReadApi(GetRead getRead, int soChap, String tenChap) {
        this.getRead = getRead;
        this.soChap = soChap;
        this.tenChap = tenChap;
        getRead.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://json-server-15.herokuapp.com/api/doctruyen?idread=chuong"+soChap+":"+tenChap).build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        } catch (IOException e) {
            data = null;
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        if (data ==null){
            this.getRead.err();
        }else {
            this.getRead.end(data);
        }
    }
}
