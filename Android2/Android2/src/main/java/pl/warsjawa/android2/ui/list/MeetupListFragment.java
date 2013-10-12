package pl.warsjawa.android2.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import pl.warsjawa.android2.R;
import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.GmapsModel;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.MeetupEvent;
import pl.warsjawa.android2.model.meetup.MeetupEventList;
import pl.warsjawa.android2.model.meetup.MeetupModel;
import pl.warsjawa.android2.ui.BaseFragment;

/**
 * Created by krzysztofsiejkowski on 10/1/13.
 */
public class MeetupListFragment extends BaseFragment {

    @Inject
    GmapsModel gmapsModel;
    @Inject
    MeetupModel meetupModel;
    @Inject
    Context context;
    @Inject
    EventBus bus;

    private ListView meetupsListView;

    private MeetupListAdapter meetupListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meetups_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meetupsListView = (ListView) view.findViewById(R.id.meetups_listview);
        meetupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (meetupListAdapter.getItem(position) instanceof MeetupEvent) {
                    meetupListAdapter.toogleExpand(position);
                    MeetupEvent event = (MeetupEvent) meetupListAdapter.getItem(position);
                    LatLng pos = event.getVenue().getLatLng();
                    meetupListAdapter.onNearbyPlacesListUpdate(new Pair<MeetupEvent, NearbyPlacesList>(event, null));
                    gmapsModel.requestNearbyPlacesList(event);
                }
            }
        });
        meetupListAdapter = new MeetupListAdapter(context);
        // TODO: this was incorrect and probably should not be here
        MeetupEventList eventList = meetupModel.getEventList();
        if (eventList != null) {
            meetupListAdapter.onEventListUpdate(eventList);
        }
        meetupsListView.setAdapter(meetupListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        meetupListAdapter.registerToEventBus(bus);
    }

    @Override
    public void onPause() {
        super.onPause();
        meetupListAdapter.unregisterFromEventBus(bus);
    }

}
