package pl.warsjawa.android2.model;

public class Event {

    private Group group;
    private String name;
    private String status;
    private long time;
    private Venue venue;

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public Venue getVenue() {
        return venue;
    }
}
