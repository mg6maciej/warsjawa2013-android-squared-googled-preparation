package pl.warsjawa.android2.rest;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Singleton
public class MeetupClient {

    private MeetupApi meetupApi;

    @Inject
    public MeetupClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("https://api.meetup.com")
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(RequestInterceptor.NONE)
                .setErrorHandler(ErrorHandler.DEFAULT)
                .build();

        meetupApi = restAdapter.create(MeetupApi.class);
    }

    public MeetupApi getApi() {
        return meetupApi;
    }
}
