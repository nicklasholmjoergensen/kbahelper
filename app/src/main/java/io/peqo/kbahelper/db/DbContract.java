package io.peqo.kbahelper.db;

import android.provider.BaseColumns;

/**
 * Static class for maintaining DB Schema.
 */

public final class DbContract {

    public static final String DB_NAME = "kbahelper.db";
    public static final int DB_VERSION = 1;

    private DbContract() {}

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_FIRSTNAME + " TEXT," +
                COLUMN_LASTNAME + " TEXT)";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
