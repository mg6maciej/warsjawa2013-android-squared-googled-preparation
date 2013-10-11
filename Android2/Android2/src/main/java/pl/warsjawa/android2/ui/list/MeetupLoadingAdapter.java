package pl.warsjawa.android2.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import pl.warsjawa.android2.model.meetup.Event;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupLoadingAdapter implements ItemAdapter<String> {

    private static final int TYPE = 2;
    private static final String string = "Loading...";

    @Override
    public String getItem() {
        return string;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public long getItemId() {
        return string.hashCode();
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
