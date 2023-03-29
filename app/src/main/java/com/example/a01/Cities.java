/*  -- Class Header Comment
   File name   :   Cities.java
   Description :   Define the properties of table called Cities in the database.
*/
package com.example.a01;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define table Cities
@Entity(tableName="Cities")

// Define class for Cities
public class Cities {

    // Define properties and columns' names
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="city_id")
    private long cityId;

    @NonNull
    @ColumnInfo(name="city_name")
    private String cityName;

    private String price;
    private String[] tourGuide;
    private String[] tourSpot;

    // Constructors
    public Cities() {}

    public Cities(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    // Setter and getters for the table's properties
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
    public long getCityId() {
        return cityId;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String[] getTourGuide() { return tourGuide; }
    public void setTourGuide(String[] tourGuide) { this.tourGuide = tourGuide; }
    public String[] getTourSpot() { return tourSpot; }
    public void setTourSpot(String[] tourSpot) { this.tourSpot = tourSpot; }
}
