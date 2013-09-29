package pl.warsjawa.android2.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class MeetupClient {

    private MeetupApi meetupApi;

    private static class Loader {
        static MeetupClient instance = new MeetupClient();
    }

    public static MeetupApi getApi() {
        return Loader.instance.meetupApi;
    }

    private MeetupClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("https://api.meetup.com")
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(RequestInterceptor.NONE)
                .setErrorHandler(ErrorHandler.DEFAULT)
                .build();

        meetupApi = restAdapter.create(MeetupApi.class);

    }

}
