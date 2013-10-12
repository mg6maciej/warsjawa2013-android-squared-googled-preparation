package pl.warsjawa.android2.rest;

import pl.warsjawa.android2.model.meetup.MeetupEventList;
import pl.warsjawa.android2.model.meetup.GroupList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MeetupApi {

    @GET("/2/events")
    void getEvents(@Query("member_id") String memberId, Callback<MeetupEventList> eventListCallback);

    @GET("/2/groups")
    void getGroups(@Query("member_id") String memberId, Callback<GroupList> groupListCallback);
}
