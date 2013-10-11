package pl.warsjawa.android2.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pl.warsjawa.android2.model.gmapsapi.nearby.NearbyPlace;
import pl.warsjawa.android2.model.meetup.Event;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupNearbyPlaceAdapter implements ItemAdapter<NearbyPlace> {

    private NearbyPlace nearbyPlace;
    private static final int TYPE = 2;

    MeetupNearbyPlaceAdapter(NearbyPlace nearbyPlace) {
        this.nearbyPlace = nearbyPlace;
    }

    @Override
    public NearbyPlace getItem() {
        return nearbyPlace;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public long getItemId() {
        return nearbyPlace.hashCode();
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        Holder holder;
        if (!(convertView instanceof MeetupEventItem)) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new Holder();
            holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView1.setText(nearbyPlace.getName());
        return convertView;
    }


    private static class Holder {
        private TextView textView1;
    }
}
