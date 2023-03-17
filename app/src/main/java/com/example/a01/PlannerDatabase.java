/*  -- Class Header Comment
   File name   :   PlannerDatabase.java
   Description :   Abstract class to define the properties of the application database.
*/
package com.example.a01;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Trips.class, Cities.class, Users.class}, version=1)
public abstract class PlannerDatabase extends RoomDatabase {
    private static PlannerDatabase instance = null;
    public abstract TripsDAO getTripsDAO();
    public abstract CitiesDAO getCitiesDAO();
    public abstract UsersDAO getUsersDAO();

    public static synchronized PlannerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                            PlannerDatabase.class, "planner.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
