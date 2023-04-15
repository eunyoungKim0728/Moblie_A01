package com.example.a01.uilayer;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.a01.R;
import com.example.a01.contentprovider.AdminContentProvider;
import com.example.a01.database.AdminListDB;
import com.example.a01.databinding.AdminInfoBinding;

import android.os.Bundle;

public class AdminInfo extends AppCompatActivity {

    private static final String TAG= "AdminInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Admin Info OnClick");

        // binding layout
        AdminInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.admin_info);

        // get URI for cursor
        Uri adminUri = Uri.parse("content://" + AdminContentProvider.PATH + "/admin");
        Cursor cursor = getContentResolver().query(adminUri,null,null,null,null);

        // define information for table
        String[] columns = {AdminListDB.ADMIN_NAME, AdminListDB.ADMIN_INFO};
        int[] to = {R.id.adminName, R.id.adminInfo};

        // instantiate simple cursor adapter for items in the list
        SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.admin_info_list,
                cursor,
                columns, to, 0
        );

        // display admin info
        ListView adminList = binding.adminListView;
        adminList.setAdapter(myAdapter);
    }
}
