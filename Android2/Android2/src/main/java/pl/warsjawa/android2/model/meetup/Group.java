package pl.warsjawa.android2.model.meetup;

import com.google.android.gms.maps.model.LatLng;

public class Group {

    private String name;
    private double lat;
    private double lon;

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return new LatLng(lat, lon);
    }

}
