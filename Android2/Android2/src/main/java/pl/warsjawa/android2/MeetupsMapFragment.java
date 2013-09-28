package pl.warsjawa.android2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import pl.warsjawa.android2.model.Group;
import pl.warsjawa.android2.model.GroupList;
import pl.warsjawa.android2.rest.GroupsClient;
import retrofit.RestAdapter;

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
        new AsyncTask<Void, Void, GroupList>() {
            @Override
            protected GroupList doInBackground(Void... voids) {
                RestAdapter adapter = new RestAdapter.Builder().setServer("https://api.meetup.com").build();
                GroupsClient client = adapter.create(GroupsClient.class);
                GroupList list = client.getGroups("83622662", new PreferenceManager(getActivity()).getToken());
                return list;
            }

            @Override
            protected void onPostExecute(GroupList list) {
                for (Group group : list.getResults()) {
                    map.addMarker(new MarkerOptions().title(group.getName()).position(group.getLatLng()));
                }
            }
        }.execute();
    }
}
