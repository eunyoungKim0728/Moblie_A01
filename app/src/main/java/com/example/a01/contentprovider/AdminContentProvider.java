package com.example.a01.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.a01.database.Admin;
import com.example.a01.database.AdminListDB;

public class AdminContentProvider extends ContentProvider {

    public static final String PATH = "com.example.a01";

    public static final int NO_MATCH = -1;
    public static final int ALL_LIST_URI = 0;
    public static final int SINGLE_URI = 1;

    private AdminListDB db = null;
    private UriMatcher uriMatcher = null;
    private static final String TAG= "AdminContentProvider";


    public AdminContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleteCount = 0;
        switch (uriMatcher.match(uri)) {
            case SINGLE_URI:
                String adminId = uri.getLastPathSegment();
                deleteCount = db.deleteAdmin(Long.getLong(adminId));
                getContext().getContentResolver().notifyChange(uri, null);
                return deleteCount;
            case ALL_LIST_URI:
                deleteCount = db.deleteAdmin(selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return deleteCount;
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch(uriMatcher.match(uri)) {
            case ALL_LIST_URI:
                return "vnd.android.cursor.dir/vnd.a03.adminlist.list";
            case SINGLE_URI:
                return "vnd.android.cursor.item/vnd.a03.adminlist.list";
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case ALL_LIST_URI:
                long insertId = db.insertAdmin(new Admin(values));
                getContext().getContentResolver().notifyChange(uri, null);
                return uri.buildUpon().appendPath(
                        String.valueOf(insertId)).build();
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }

    @Override
    public boolean onCreate() {
        db = new AdminListDB(getContext());
        uriMatcher = new UriMatcher(NO_MATCH);
        uriMatcher.addURI(PATH, "admin", ALL_LIST_URI);
        uriMatcher.addURI(PATH, "admin/#", SINGLE_URI);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case ALL_LIST_URI:
                return db.queryAdmins(projection, selection,
                        selectionArgs, sortOrder);
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount = 0;
        switch (uriMatcher.match(uri)) {
            case SINGLE_URI:
                String adminId = uri.getLastPathSegment();
                Admin admin = new Admin(values);
                admin.setId(Integer.getInteger(adminId));
                updateCount = db.updateAdmin(admin);
                getContext().getContentResolver().notifyChange(uri, null);
                return updateCount;
            case ALL_LIST_URI:
                updateCount = db.updateAdmin(values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return updateCount;
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }


}