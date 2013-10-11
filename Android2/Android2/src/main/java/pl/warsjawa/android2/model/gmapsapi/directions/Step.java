package pl.warsjawa.android2.model.gmapsapi.directions;

import pl.warsjawa.android2.model.gmapsapi.Coords;

public class Step {

    private ValueWithDescription distance;
    private ValueWithDescription duration;
    private Coords endLocation;
    private EncodedPolyline polyline;
    private Coords startLocation;
    private String travelMode;

    public EncodedPolyline getPolyline() {
        return polyline;
    }
}
