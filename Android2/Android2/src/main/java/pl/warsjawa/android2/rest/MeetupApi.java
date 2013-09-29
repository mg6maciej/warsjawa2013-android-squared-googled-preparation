package pl.warsjawa.android2.rest;

import pl.warsjawa.android2.model.GroupList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MeetupApi {

    @GET("/2/groups")
    void getGroups(@Query("member_id") String memberId, @Query("access_token") String token, Callback<GroupList> groupListCallback);
}
