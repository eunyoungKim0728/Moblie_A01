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


public class RestaurantList extends AppCompatActivity {

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

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result =false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.citiesPage:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.ItineraryPage:
                intent=new Intent(this,Itinerary.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.packingPage:
                intent=new Intent(this,Packing.class);
                startActivity(intent);
                result = true;
                break;
            case R.id.restaurant:
                intent=new Intent(this,RestaurantList.class);
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