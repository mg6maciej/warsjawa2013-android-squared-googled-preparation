package pl.warsjawa.android2.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
* Created by krzysztofsiejkowski on 10/6/13.
*/
public interface ItemAdapter<T> {

    T getItem();

    boolean isEnabled();

    long getItemId();

    int getItemViewType();

    View getView(LayoutInflater inflater, View convertView, ViewGroup parent);

}
