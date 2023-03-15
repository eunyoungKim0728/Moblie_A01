/*
 * FILE            :RestaurantList.java
 * PROGRAMMER      :Eunyoung Kim, Yujin Jeong, Hyewon Lee, Maísa Wolff Resplande
 * FIRST VERSION   :2023-02-11
 * DESCRIPTION      : Programs that show RestarantList and can run on that screen
 */
package com.example.a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.example.a01.databinding.RestaurantListBinding;

/*  -- Class Header Comment
 Name    :   Packing
 Purpose :  Class to run the RestaurantList
 */
public class RestaurantList extends AppCompatActivity {


    /*  -- Method Header Comment
   Name   :   onCreate
   Purpose :   Function that runs when clicked
   Inputs   :   savedInstanceState   Bundle
   Outputs   :   NONE
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RestaurantListBinding binding = DataBindingUtil.setContentView(this,R.layout.restaurant_list);

        WebView webView = binding.restaurant;
        ProgressBar progressBar = binding.myProgressBar;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if(progress>=100)
                {
                    progressBar.setVisibility(View.GONE);

                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });


        webView.loadUrl("https://www.tripadvisor.ca/Restaurants-g155019-Toronto_Ontario.html");
    }


    /*  -- Method Header Comment
   Name   :   onCreateOptionsMenu
   Purpose :   Method that runs when a menu is clicked
   Inputs   :   menu   Menu
   Outputs   :   NONE
   */
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }



    /*  -- Method Header Comment
   Name   :   onOptionsItemSelected
   Purpose :   Method for displaying a list
   Inputs   :   item   MenuItem
   Outputs   :   NONE
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result =false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.Toronto:
                intent=new Intent(this,Itinerary.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Quebec:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Vancouver:
                intent=new Intent(this,NoItinerary1.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.Main:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                result = true;
                break;


            default:
                result = super.onOptionsItemSelected(item);
                break;

        }

        return result;
    }
}