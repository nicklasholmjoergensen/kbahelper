package io.peqo.kbahelper.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import io.peqo.kbahelper.db.DbHelper;

public class UserDao {

    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public UserDao(Context context) {
        dbHelper =  new DbHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }



}
