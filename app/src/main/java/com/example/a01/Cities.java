/*  -- Class Header Comment
   File name   :   Cities.java
   Description :   Define the properties of table called Cities in the database.
*/
package com.example.a01;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Cities {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    @ColumnInfo(name="city_id")
    private long cityId;

    @NonNull
    private String name;
    @NonNull
    @ColumnInfo(name="city_name")
    private long cityName;

    //TODO ADD FOREIGN KEY (?)

    public Cities() {}

    // Setter and getters for the table's properties
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
