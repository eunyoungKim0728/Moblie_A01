package com.example.a01.database;

import android.util.Log;

public class AdminList {

    private int id;
    private String name;
    private static final String TAG= "AdminList";

    public AdminList() {}

    public AdminList(String name) {
        Log.d(TAG, "OneParameter/name");
        this.name = name;
    }


    public AdminList(int id, String name) {
        Log.d(TAG, "TwoParameter/id,name");
        this.id = id;
        this.name = name;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
