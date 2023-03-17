package com.example.a01;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Trips")
public class Trips {

    // Define properties and column name
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    @NonNull
    @ColumnInfo(name="trip_id")
    private long tripId;

    @NonNull
    private long city_id;
    @NonNull
    @ColumnInfo(name="city_id")
    private long cityId;

    @NonNull
    private long user_id;
    @NonNull
    @ColumnInfo(name="user_id")
    private long userId;

    private String packing_list;
    @ColumnInfo(name="packing_list")
    private String packingList;

    // TODO check about the constructors
    public Trips() {}

    public Trips(int tripId, int cityId, int userId) {
        this.tripId = tripId;
        this.cityId = cityId;
        this.userId = userId;
        this.packingList = "";
    }

    // Setter and getters to table's properties
    public long getId() {
        return id;
    }

    public void setId(long tripId) {
        this.id = tripId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPackingList(String packing_list) {
        this.packing_list = packing_list;
    }

    public String getPackingList() {
        return packing_list;
    }
}