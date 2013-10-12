package pl.warsjawa.android2.ui.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.event.ShowNearbyPlacesEvent;
import pl.warsjawa.android2.model.gmapsapi.GmapsModel;
import pl.warsjawa.android2.model.meetup.MeetupEvent;
import pl.warsjawa.android2.model.meetup.MeetupEventList;
import pl.warsjawa.android2.model.meetup.MeetupModel;

public class MyEventsDisplayer {

    private GoogleMap map;
    private Map<Marker, MeetupEvent> markerEventMap;
    @Inject
    MeetupModel model;
    @Inject
    EventBus bus;
    @Inject
    GmapsModel gmapsModel;

    public void setUpMap(final GoogleMap map) {
        this.map = map;
        this.markerEventMap = new HashMap<Marker, MeetupEvent>();
        this.map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MeetupEvent event = markerEventMap.get(marker);
                if (event != null) {
                    bus.post(new ShowNearbyPlacesEvent(event));
                    marker.showInfoWindow();
                    return true;
                } else {
                    return false;
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
    public void onMyEventsUpdate(MeetupEventList myEventList) {
        displayMyEvents();
    }

    private void displayMyEvents() {
        if (map != null) {
            clearEvents();
            MeetupEventList myEvents = model.getEventList();
            if (myEvents != null) {
                for (MeetupEvent event : myEvents.getResults()) {
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
