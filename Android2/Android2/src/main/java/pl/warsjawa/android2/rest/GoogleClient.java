package pl.warsjawa.android2.rest;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.okhttp.OkHttpClient;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.warsjawa.android2.model.gmapsapi.RouteList;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;

@Singleton
public class GoogleClient {

    private GoogleApi api;

    @Inject
    public GoogleClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("https://maps.googleapis.com/maps/api")
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(RequestInterceptor.NONE)
                .setErrorHandler(ErrorHandler.DEFAULT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(GoogleApi.class);
    }

    public void getDirections(LatLng origin, LatLng destination, boolean sensor, Callback<RouteList> callback) {
        api.getDirections(latLngToString(origin), latLngToString(destination), sensor, callback);
    }

    public void requestNearbyPlaces(LatLng location, int radius, Callback<NearbyPlacesList> callback) {
        api.requestNearbyPlaces(latLngToString(location), radius, "restaurant|pub|night_club", false, "AIzaSyBE1ihpDpD7H4FgH1ju_foQFrYecBcck5I", callback);
    }

    private String latLngToString(LatLng latLng) {
        return String.format(Locale.US, "%s,%s", latLng.latitude, latLng.longitude);
    }
}
