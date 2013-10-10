package pl.warsjawa.android2.rest;

import com.google.android.gms.maps.model.LatLng;

import pl.warsjawa.android2.model.gmapsapi.RouteList;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GoogleApi {

    @GET("/directions/json")
    void getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("sensor") boolean sensor, Callback<RouteList> callback);

    @GET("/place/nearbysearch/json")
    void requestNearbyPlaces(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("types") String types,
            @Query("sensor") boolean sensor,
            @Query("key") String key,
            Callback<NearbyPlacesList> callback);
}
