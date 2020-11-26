package com.buios.buios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    static String name="user_db";
    static int version=1;
    String createTableUser="CREATE TABLE if not exists 'user' ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT,"+" 'password' TEXT, 'email' TEXT)";

    public UserDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        getWritableDatabase().execSQL(createTableUser);
    }

    public void insertUser(ContentValues contentValue){
        getWritableDatabase().insert("user","", contentValue);
    }
    //id pw 비교하는 메소드 필요

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
