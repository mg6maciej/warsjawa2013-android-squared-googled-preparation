package pl.warsjawa.android2.ui.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pl.warsjawa.android2.model.meetup.Event;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupEventAdapter implements ItemAdapter<Event> {

    public static final int TYPE = 0;

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
        Holder holder;
        if (!(convertView instanceof MeetupEventItem)) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new Holder();
            holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
            holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView1.setText(event.getName());
        holder.textView2.setText(event.getGroup().getName());
        return convertView;
    }


    private static class Holder {
        private TextView textView1;
        private TextView textView2;
    }

}
