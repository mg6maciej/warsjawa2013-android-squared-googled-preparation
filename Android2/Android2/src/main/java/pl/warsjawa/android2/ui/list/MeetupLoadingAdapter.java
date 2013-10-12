package pl.warsjawa.android2.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
class MeetupLoadingAdapter implements ItemAdapter<String> {

    private static final int TYPE = 1;
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
        Holder holder;
        if (!(convertView instanceof MeetupEventItem)) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new Holder();
            holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView1.setText(string);
        return convertView;
    }


    private static class Holder {
        private TextView textView1;
    }
}
