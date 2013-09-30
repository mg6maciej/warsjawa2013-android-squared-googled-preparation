package pl.warsjawa.android2.model.gmapsapi;

import com.google.android.gms.maps.model.LatLng;

public class Coords {

    private double lat;
    private double lng;

    public LatLng toLatLng() {
        return new LatLng(lat, lng);
    }
}
