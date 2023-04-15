package com.example.a01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdminListDB {

    private static final String TAG= "AdminListDB";

    // database constants
    public static final String DB_NAME = "adminlist.db";
    public static final int    DB_VERSION = 1;

    // list table constants
    public static final String INFO_TABLE = "list";

    public static final String INFO_ID = "_id";
    public static final int    INFO_ID_COL = 0;

    public static final String INFO_NAME = "info_name";
    public static final int    INFO_NAME_COL = 1;

    // task table constants
    public static final String ADMIN_TABLE = "admin";

    public static final String ADMIN_ID = "_id";
    public static final int    ADMIN_ID_COL = 0;

    public static final String ADMIN_INFO_ID = "admin_id";
    public static final int    ADMIN_INFO_ID_COL = 1;

    public static final String ADMIN_NAME = "admin_name";
    public static final int    ADMIN_NAME_COL = 2;

    public static final String ADMIN_INFO = "info";
    public static final int    ADMIN_INFO_COL = 3;


    public static final String ADMIN_HIDDEN = "hidden";
    public static final int    ADMIN_HIDDEN_COL = 5;

    // CREATE and DROP TABLE statements
    public static final String CREATE_INFO_TABLE =
            "CREATE TABLE " + INFO_TABLE + " (" +
                    INFO_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    INFO_NAME + " TEXT    NOT NULL UNIQUE);";

    public static final String CREATE_ADMIN_TABLE =
            "CREATE TABLE " + ADMIN_TABLE + " (" +
                    ADMIN_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ADMIN_INFO_ID    + " INTEGER NOT NULL, " +
                    ADMIN_NAME       + " TEXT    NOT NULL, " +
                    ADMIN_INFO      + " TEXT, " +
                    ADMIN_HIDDEN     + " INTEGER);";

    public static final String DROP_INFO_TABLE =
            "DROP TABLE IF EXISTS " + INFO_TABLE;

    public static final String DROP_ADMIN_TABLE =
            "DROP TABLE IF EXISTS " + ADMIN_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_INFO_TABLE);
            db.execSQL(CREATE_ADMIN_TABLE);

            // insert default lists
            db.execSQL("INSERT INTO list VALUES (1, 'Ella Kim')");
            db.execSQL("INSERT INTO list VALUES (2, 'CEO')");

            // insert sample tasks
            db.execSQL("INSERT INTO admin VALUES ( 1, 'Eunyoung Kim', " +
                    "'Number: (+1)123-456-7890\nEmail: ekim2911@conestogac.on.ca\n', 0, 0)");
            db.execSQL("INSERT INTO admin VALUES (2, 'Yujin Jeong', " +
                    "'Number: (+1)987-654-3210\nEmail: ...@conestogac.on.ca\n', 0, 0)");
            db.execSQL("INSERT INTO admin VALUES (3, 'Maisa Wolff Resplande\n', " +
                    "'Number: (+1)456-123-0789\nEmail: ...@conestogac.on.ca\n', 0, 0)");
            db.execSQL("INSERT INTO admin VALUES (4, 'Hyewon Lee', " +
                    "'Number: (+1)951-753-0258\nEmail: ...@conestogac.on.ca\n', 0, 0)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d(TAG,"Admin list");

            db.execSQL(AdminListDB.DROP_INFO_TABLE);
            db.execSQL(AdminListDB.DROP_ADMIN_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public AdminListDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    // public methods
    public ArrayList<AdminList> getLists() {
        ArrayList<AdminList> lists = new ArrayList<AdminList>();
        openReadableDB();
        Cursor cursor = db.query(INFO_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            AdminList list = new AdminList();
            list.setId(cursor.getInt(INFO_ID_COL));
            list.setName(cursor.getString(INFO_NAME_COL));

            lists.add(list);
        }
        closeCursor(cursor);
        closeDB();

        return lists;
    }

    public AdminList getList(String name) {
        String where = INFO_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = db.query(INFO_TABLE, null,
                where, whereArgs, null, null, null);
        AdminList list = null;
        cursor.moveToFirst();
        list = new AdminList(cursor.getInt(INFO_ID_COL),
                cursor.getString(INFO_NAME_COL));
        this.closeCursor(cursor);
        this.closeDB();

        return list;
    }

    public Cursor getTaskCursor(String infoName) {
        String where =
                ADMIN_INFO_ID + "= ? AND " +
                        ADMIN_HIDDEN + "!=1";
        int listID = getList(infoName).getId();
        String[] whereArgs = { Integer.toString(listID) };

        this.openReadableDB();
        Cursor cursor = db.query(ADMIN_TABLE, null,
                where, whereArgs,
                null, null, null);
        return cursor;
    }

    public Cursor queryTasks(String[] columns, String where,
                             String[] whereArgs, String orderBy) {
        this.openReadableDB();
        Cursor cursor = db.query(ADMIN_TABLE, columns,
                where, whereArgs,
                null, null, orderBy);
        return cursor;
    }

    public ArrayList<Admin> getTasks(String listName) {
        String where =
                ADMIN_INFO_ID + "= ? AND " +
                        ADMIN_HIDDEN + "!=1";
        int listID = getList(listName).getId();
        String[] whereArgs = { Integer.toString(listID) };

        this.openReadableDB();
        Cursor cursor = db.query(ADMIN_TABLE, null,
                where, whereArgs,
                null, null, null);
        ArrayList<Admin> tasks = new ArrayList<Admin>();
        while (cursor.moveToNext()) {
            tasks.add(getTaskFromCursor(cursor));
        }
        this.closeCursor(cursor);
        this.closeDB();

        return tasks;
    }

    public Admin getTask(int id) {
        String where = ADMIN_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(ADMIN_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Admin task = getTaskFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        return task;
    }

    private static Admin getTaskFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Admin task = new Admin(
                        cursor.getInt(ADMIN_ID_COL),
                        cursor.getInt(ADMIN_INFO_ID_COL),
                        cursor.getString(ADMIN_NAME_COL),
                        cursor.getString(ADMIN_INFO_COL),
                        cursor.getInt(ADMIN_HIDDEN_COL));
                return task;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    public long insertTask(Admin task) {
        ContentValues cv = new ContentValues();
        cv.put(ADMIN_INFO_ID, task.getId());
        cv.put(ADMIN_NAME, task.getName());
        cv.put(ADMIN_INFO, task.getInfo());
        cv.put(ADMIN_HIDDEN, task.getHidden());

        this.openWriteableDB();
        long rowID = db.insert(ADMIN_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateTask(Admin task) {
        ContentValues cv = new ContentValues();
        cv.put(ADMIN_INFO_ID, task.getId());
        cv.put(ADMIN_NAME, task.getName());
        cv.put(ADMIN_INFO, task.getInfo());
        cv.put(ADMIN_HIDDEN, task.getHidden());

        String where = ADMIN_ID + "= ?";
        String[] whereArgs = { String.valueOf(task.getId()) };

        this.openWriteableDB();
        int rowCount = db.update(ADMIN_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int updateTask(ContentValues cv, String where, String[] whereArgs) {
        this.openWriteableDB();
        int rowCount = db.update(ADMIN_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteTask(String where, String[] whereArgs) {
        this.openWriteableDB();
        int rowCount = db.delete(ADMIN_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteTask(long id) {
        String where = ADMIN_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(ADMIN_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

}
