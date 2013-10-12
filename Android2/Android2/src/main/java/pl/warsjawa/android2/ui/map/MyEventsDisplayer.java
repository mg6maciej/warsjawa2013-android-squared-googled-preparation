package pl.warsjawa.android2.ui.map;

import android.util.Log;
import android.util.Pair;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.GmapsModel;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlace;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.Event;
import pl.warsjawa.android2.model.meetup.EventList;
import pl.warsjawa.android2.model.meetup.MeetupModel;

public class MyEventsDisplayer {

    private GoogleMap map;
    private Map<Marker, Event> markerEventMap;
    @Inject
    MeetupModel model;
    @Inject
    EventBus bus;
    @Inject
    GmapsModel gmapsModel;

    public void setUpMap(final GoogleMap map) {
        this.map = map;
        this.markerEventMap = new HashMap<Marker, Event>();
        this.map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Event event = markerEventMap.get(marker);
                if (event == null) {
                    return;
                }
                Log.i("tag", "marker = " + marker.getPosition());
                NearbyPlacesList nearbyPlacesList = gmapsModel.getNearbyPlacesList(event);
                if (nearbyPlacesList == null) {
                    gmapsModel.requestNearbyPlacesList(event);
                }
                else {
                    displayNearbyPlaces(event);
                }
            }
        });
        displayMyEvents();
    }

    public void registerForMyEventsUpdate() {
        bus.register(this);
        displayMyEvents();
    }

    public void unregisterFromMyEventsUpdate() {
        bus.unregister(this);
    }

    @Subscribe
    public void onMyEventsUpdate(EventList myEventList) {
        displayMyEvents();
    }

    @Subscribe
    public void onNearbyPlacesUpdate(Pair<Event,NearbyPlacesList> places) {
        displayNearbyPlaces(places.first);
    }

    private void displayMyEvents() {
        if (map != null) {
            clearEvents();
            EventList myEvents = model.getEventList();
            if (myEvents != null) {
                for (Event event : myEvents.getResults()) {
                    Marker marker = map.addMarker(new MarkerOptions().title(event.getName()).snippet(event.getGroup().getName()).position(event.getVenue().getLatLng()));
                    markerEventMap.put(marker, event);
                }
            }
        }
    }

    private void clearEvents() {
        for (Marker marker : markerEventMap.keySet()) {
            marker.remove();
        }
        markerEventMap.clear();
    }

    private void displayNearbyPlaces(Event event) {
        BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        NearbyPlacesList nearbyPlacesList = gmapsModel.getNearbyPlacesList(event);
        if (nearbyPlacesList != null) {
            for (NearbyPlace place : nearbyPlacesList.getResults()) {
                map.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()).icon(icon));
            }
        }
    }
}
