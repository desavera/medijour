package com.acompany.photoi;

import android.util.Log;

import com.acompany.photoi.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by mvera on 29/06/16.
 */
public class RestAPIFetcher {



    public static final String SERVER_URL = "http://192.168.1.105:8080";
    private static final String TAG = "RestAPIFetcher";

    private static RestAPIFetcher ourInstance = new RestAPIFetcher();



    public static RestAPIFetcher getInstance() {
        return ourInstance;
    }

    private RestAPIFetcher() {
    }

    protected User fetchUser(String username) {

        try {
            //Create an HTTP client
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(SERVER_URL + "/users/" + username);

            //Perform the request and check the status code
            HttpResponse response = client.execute(get);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();

                try {
                    //Read the server response and attempt to parse it as JSON
                    Reader reader = new InputStreamReader(content);

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    User user = gson.fromJson(reader, User.class);
                    content.close();

                    return user;

                } catch (Exception ex) {
                    Log.e(TAG, "Failed to parse JSON due to: " + ex);

                }
            } else {
                Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
            }
        } catch(Exception ex) {
            Log.e(TAG, "Failed to send HTTP GET request due to: " + ex);
        }
        return null;
    }

    protected void userLogin(User user) {

        try {
            //Create an HTTP client
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(SERVER_URL + "/users/login");


            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
            Gson gson = gsonBuilder.create();
            String jsonBody = gson.toJson(user);

            StringEntity se = new StringEntity( "JSON: " + jsonBody.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

            HttpResponse response = client.execute(post);

        } catch(Exception ex) {
            Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
        }

    }

    protected void userLogout(User user) {

        try {
            //Create an HTTP client
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(SERVER_URL + "/users/logout");


            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
            Gson gson = gsonBuilder.create();
            String jsonBody = gson.toJson(user);

            StringEntity se = new StringEntity( "JSON: " + jsonBody.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

            HttpResponse response = client.execute(post);

        } catch(Exception ex) {
            Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
        }
    }


}
