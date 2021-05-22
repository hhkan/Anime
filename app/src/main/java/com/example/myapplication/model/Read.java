package com.example.myapplication.model;

public class Read {
    String id;
    String read;
    String idRead;

    public Read(String id, String read, String idRead) {
        this.id = id;
        this.read = read;
        this.idRead = idRead;
    }

    public Read() {
    }

    public String getIdRead() {
        return idRead;
    }

    public void setIdRead(String idRead) {
        this.idRead = idRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}
