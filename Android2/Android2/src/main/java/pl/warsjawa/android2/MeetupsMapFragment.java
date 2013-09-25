package pl.warsjawa.android2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MeetupsMapFragment extends Fragment {

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meetups_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createMapFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void createMapFragment() {
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
        
    }
}
