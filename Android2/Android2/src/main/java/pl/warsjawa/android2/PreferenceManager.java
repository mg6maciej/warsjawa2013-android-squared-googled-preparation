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
    private static final String KEY_MAP_ZOOM = "map_zoom";
    private static final String KEY_MAP_LATITUDE = "map_lat";
    private static final String KEY_MAP_LONGITUDE = "map_lng";
    private static final String KEY_MAP_BEARING = "map_bearing";
    private static final String KEY_MAP_TILT = "map_tilt";

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
        float zoom = prefs.getFloat(KEY_MAP_ZOOM, -1);
        if (zoom >= 0) {
            double lat = prefs.getFloat(KEY_MAP_LATITUDE, 0);
            double lng = prefs.getFloat(KEY_MAP_LONGITUDE, 0);
            float bearing = prefs.getFloat(KEY_MAP_BEARING, 0);
            float tilt = prefs.getFloat(KEY_MAP_TILT, 0);
            position = CameraPosition.builder()
                    .target(new LatLng(lat, lng))
                    .zoom(zoom)
                    .bearing(bearing)
                    .tilt(tilt)
                    .build();
        }
        return position;
    }

    public void saveMapPosition(CameraPosition position) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(KEY_MAP_LATITUDE, (float) position.target.latitude);
        editor.putFloat(KEY_MAP_LONGITUDE, (float) position.target.longitude);
        editor.putFloat(KEY_MAP_ZOOM, position.zoom);
        editor.putFloat(KEY_MAP_BEARING, position.bearing);
        editor.putFloat(KEY_MAP_TILT, position.tilt);
        editor.apply();
    }
}
