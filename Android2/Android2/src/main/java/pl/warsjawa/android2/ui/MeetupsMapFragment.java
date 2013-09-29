package pl.warsjawa.android2.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import pl.warsjawa.android2.PreferenceManager;
import pl.warsjawa.android2.R;
import pl.warsjawa.android2.model.Event;
import pl.warsjawa.android2.model.EventList;
import pl.warsjawa.android2.model.TheModel;
import pl.warsjawa.android2.rest.MeetupClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MeetupsMapFragment extends BaseFragment {

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Inject
    TheModel model;
    @Inject
    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meetups_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createMapFragmentIfNeeded();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCurrentPosition();
    }

    private void createMapFragmentIfNeeded() {
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.meetups_map_container);
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            FragmentTransaction tx = fm.beginTransaction();
            tx.add(R.id.meetups_map_container, mapFragment);
            tx.commit();
        }
    }

    private void setUpMapIfNeeded() {
        if (!isMapReady()) {
            map = mapFragment.getMap();
            if (isMapReady()) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        restorePreviousPosition();
        displayMyEvents();
    }

    @Subscribe
    public void onMyEventsUpdate(EventList myEventList) {
        displayMyEvents();
    }

    private void displayMyEvents() {
        if (isMapReady()) {
            EventList myEvents = model.getEventList();
            if (myEvents != null) {
                for (Event event : myEvents.getResults()) {
                    map.addMarker(new MarkerOptions().title(event.getName()).snippet(event.getGroup().getName()).position(event.getVenue().getLatLng()));
                }
            }
        }
    }

    private void restorePreviousPosition() {
        CameraPosition previousPosition = preferenceManager.getMapPosition();
        if (previousPosition != null) {
            map.moveCamera(CameraUpdateFactory.newCameraPosition(previousPosition));
        }
    }

    private void saveCurrentPosition() {
        if (isMapReady()) {
            CameraPosition currentPosition = map.getCameraPosition();
            preferenceManager.saveMapPosition(currentPosition);
        }
    }

    private boolean isMapReady() {
        return map != null;
    }
}
