package com.example.a01.database;

import android.content.ContentValues;

public class Admin {

    private long adminId;
    private long infoId;
    private String name;
    private String info;
    private int hidden;

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public Admin() {
        name = "";
        info = "";
        hidden = FALSE;
    }

    public Admin(int infoId, String name, String info,
                 int hidden) {
        this.infoId = infoId;
        this.name = name;
        this.info = info;
        this.hidden = hidden;
    }

    public Admin(int adminId, int infoId, String name, String info,
                 int hidden) {
        this.adminId = adminId;
        this.infoId = infoId;
        this.name = name;
        this.info = info;
        this.hidden = hidden;
    }

    public Admin(ContentValues values) {
        this.infoId = (long)values.get(AdminListDB.INFO_ID);
        this.name = (String)values.get(AdminListDB.ADMIN_NAME);
        this.info = (String)values.get(AdminListDB.ADMIN_INFO);
        this.hidden = (int)values.get(AdminListDB.ADMIN_HIDDEN);
    }

    public long getId() {
        return adminId;
    }

    public void setId(long adminId) {
        this.adminId = adminId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getHidden(){
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }
}
