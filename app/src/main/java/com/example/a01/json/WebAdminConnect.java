package com.example.a01.json;

import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class WebAdminConnect {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int DATARETRIEVAL_TIMEOUT = 30000;

    public static JSONObject requestWebAdminConnect(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {

            } else if (statusCode != HttpURLConnection.HTTP_OK) {

            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (SocketTimeoutException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
        private static void disableConnectionReuseIfNecessary() {

            if (Build.VERSION.SDK_INT <  Build.VERSION_CODES.FROYO) {
                System.setProperty("http.keepAlive", "false");
            }
        }

        private static String getResponseText(InputStream inStream) {

            return new Scanner(inStream).useDelimiter("\\A").next();
        }
}
