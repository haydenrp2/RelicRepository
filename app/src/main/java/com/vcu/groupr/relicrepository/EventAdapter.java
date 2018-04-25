package com.vcu.groupr.relicrepository;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_catalog, parent, false);
        }

        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        Event event = getItem(position);

        authorTextView.setText(event.getOrganizer());

        return convertView;
    }
}
