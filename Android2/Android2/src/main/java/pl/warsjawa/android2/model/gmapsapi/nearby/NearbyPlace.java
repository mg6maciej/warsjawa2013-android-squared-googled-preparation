package pl.warsjawa.android2.model.gmapsapi.nearby;

import com.google.android.gms.maps.model.LatLng;

public class NearbyPlace {

    private Geometry geometry;
    private String id;
    private String name;
    private Double rating;

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return geometry.getLocation().toLatLng();
    }
}
