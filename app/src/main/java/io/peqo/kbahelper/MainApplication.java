package io.peqo.kbahelper;

import android.app.Application;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import io.peqo.kbahelper.model.DaoMaster;
import io.peqo.kbahelper.model.DaoSession;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "kbahelper.db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        Log.d(TAG, "Database opened for transaction.");
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
