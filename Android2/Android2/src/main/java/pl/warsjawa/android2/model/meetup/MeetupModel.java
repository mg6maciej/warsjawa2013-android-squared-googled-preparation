package pl.warsjawa.android2.model.meetup;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.rest.MeetupClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Singleton
public class MeetupModel {

    private final EventBus bus;
    private final MeetupClient meetupClient;
    private MeetupEventList myEventList;

    @Inject
    public MeetupModel(EventBus bus, MeetupClient meetupClient) {
        this.bus = bus;
        this.meetupClient = meetupClient;
    }

    public MeetupEventList getEventList() {
        if (myEventList == null) {
            Callback<MeetupEventList> groupListCallback = new Callback<MeetupEventList>() {
                @Override
                public void success(MeetupEventList eventList, Response response) {
                    myEventList = eventList;
                    bus.post(eventList);
                }
                @Override
                public void failure(RetrofitError error) {
                }
            };
            meetupClient.getMyUpcomingEvents(groupListCallback);
        }
        return myEventList;
    }

}
