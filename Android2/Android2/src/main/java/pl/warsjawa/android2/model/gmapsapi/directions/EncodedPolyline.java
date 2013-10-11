package pl.warsjawa.android2.model.gmapsapi.directions;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.List;

public class EncodedPolyline {

    private String points;

    public String getPoints() {
        return points;
    }

    public List<LatLng> toList() {
        return PolyUtil.decode(points);
    }
}
