//FILE          : MyFragment.java
//PROJECT       : PROG3150 - assignment 2
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This fragmet file is going to ResultAdminList
//


package com.example.a01;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a01.json.ResultAdminList;


public class MyFragment extends Fragment {

    public static final String TAG= "MyFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateView");
        return inflater.inflate(R.layout.admin_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        new ResultAdminList().execute();

    }
    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {

        Log.i(TAG, "onPause");
        super.onPause();
    }
}
