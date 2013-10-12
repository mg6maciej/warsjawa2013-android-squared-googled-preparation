package pl.warsjawa.android2.event;

import pl.warsjawa.android2.model.meetup.MeetupEvent;

public class ShowNearbyPlacesEvent {

    private final MeetupEvent event;

    public ShowNearbyPlacesEvent(MeetupEvent event) {
        this.event = event;
    }

    public MeetupEvent getEvent() {
        return event;
    }
}
