package io.peqo.kbahelper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.peqo.kbahelper.model.User;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "kba_helper";
    private static final String TABLE_NAME = "user";
    private static final String COL_ID = "id";
    private static final String COL_FIRSTNAME = "first_name";
    private static final String COL_LASTNAME = "last_name";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_TEAM_ID = "team_id";

    public SQLiteHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY, "
                + COL_FIRSTNAME + " VARCHAR(50), "
                + COL_LASTNAME + " VARCHAR(50), "
                + COL_USERNAME + " VARCHAR(50), "
                + COL_EMAIL + " VARCHAR(50), "
                + COL_TEAM_ID + " INTEGER"
                + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    /**
     * Store user details in database
     */

    public void addUser(User user) {
        final SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(COL_ID, user.id);
        v.put(COL_FIRSTNAME, user.firstName);
        v.put(COL_LASTNAME, user.lastName);
        v.put(COL_USERNAME, user.username);
        v.put(COL_EMAIL, user.email);
        v.put(COL_TEAM_ID, user.teamId);

        db.insert(TABLE_NAME, null, v);
        db.close();
    }

    /**
     * Fetch user details from database
     */

    public User getUser() {
        final SQLiteDatabase db = this.getReadableDatabase();
        final String q = "SELECT * FROM " + TABLE_NAME;
        final Cursor cursor = db.rawQuery(q, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            User user = new User.Builder()
                    .setId(cursor.getLong(0))
                    .setFirstName(cursor.getString(1))
                    .setLastName(cursor.getString(2))
                    .setUsername(cursor.getString(3))
                    .setEmail(cursor.getString(4))
                    .build();
            cursor.close();
            db.close();
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }

    /**
     * Delete user details in database
     */

    public void deleteUser() {
        final SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
