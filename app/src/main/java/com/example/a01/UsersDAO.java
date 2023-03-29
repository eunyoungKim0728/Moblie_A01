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

    // Select the id by the name of the user
    @Query("SELECT user_id FROM Users WHERE user_name=:name")
    int getUserIDByName(String name);

    // Select all names from User
    @Query("SELECT user_name FROM Users")
    java.util.List<String> getAllUsersName();

    // Select all columns and rows from table Users
    /*@Query("SELECT * FROM Users WHERE user_id=" +
            "(SELECT user_id FROM Users WHERE user_name=:name)")
    java.util.List<Users> getUserInfo();*/
}
