package pl.warsjawa.android2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import pl.warsjawa.android2.model.Group;
import pl.warsjawa.android2.model.GroupList;
import pl.warsjawa.android2.rest.MeetupClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MeetupsMapFragment extends BaseFragment {

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Inject
    MeetupClient meetupClient;

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

        Callback<GroupList> groupListCallback = new Callback<GroupList>() {
            @Override
            public void success(GroupList groupList, Response response) {
                for (Group group : groupList.getResults()) {
                    map.addMarker(new MarkerOptions().title(group.getName()).position(group.getLatLng()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        };

        meetupClient.getApi().getGroups("83622662", new PreferenceManager(getActivity()).getToken(), groupListCallback);
    }
}
