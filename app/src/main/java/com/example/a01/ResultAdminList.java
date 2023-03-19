//FILE          : ResultAdminList.java
//PROJECT       : PROG3150 - assignment 2
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This file shows the result of data
//
package com.example.a01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.a01.databinding.AdminInfoBinding;
import com.example.a01.databinding.AdminInfoListBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultAdminList extends AsyncTask<Void, Void, MyResult> {
    private Context myContext = null;
    private Activity activity;
    private AdminInfoBinding binding;
    public static final String TAG= "ResultAdminList";
    public ResultAdminList() {

    }

    public ResultAdminList(Context context) {
        this.myContext = context;
        }

    @Override
    protected MyResult doInBackground(Void... unused) {
        MyResult result = null;
        Log.i(TAG,"In Call service");


        try {

            JSONArray serviceItems = WebAdminConnect.requestWebAdminConnect(
                    "http://127.0.0.1:3000/root").getJSONArray("guides");

            result = new MyResult();
            ArrayList<HashMap<String, String>> resultData = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < serviceItems.length(); i++) {
                JSONObject obj = serviceItems.getJSONObject(i);
                HashMap<String, String> singleResult = new HashMap<String, String>();
                singleResult.put("id", obj.getString("id"));
                singleResult.put("name", obj.getString("name"));
                singleResult.put("email", obj.getString("email"));
                singleResult.put("number", obj.getString("number"));
                resultData.add(singleResult);
            }
            result.setData(resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(MyResult result) {
        super.onPostExecute(result);

        Log.i(TAG,"onPostExecute");

        if (result != null) {
            String[] from = new String[]{"id", "name","email", "number"};
            int[] to = new int[]{R.id.adminID, R.id.adminName, R.id.adminEmail, R.id.adminNumber};

            SimpleAdapter myAdapter = new SimpleAdapter(myContext, result.getData(), R.layout.admin_info_list, from, to);
            ListView myList = binding.adminListView;
            myList.setAdapter(myAdapter);
        } else {
            Log.e("ResultAdminList", "Result is null");
        }
    }

}

