package com.example.myapplication.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Chapter {
    private String tenChap, id,idChap,idRead;

    public Chapter(String tenChap,String idChap, String idRead) {
        this.tenChap = tenChap;
        this.idChap = idChap;
        this.idRead = idRead;

    }

    public Chapter() {
    }

    public String getIdChap() {
        return idChap;
    }

    public void setIdChap(String idChap) {
        this.idChap = idChap;
    }

    public String getIdRead() {
        return idRead;
    }

    public void setIdRead(String idRead) {
        this.idRead = idRead;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Chapter(JSONObject o) throws JSONException {
        tenChap = o.getString("tenChap");
        id = o.getString("id");
        idChap = o.getString("idchap");
        idRead = o.getString("idread");

    }
}
