package com.acompany.photoi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mvera on 27/06/16.
 */
public class PhotoiSQLiteManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "photoidev.db";
    private static final int DATABASE_VERSION = 1;

    public static final String USER_DATA_COLUMN = "userdata";
    public static final String USER_TABLE = "user";

    public static final String PRODUCT_DATA_COLUMN = "productdata";
    public static final String PRODUCT_TABLE = "product";

    public static final String PAYMENT_REQUEST_TABLE = "payment_request";

    // Database creation sql statement
    private static final String USER_CREATE = "create table " + USER_TABLE + " (_id integer primary key autoincrement, userdata text not null);";
    private static final String PRODUCT_CREATE = "create table " + PRODUCT_TABLE + " (_id integer primary key autoincrement, productdata text not null);";
    private static final String PAYMENT_REQUEST_CREATE = "create table " + PAYMENT_REQUEST_TABLE + "  (_id integer primary key autoincrement, user_id integer,product_id integer,foreign key(user_id) references users(_id),foreign key(product_id) references product(_id));";


    public PhotoiSQLiteManager(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_CREATE);
        db.execSQL(PRODUCT_CREATE);
        db.execSQL(PAYMENT_REQUEST_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(PhotoiSQLiteManager.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS product");
        db.execSQL("DROP TABLE IF EXISTS payment_request_create");
        onCreate(db);
    }
}
