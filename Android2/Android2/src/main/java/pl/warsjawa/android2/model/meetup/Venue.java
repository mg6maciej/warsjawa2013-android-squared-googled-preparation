package pl.warsjawa.android2.model.meetup;

import com.google.android.gms.maps.model.LatLng;

public class Venue {

    private String address1;
    private String city;
    private String country;
    private double lat;
    private double lon;
    private String name;

    public LatLng getLatLng() {
        return new LatLng(lat, lon);
    }
}
