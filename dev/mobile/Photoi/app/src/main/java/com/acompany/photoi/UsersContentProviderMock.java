package com.acompany.photoi;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.sql.DatabaseMetaData;
import java.util.Arrays;

public class UsersContentProviderMock extends ContentProvider {


    public static final String PROVIDER_NAME = "com.acompany.photoi.userscontentprovidermock";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);


    public static String[] DUMMY_CREDENTIALS = new String[]{
            "photoi:photoi"
    };


    // delete first
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int length = this.DUMMY_CREDENTIALS.length;

        String[] swap = new String[length];
        swap = Arrays.copyOf(this.DUMMY_CREDENTIALS,length);
        this.DUMMY_CREDENTIALS = new String[length - 1];

        for (int i=0;i < this.DUMMY_CREDENTIALS.length;i++) {

            this.DUMMY_CREDENTIALS[i] = swap[i+1];

        }

        return 1;
    }

    // I am my only client !
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int length = this.DUMMY_CREDENTIALS.length;

        String username = values.getAsString("username");
        String password = values.getAsString("password");

        this.DUMMY_CREDENTIALS[length] = new String (username + ':' + password);

        // returns the new dummy size
        return Uri.withAppendedPath(CONTENT_URI,new Integer(length).toString());
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {


        MatrixCursor cursor = new MatrixCursor(new String[] {"credential"},1);
        cursor.addRow(new String[] {DUMMY_CREDENTIALS[0]});

        return cursor;
    }

    // updates first
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        String username = values.getAsString("username");
        String password = values.getAsString("password");

        this.DUMMY_CREDENTIALS[0] = new String(username + ':' + password);

        // returns number of updated rows
        return 1;
    }
}
