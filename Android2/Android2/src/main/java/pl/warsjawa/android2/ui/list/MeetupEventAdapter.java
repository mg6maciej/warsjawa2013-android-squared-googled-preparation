package pl.warsjawa.android2.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import pl.warsjawa.android2.model.meetup.Event;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupEventAdapter implements ItemAdapter<Event> {

    public static final int TYPE = 1;

    private MeetupEventItem eventItem;
    private Event event;

    MeetupEventAdapter(Event event) {
        this.event = event;
    }

    @Override
    public Event getItem() {
        return event;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public long getItemId() {
        return event.hashCode();
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return convertView;
    }

}
