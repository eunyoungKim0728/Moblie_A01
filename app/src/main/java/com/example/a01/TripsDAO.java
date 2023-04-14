/*  -- Interface Header Comment
   File name   :   TripsDAO
   Description :   Implements an interface to create a table called trips in a database.
*/

package com.example.a01;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TripsDAO {
    @Insert
    void insert (Trips... trips);

    @Update
    void update (Trips... trips);

    @Delete
    void delete (Trips... trips);

    // Select all columns and rows from table Trips
    @Query("SELECT * FROM Trips")
    java.util.List<Trips> getTrips();

    // Select all from trips specifying an user ID
    @Query("SELECT * FROM Trips WHERE user_id" +
            " = (SELECT user_id FROM Users WHERE user_id = :userId) ")
    java.util.List<Trips> getTripsFromUserID(long userId);

    // Select all from trips specifying a city ID
    @Query("SELECT * FROM Trips WHERE city_id" +
            " = (SELECT city_id FROM Cities WHERE city_id = :cityId) ")
    java.util.List<Trips> getTripsFromCityID(long cityId);

    // Select all from trips specifying a user name
    @Query("SELECT * FROM Trips WHERE user_id" +
            " = (SELECT user_id FROM Users WHERE user_name = :userName) ")
    java.util.List<Trips> getTripsFromUserName(String userName);

    // Select packing_list from trips specifying a user name
    @Query("SELECT packing_list FROM Trips WHERE user_id" +
            " = (SELECT user_id FROM Users WHERE user_name = :userName) ")
    String getPackingListFromUserName(String userName);
}
