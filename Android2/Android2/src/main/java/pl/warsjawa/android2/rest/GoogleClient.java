package pl.warsjawa.android2.rest;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.okhttp.OkHttpClient;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.model.gmapsapi.RouteList;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Singleton
public class GoogleClient {

    private GoogleApi api;

    @Inject
    public GoogleClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("http://maps.googleapis.com/maps/api")
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(RequestInterceptor.NONE)
                .setErrorHandler(ErrorHandler.DEFAULT)
                .build();

        api = restAdapter.create(GoogleApi.class);
    }

    public void getDirections(LatLng origin, LatLng destination, boolean sensor, Callback<RouteList> callback) {
        api.getDirections(latLngToString(origin), latLngToString(destination), sensor, callback);
    }

    private String latLngToString(LatLng latLng) {
        return String.format(Locale.US, "%s,%s", latLng.latitude, latLng.longitude);
    }
}
