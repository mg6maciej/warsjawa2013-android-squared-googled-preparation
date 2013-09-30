package pl.warsjawa.android2.ui.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.meetup.Event;
import pl.warsjawa.android2.model.meetup.EventList;
import pl.warsjawa.android2.model.meetup.TheModel;

public class MyEventsDisplayer {

    private GoogleMap map;
    @Inject
    TheModel model;
    @Inject
    EventBus bus;

    public void setUpMap(GoogleMap map) {
        this.map = map;
        displayMyEvents();
    }

    public void registerForMyEventsUpdate() {
        bus.register(this);
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
            EventList myEvents = model.getEventList();
            if (myEvents != null) {
                for (Event event : myEvents.getResults()) {
                    map.addMarker(new MarkerOptions().title(event.getName()).snippet(event.getGroup().getName()).position(event.getVenue().getLatLng()));
                }
            }
        }
    }
}
