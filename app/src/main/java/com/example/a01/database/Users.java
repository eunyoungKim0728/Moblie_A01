/*  -- Class Header Comment
   File name   :   Users.java
   Description :   Define the properties of table called Users in the database.
*/
package com.example.a01.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

// Define table Users
@Entity(tableName="Users")

// Define class for Users
public class Users {

    // Define properties and columns' names
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="user_id")
    private long userId;

    @NonNull
    @ColumnInfo(name="user_name")
    private String userName;

    // Constructors
    @Ignore
    public Users() {}

    public Users(String userName) {
        this.userName = userName;
    }

    // Setter and getters for the table's properties
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

}
