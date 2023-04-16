//FILE          : MyFragment.java
//PROJECT       : PROG3150 - assignment 3
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


/*  -- Class Header Comment
 Name    :   MyFragment
 Purpose :  Class to run the Fragment
 */
public class MyFragment extends Fragment {

    public static final String TAG= "MyFragment";

    /*  -- Method Header Comment
   Name     :   onCreate
   Purpose  :   Create the view
   Inputs   :   LayoutInflater  inflater
                ViewGroup       container
                Bundle          savedInstanceState
   Outputs  :   NONE
    */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateView");
        return inflater.inflate(R.layout.admin_info, container, false);
    }


    /*  -- Method Header Comment
   Name     :   onActivityCreated
   Purpose  :   Function that runs when created
   Inputs   :   savedInstanceState   Bundle
   Outputs  :   NONE
    */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        new ResultAdminList().execute();

    }


    /*  -- Method Header Comment
   Name     :   onResume
   Purpose  :   Function that runs when resumed
   Inputs   :   savedInstanceState   Bundle
   Outputs  :   NONE
    */
    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }


    /*  -- Method Header Comment
   Name     :   onPause
   Purpose  :   Function that runs when paused
   Inputs   :   NONE
   Outputs  :   NONE
    */
    @Override
    public void onPause() {

        Log.i(TAG, "onPause");
        super.onPause();
    }
}
