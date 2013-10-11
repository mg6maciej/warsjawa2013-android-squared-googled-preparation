package pl.warsjawa.android2.ui.list;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlacesList;
import pl.warsjawa.android2.model.meetup.Event;
import pl.warsjawa.android2.model.meetup.EventList;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupListAdapter extends BaseAdapter {

    private static final int TYPE_COUNT = 3;

    private LayoutInflater inflater;

    private List<MeetupMergeAdapter> data;
    private List<Pair<Integer, Integer>> count;

    public MeetupListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data = new ArrayList<MeetupMergeAdapter>();
        count = new ArrayList<Pair<Integer, Integer>>();
    }

    public void toogleExpand(int position) {
        Pair<Integer,Integer> pair = count.get(position);
        data.get(pair.first).toogleExpand();
    }

    private void dataUpdated() {
        count.clear();
        for (int i = 0; i < data.size(); i++) {
            int elems = data.get(i).getCount();
            for (int j = 0; j < elems; j++)
                count.add(new Pair<Integer, Integer>(i,j));
        }
        notifyDataSetChanged();
    }

    public void registerToEventBus(EventBus bus) {
        bus.register(this);
    }

    public void unregisterFromEventBus(EventBus bus) {
        bus.unregister(this);
    }

    @Subscribe
    public void onEventListUpdate(EventList eventList) {
        data.clear();
        for (Event event : eventList.getResults()) {
            data.add(new MeetupMergeAdapter(inflater,event));
        }
        dataUpdated();
    }

    @Subscribe
    public void onNearbyPlacesListUpdate(Pair<LatLng, NearbyPlacesList> places) {
        for (MeetupMergeAdapter meetupMergeAdapter : data) {
            if (((Event) meetupMergeAdapter.getItem(0)).getVenue().compareLatLng(places.first))
                meetupMergeAdapter.updateData(places.second);
        }
        dataUpdated();
    }

    @Override
    public int getCount() {
        return count.size();
    }

    @Override
    public Object getItem(int position) {
        Pair<Integer,Integer> pair = count.get(position);
        return data.get(pair.first).getItem(pair.second);
    }

    @Override
    public long getItemId(int position) {
        Pair<Integer,Integer> pair = count.get(position);
        return data.get(pair.first).getItemId(pair.second);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        Pair<Integer,Integer> pair = count.get(position);
        return data.get(pair.first).isEnabled(pair.second);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Pair<Integer,Integer> pair = count.get(position);
        return data.get(pair.first).getItemViewType(pair.second);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pair<Integer,Integer> pair = count.get(position);
        return data.get(pair.first).getView(pair.second, convertView, parent);
    }

}
