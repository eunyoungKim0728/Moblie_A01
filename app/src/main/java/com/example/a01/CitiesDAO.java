/*  -- Interface Header Comment
   File name   :   CitiesDAO
   Description :   Implements an interface to create a table called cities in a database.
*/
package com.example.a01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CitiesDAO {
    @Insert
    void insert (Cities... cities);

    @Update
    void update (Cities... cities);

    @Delete
    void delete (Cities... cities);

    // Select all columns and rows from table Cities
    @Query("SELECT * FROM Cities")
    java.util.List<Cities> getCities();

    // Select the id by the name of the city
    @Query("SELECT id FROM Cities WHERE name=:name")
    int getCityIDByName(String name);
}
