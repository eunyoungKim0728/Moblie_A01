/*  -- Interface Header Comment
   File name   :   UsersDAO
   Description :   Implements an interface to create a table called users in a database.
*/

package com.example.a01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsersDAO {

    @Insert
    void insert (Users... users);

    @Update
    void update (Users... users);

    @Delete
    void delete (Users... users);

    // Select all columns and rows from table Users
    @Query("SELECT * FROM Users")
    java.util.List<Users> getUsers();

    // Select the trip_id by the name of the user
    @Query("SELECT id FROM Users WHERE name=:name")
    int getUserIDByName(String name);
}
