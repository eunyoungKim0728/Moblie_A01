/*  -- Class Header Comment
   File name   :   Users
   Description :   Define the properties of table called Users in the database.
*/
package com.example.a01;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName="Users")
public class Users {
    @PrimaryKey
    @NonNull
    private int id;
    @NonNull
    private String name;
    //TODO ADD FOREIGN KEY
    @NonNull
    private int trip_id;

    private String packing_list;

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
    public void setPackingList(String packing_list) {
        this.packing_list = packing_list;
    }
    public String getPackingList() {
        return packing_list;
    }
}
