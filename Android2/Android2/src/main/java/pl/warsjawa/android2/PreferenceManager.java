package pl.warsjawa.android2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences prefs;

    public PreferenceManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }
}
