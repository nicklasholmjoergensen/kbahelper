package io.peqo.kbahelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.peqo.kbahelper.db.DbContract;
import io.peqo.kbahelper.db.DbHelper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper mDbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.UserTable.COLUMN_FIRSTNAME, "Nicklas");
        values.put(DbContract.UserTable.COLUMN_LASTNAME, "Holm Jørgensen");
        long newRowId = db.insert(DbContract.UserTable.TABLE_NAME, null, values);
    }
}
