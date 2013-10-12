package pl.warsjawa.android2.event;

import pl.warsjawa.android2.model.meetup.Event;

public class ShowNearbyPlacesEvent {

    private final Event event;

    public ShowNearbyPlacesEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
