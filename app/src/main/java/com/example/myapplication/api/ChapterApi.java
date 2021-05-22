package com.example.myapplication.api;

import android.os.AsyncTask;

import com.example.myapplication.interfaces.GetChapter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ChapterApi extends AsyncTask<Void, Void, Void> {
    String data;
    GetChapter getChapter;
    String idTruyen;

    public ChapterApi(GetChapter getChapter, String idTruyen) {
        this.getChapter = getChapter;
        this.getChapter.start();
        this.idTruyen = idTruyen;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://json-server-15.herokuapp.com/api/dschap?idchap="+idTruyen).build();
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
            this.getChapter.err();
        }else {
            this.getChapter.end(data);
        }
    }
}
