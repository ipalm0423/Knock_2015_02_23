package com.morphius.myapplicationt123;


import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;


public class MyDBhelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Friend.db";
    public static final String TABLE_NAME = "FriendList";
    public static final int VERSION = 1;
    public static final String NAME_COLUMN = "name";
    public static final String HIDEID_COLUMN = "hideID";
    public static final String COUNTTIME_COLUMN = "counttimer";

    public MyDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public MyDBhelper(Context context){
        this(context, DATABASE_NAME, null, VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db){





        final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN + " TEXT NOT NULL, " +
                HIDEID_COLUMN + " TEXT, " +
                COUNTTIME_COLUMN + " INTEGER)";
        db.execSQL(INIT_TABLE);

    }
    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}
