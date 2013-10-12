package pl.warsjawa.android2.ui.map;

import android.util.Pair;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.event.ShowNearbyPlacesEvent;
import pl.warsjawa.android2.model.gmapsapi.GmapsModel;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlace;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.MeetupEvent;

public class NearbyPlacesDisplayer {

    private GoogleMap map;
    private MeetupEvent currentEvent;
    private Map<Marker, NearbyPlace> markerNearbyPlaceMap;
    @Inject
    EventBus bus;
    @Inject
    GmapsModel gmapsModel;

    public void setUpMap(GoogleMap map) {
        this.map = map;
        this.markerNearbyPlaceMap = new HashMap<Marker, NearbyPlace>();
    }

    public void registerForEvents() {
        bus.register(this);
    }

    public void unregisterFromEvents() {
        bus.unregister(this);
    }

    @Subscribe
    public void onEvent(ShowNearbyPlacesEvent showNearbyPlacesEvent) {
        MeetupEvent event = showNearbyPlacesEvent.getEvent();
        NearbyPlacesList nearbyPlacesList = gmapsModel.getNearbyPlacesList(event);
        if (nearbyPlacesList == null) {
            currentEvent = null;
            clearNearbyPlaces();
            gmapsModel.requestNearbyPlacesList(event);
        }
        else {
            displayNearbyPlaces(event);
        }
    }

    @Subscribe
    public void onNearbyPlacesUpdate(Pair<MeetupEvent,NearbyPlacesList> places) {
        displayNearbyPlaces(places.first);
    }

    private void displayNearbyPlaces(MeetupEvent event) {
        if (event.equals(currentEvent)) {
            return;
        }
        currentEvent = event;
        clearNearbyPlaces();
        NearbyPlacesList nearbyPlacesList = gmapsModel.getNearbyPlacesList(event);
        if (nearbyPlacesList != null) {
            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
            LatLngBounds.Builder builder = LatLngBounds.builder();
            for (NearbyPlace place : nearbyPlacesList.getResults()) {
                LatLng location = place.getLocation();
                Marker marker = map.addMarker(new MarkerOptions().position(location).title(place.getName()).icon(icon));
                markerNearbyPlaceMap.put(marker, place);
                builder.include(location);
            }
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 30));
        }
    }

    private void clearNearbyPlaces() {
        for (Marker marker : markerNearbyPlaceMap.keySet()) {
            marker.remove();
        }
        markerNearbyPlaceMap.clear();
    }
}
