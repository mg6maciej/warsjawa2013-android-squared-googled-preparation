package pl.warsjawa.android2.model.meetup;

public class MeetupEvent {

    private Group group;
    private String id;
    private String name;
    private String status;
    private long time;
    private Venue venue;

    public Group getGroup() {
        return group;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Venue getVenue() {
        return venue;
    }

}
