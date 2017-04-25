package io.peqo.kbahelper.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsManager {

    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tag_token";

    private static SharedPrefsManager instance;
    private static Context c;

    private SharedPrefsManager(Context c) {
        this.c = c;
    }

    public static synchronized SharedPrefsManager getInstance(Context c) {
        if(instance == null) {
            instance = new SharedPrefsManager(c);
        }
        return instance;
    }

    // Save token to device
    public boolean saveToken(String token) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getToken() {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return prefs.getString(TAG_TOKEN, null);
    }
}
