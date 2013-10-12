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
import pl.warsjawa.android2.event.ShowNearbyPlacesEvent;
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
                bus.post(new ShowNearbyPlacesEvent(event));
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
}
