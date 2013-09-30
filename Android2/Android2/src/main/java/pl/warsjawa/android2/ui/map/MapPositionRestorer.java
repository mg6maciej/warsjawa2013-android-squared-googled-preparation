package pl.warsjawa.android2.ui.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

import javax.inject.Inject;

import pl.warsjawa.android2.PreferenceManager;

public class MapPositionRestorer {

    private GoogleMap map;
    @Inject
    PreferenceManager preferenceManager;

    public void setMap(GoogleMap map) {
        this.map = map;
    }

    public void restorePreviousPosition() {
        if (map != null) {
            CameraPosition previousPosition = preferenceManager.getMapPosition();
            if (previousPosition != null) {
                map.moveCamera(CameraUpdateFactory.newCameraPosition(previousPosition));
            }
        }
    }

    public void saveCurrentPosition() {
        if (map != null) {
            CameraPosition currentPosition = map.getCameraPosition();
            preferenceManager.saveMapPosition(currentPosition);
        }
    }
}
