package com.example.a01;

public class AdminList {

    private int id;
    private String name;

    public AdminList() {}

    public AdminList(String name) {
        this.name = name;
    }


    public AdminList(int id, String name) {
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
