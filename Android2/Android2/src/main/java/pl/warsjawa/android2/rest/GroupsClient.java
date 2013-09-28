package pl.warsjawa.android2.rest;

import pl.warsjawa.android2.model.GroupList;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.RestMethod;

public interface GroupsClient {

    @GET("/2/groups")
    GroupList getGroups(@Query("member_id") String memberId, @Query("access_token") String token);
}
