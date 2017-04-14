package com.example.kaine.loginwithreg.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kaine on 2016/12/15.
 */

public class MySqliteHelper extends SQLiteOpenHelper{
    //自定义访问sqlite
    public MySqliteHelper(Context context) {
        super(context, "userdb.db", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id int primary key ,name text,pwd text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
