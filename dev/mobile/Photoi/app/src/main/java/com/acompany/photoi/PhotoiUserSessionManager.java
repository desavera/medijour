package com.acompany.photoi;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by mvera on 29/06/16.
 */
public class PhotoiUserSessionManager {


    private static final String PHOTOI_SESSION_LOGIN_TOKEN = "com.acompany.photoi.extra.SESSION_TOKEN";
    private static final String PREFS_NAME = "photoi-prefs";
    public static final String NOT_VALID_TOKEN = "com.acompany.photoi.extra.INVALID_SESSION_TOKEN" ;

    private Context context;

    public PhotoiUserSessionManager(Context context) {

        this.context = context;
    }

    public void init(String username) {

        // stores the token
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(PHOTOI_SESSION_LOGIN_TOKEN,"LOGIN:" + username + ':' + new Long(new Date().getTime()).toString());

        // Commit the edits!
        editor.commit();
    }

    public void finish(String username) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();


        editor.putString(PHOTOI_SESSION_LOGIN_TOKEN,"LOGOUT:" + username + ':' + new Long(new Date().getTime()).toString());

        // Commit the edits!
        editor.commit();

    }

    public String getValidSessionUsername() {

        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString(PHOTOI_SESSION_LOGIN_TOKEN,"");

        String[] pieces = token.split(":");

        if (pieces.length == 3) {

            long loginTime = Long.parseLong(pieces[2]);
            if (pieces[0].startsWith("LOGIN") && (new Date().getTime() - loginTime <= 20*60*1000))
                return pieces[1];

        }


        return NOT_VALID_TOKEN;

    }
}
