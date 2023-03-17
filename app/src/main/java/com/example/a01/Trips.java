/*  -- Class Header Comment
   File name   :   Trips.java
   Description :   Define the properties of table called Trips in the database.
*/
package com.example.a01;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

// Define table Trips and constraint foreign keys
@Entity(tableName="Trips", foreignKeys = {@ForeignKey(entity = Cities.class,
        parentColumns = "city_id",
        childColumns = "city_id",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Users.class,
        parentColumns = "user_id",
        childColumns = "user_id",
        onDelete = ForeignKey.CASCADE)
        })

// Define class for Trips
public class Trips {

    // Define properties and columns' names
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="trip_id")
    private long id;

    @NonNull
    @ColumnInfo(name="city_id")
    private long cityId;

    @NonNull
    @ColumnInfo(name="user_id")
    private long userId;

    @ColumnInfo(name="packing_list")
    private String packingList;

    // Constructors
    // TODO check about the constructors
    public Trips() {}

    public Trips(int tripId, int cityId, int userId) {
        this.id = tripId;
        this.cityId = cityId;
        this.userId = userId;
        this.packingList = "";
    }

    // Setter and getters to table's properties
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getPackingList() {
        return packingList;
    }
}