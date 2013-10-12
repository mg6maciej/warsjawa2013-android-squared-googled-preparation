package pl.warsjawa.android2.rest;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.PreferenceManager;
import pl.warsjawa.android2.model.meetup.MeetupEventList;
import pl.warsjawa.android2.model.meetup.GroupList;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Singleton
public class MeetupClient {

    private MeetupApi meetupApi;

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    public MeetupClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("https://api.meetup.com")
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(createRequestInterceptor())
                .setErrorHandler(ErrorHandler.DEFAULT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        meetupApi = restAdapter.create(MeetupApi.class);
    }

    public void getMyUpcomingEvents(Callback<MeetupEventList> callback) {
        meetupApi.getEvents("self", callback);
    }

    public void getMyGroups(Callback<GroupList> callback) {
        meetupApi.getGroups("self", callback);
    }

    private RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addQueryParam("access_token", preferenceManager.getToken());
            }
        };
    }
}
