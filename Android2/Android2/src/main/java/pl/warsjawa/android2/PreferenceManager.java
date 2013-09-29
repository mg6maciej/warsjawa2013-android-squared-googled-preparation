package pl.warsjawa.android2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceManager {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences prefs;

    @Inject
    public PreferenceManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public CameraPosition getMapPosition() {
        CameraPosition position = null;
        float zoom = prefs.getFloat("zoom", -1);
        if (zoom >= 0) {
            double lat = prefs.getFloat("lat", 0);
            double lng = prefs.getFloat("lng", 0);
            float bearing = prefs.getFloat("bearing", 0);
            float tilt = prefs.getFloat("tilt", 0);
            position = CameraPosition.builder().target(new LatLng(lat, lng)).zoom(zoom).bearing(bearing).tilt(tilt).build();
        }
        return position;
    }

    public void saveMapPosition(CameraPosition position) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("lat", (float) position.target.latitude);
        editor.putFloat("lng", (float) position.target.longitude);
        editor.putFloat("zoom", position.zoom);
        editor.putFloat("bearing", position.bearing);
        editor.putFloat("tilt", position.tilt);
        editor.apply();
    }
}
