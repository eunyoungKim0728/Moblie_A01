//FILE          : MyResult.java
//PROJECT       : PROG3150 - assignment 3
//PROGRAMMER    : Yujin Jeong, Eunyoung Kim. Hyewon Lee, Maísa Wolff Resplande
//FIRST VERSION : 2023.03.18
//DESCRIPTION   : This file gets the data and receive the data value
//


package com.example.a01;

import java.util.ArrayList;
import java.util.HashMap;

/*  -- Class Header Comment
 Name    :   MyResult
 Purpose :  Class to run the ArrayList
 */
public class MyResult {

    private ArrayList<HashMap<String, String>> data;

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    public void setData(ArrayList<HashMap<String, String>> data) {
        this.data = data;
    }

}
