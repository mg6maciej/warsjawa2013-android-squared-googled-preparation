package pl.warsjawa.android2.model.gmapsapi;

import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.Event;
import pl.warsjawa.android2.rest.GoogleClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Singleton
public class GmapsModel {

    private final EventBus bus;
    private final GoogleClient googleClient;
    private HashMap<Event, NearbyPlacesList> nearbyPlacesMap;

    @Inject
    public GmapsModel(EventBus bus, GoogleClient googleClient) {
        this.bus = bus;
        this.googleClient = googleClient;
        this.nearbyPlacesMap = new HashMap<Event, NearbyPlacesList>();
    }

    public void requestNearbyPlacesList(final Event event) {
        NearbyPlacesList nearbyPlacesList = nearbyPlacesMap.get(event);
        if (nearbyPlacesList == null) {
            Callback<NearbyPlacesList> groupListCallback = new Callback<NearbyPlacesList>() {
                @Override
                public void success(NearbyPlacesList nearbyPlaces, Response response) {
                    nearbyPlacesMap.put(event, nearbyPlaces);
                    bus.post(new Pair<Event,NearbyPlacesList>(event, nearbyPlaces));
                }
                @Override
                public void failure(RetrofitError error) {
                }
            };
            googleClient.requestNearbyPlaces(event.getVenue().getLatLng(), 500, groupListCallback);
        }
        else {
            bus.post(new Pair<Event, NearbyPlacesList>(event, nearbyPlacesList));
        }
    }

    public NearbyPlacesList getNearbyPlacesList(Event event) {
        return nearbyPlacesMap.get(event);
    }
}
