package pl.warsjawa.android2.model.gmapsapi;

import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.rest.GoogleClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Singleton
public class GmapsModel {

    private final EventBus bus;
    private final GoogleClient googleClient;
    private HashMap<LatLng, NearbyPlacesList> nearbyPlacesMap;

    @Inject
    public GmapsModel(EventBus bus, GoogleClient googleClient) {
        this.bus = bus;
        this.googleClient = googleClient;
        this.nearbyPlacesMap = new HashMap<LatLng, NearbyPlacesList>();
    }

    public void requestNearbyPlacesList(final LatLng position) {
        NearbyPlacesList nearbyPlacesList = nearbyPlacesMap.get(position);
        if (nearbyPlacesList == null) {
            Callback<NearbyPlacesList> groupListCallback = new Callback<NearbyPlacesList>() {
                @Override
                public void success(NearbyPlacesList nearbyPlaces, Response response) {
                    nearbyPlacesMap.put(position, nearbyPlaces);
                    bus.post(new Pair<LatLng,NearbyPlacesList>(position, nearbyPlaces));
                }
                @Override
                public void failure(RetrofitError error) {
                }
            };
            googleClient.requestNearbyPlaces(position, 500, groupListCallback);
        }
        else {
            bus.post(new Pair<LatLng,NearbyPlacesList>(position, nearbyPlacesList));
        }
    }

    public NearbyPlacesList getNearbyPlacesList(LatLng position) {
        return nearbyPlacesMap.get(position);
    }
}
