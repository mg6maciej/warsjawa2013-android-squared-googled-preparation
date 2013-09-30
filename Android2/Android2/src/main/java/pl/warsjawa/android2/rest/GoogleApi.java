package pl.warsjawa.android2.rest;

import com.google.android.gms.maps.model.LatLng;

import pl.warsjawa.android2.model.gmapsapi.RouteList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GoogleApi {

    @GET("/directions/json")
    void getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("sensor") boolean sensor, Callback<RouteList> callback);
}
