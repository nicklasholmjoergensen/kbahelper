package io.peqo.kbahelper.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.peqo.kbahelper.db.DbContentProvider;
import io.peqo.kbahelper.db.DbContract;
import io.peqo.kbahelper.model.User;

public class UserDao extends DbContentProvider implements Dao<User, Integer> {

    private Cursor cursor;
    private ContentValues values;

    public UserDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public User findOne(Integer id) {
        final String selectionArgs[] = { String.valueOf(id) };
        final String selection = DbContract.UserTable.COLUMN_ID + " = ?";

        User user = null;

        cursor = super.query(DbContract.UserTable.TABLE_NAME,
                DbContract.UserTable.COLUMNS,
                selection,
                selectionArgs,
                DbContract.UserTable.COLUMN_ID);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = cursorToEntity(cursor);
            cursor.close();
        }

        return user;
    }

    @Override
    public boolean save(User user) {
        values = new ContentValues();
        //values.put(DbContract.UserTable.COLUMN_ID, user.getId());
        values.put(DbContract.UserTable.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(DbContract.UserTable.COLUMN_LASTNAME, user.getLastName());

        try {
            super.insert(DbContract.UserTable.TABLE_NAME, values);
            return true;
        } catch(SQLiteConstraintException ex) {
            Log.w("Database Error: ", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        cursor = super.query(DbContract.UserTable.TABLE_NAME,
                DbContract.UserTable.COLUMNS,
                null,
                null,
                DbContract.UserTable.COLUMN_ID);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                User user = cursorToEntity(cursor);
                list.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    protected User cursorToEntity(Cursor cursor) {
        User user = new User();

        if(cursor != null) {
            if(cursor.getColumnIndex(DbContract.UserTable.COLUMN_ID) != -1) {
                user.setId(cursor.getInt(cursor.getColumnIndex(DbContract.UserTable.COLUMN_ID)));
            }
            if(cursor.getColumnIndex(DbContract.UserTable.COLUMN_FIRSTNAME) != -1) {
                user.setFirstName(cursor.getString(cursor.getColumnIndex(DbContract.UserTable.COLUMN_FIRSTNAME)));
            }
            if(cursor.getColumnIndex(DbContract.UserTable.COLUMN_LASTNAME) != -1) {
                user.setLastName(cursor.getString(cursor.getColumnIndex(DbContract.UserTable.COLUMN_LASTNAME)));
            }
        }

        return user;
    }
}
