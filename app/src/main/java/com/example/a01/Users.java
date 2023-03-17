/*  -- Class Header Comment
   File name   :   Users.java
   Description :   Define the properties of table called Users in the database.
*/
package com.example.a01;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName="Users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    @ColumnInfo(name="user_id")
    private long userId;

    @NonNull
    private String name;
    @NonNull
    @ColumnInfo(name="user_name")
    private long userName;

    //TODO ADD FOREIGN KEY
    @NonNull
    private int trip_id;
    @NonNull
    @ColumnInfo(name="trip_id")
    private long tripID;

    public Users() {}

    // TODO check if this part of the code can be deleted
    /* @Ignore
    public Users(String name) {
        this.name = name;
    }
    @Ignore
    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }*/

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
    public void setTripId(int trip_id) {this.trip_id = trip_id;}
    public int getTripId() {
        return trip_id;
    }

}
