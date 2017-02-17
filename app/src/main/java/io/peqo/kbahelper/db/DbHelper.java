package io.peqo.kbahelper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DbContract.DB_NAME, null, DbContract.DB_VERSION);
    }

    /**
     * Called during creation of database.
     * @param db to create table in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.UserTable.CREATE_TABLE);
    }

    /**
     * Called during upgrade of database.
     * @param db being upgraded
     * @param oldVersion previous database version
     * @param newVersion current database versions (needs increment when updating schema)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.UserTable.DROP_TABLE);
        onCreate(db);
    }
}
