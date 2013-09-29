package pl.warsjawa.android2.rest;

import pl.warsjawa.android2.model.EventList;
import pl.warsjawa.android2.model.GroupList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MeetupApi {

    @GET("/2/events")
    void getEvents(@Query("member_id") String memberId, Callback<EventList> eventListCallback);

    @GET("/2/groups")
    void getGroups(@Query("member_id") String memberId, Callback<GroupList> groupListCallback);
}
