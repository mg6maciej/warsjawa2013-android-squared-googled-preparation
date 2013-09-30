package pl.warsjawa.android2.ui.map;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import pl.warsjawa.android2.R;
import pl.warsjawa.android2.ui.BaseFragment;

public class MeetupsMapFragment extends BaseFragment {

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Inject MapPositionRestorer mapPositionRestorer;
    @Inject MyEventsDisplayer eventsDisplayer;

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
        eventsDisplayer.registerForMyEventsUpdate();
        setUpMapIfNeeded();
    }

    @Override
    public void onPause() {
        super.onPause();
        eventsDisplayer.unregisterFromMyEventsUpdate();
        mapPositionRestorer.saveCurrentPosition();
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
        if (map == null) {
            map = mapFragment.getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mapPositionRestorer.restorePreviousPosition(map);
        eventsDisplayer.setUpMap(map);
    }
}
