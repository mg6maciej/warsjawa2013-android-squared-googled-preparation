package pl.warsjawa.android2.ui.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import pl.warsjawa.android2.rest.ParseClient;

public class MapPositionAdder implements GoogleMap.OnMapLongClickListener {

    private GoogleMap map;
    @Inject
    ParseClient parseClient;

    public void setUpMap(GoogleMap map) {
        this.map = map;
        map.setOnMapLongClickListener(this);
        parseClient.retrieveLocations(new ParseClient.Callback<List<LatLng>>() {
            @Override
            public void got(List<LatLng> object) {
                for (LatLng location : object) {
                    createMarker(location);
                }
            }
        });
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
//        parseClient.addLocation(latLng);
        createMarker(latLng);
    }

    private void createMarker(LatLng latLng) {
        BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        map.addMarker(new MarkerOptions().position(latLng).icon(icon));
    }
}
