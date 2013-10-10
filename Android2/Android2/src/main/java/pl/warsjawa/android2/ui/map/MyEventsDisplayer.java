package pl.warsjawa.android2.ui.map;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.inject.Inject;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlace;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.Event;
import pl.warsjawa.android2.model.meetup.EventList;
import pl.warsjawa.android2.model.meetup.TheModel;
import pl.warsjawa.android2.rest.GoogleClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyEventsDisplayer {

    private GoogleMap map;
    @Inject
    TheModel model;
    @Inject
    EventBus bus;
    @Inject
    GoogleClient googleClient;

    public void setUpMap(final GoogleMap map) {
        this.map = map;
        this.map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.i("tag", "marker = " + marker.getPosition());
                // TODO: temp, move to services
                googleClient.requestNearbyPlaces(marker.getPosition(), 500, new Callback<NearbyPlacesList>() {
                    @Override
                    public void success(NearbyPlacesList nearbyPlacesList, Response response) {
                        for (NearbyPlace place : nearbyPlacesList.getResults()) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                            map.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()).icon(icon));
                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {

                    }
                });
            }
        });
        displayMyEvents();
    }

    public void registerForMyEventsUpdate() {
        bus.register(this);
    }

    public void unregisterFromMyEventsUpdate() {
        bus.unregister(this);
    }

    @Subscribe
    public void onMyEventsUpdate(EventList myEventList) {
        displayMyEvents();
    }

    private void displayMyEvents() {
        if (map != null) {
            EventList myEvents = model.getEventList();
            if (myEvents != null) {
                for (Event event : myEvents.getResults()) {
                    map.addMarker(new MarkerOptions().title(event.getName()).snippet(event.getGroup().getName()).position(event.getVenue().getLatLng()));
                }
            }
        }
    }
}
