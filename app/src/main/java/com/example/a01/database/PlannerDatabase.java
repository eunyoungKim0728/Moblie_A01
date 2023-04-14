/*  -- Class Header Comment
   File name   :   PlannerDatabase.java
   Description :   Abstract class to define the properties of the application database.
*/
package com.example.a01.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Trips.class, Cities.class, Users.class}, version=1)
public abstract class PlannerDatabase extends RoomDatabase {
    private static final String LOG_TAG = PlannerDatabase.class.getSimpleName();
    private static PlannerDatabase instance = null;
    public abstract TripsDAO getTripsDAO();
    public abstract CitiesDAO getCitiesDAO();
    public abstract UsersDAO getUsersDAO();

    public static synchronized PlannerDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(LOG_TAG, "Creating new database instance");
            instance = Room.databaseBuilder(context,
                            PlannerDatabase.class, "planner.db")
                    .allowMainThreadQueries()
                    .build();
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

}
