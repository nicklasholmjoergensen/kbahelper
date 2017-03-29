package io.peqo.kbahelper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "kba_helper";
    private static final String TABLE_NAME = "user";
    private static final String COL_ID = "id";
    private static final String COL_FIRSTNAME = "first_name";
    private static final String COL_LASTNAME = "last_name";
    private static final String COL_EMAIL = "email";
    private static final String COL_UUID = "uuid";

    public SQLiteHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
