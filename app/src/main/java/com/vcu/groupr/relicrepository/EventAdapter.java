package com.vcu.groupr.relicrepository;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Date;
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
        //View calendarView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.calendar, parent, false);'
        /*CalendarView mCalendarView = (CalendarView) parent.findViewById(R.id.calendarView);
        Date date = new Date(mCalendarView.getDate());
        String dateString = date.getMonth()+1+"/"+date.getDay()+"/"+date.getYear();*/
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        Event event = getItem(position);
        //if(event.getDate().equals(dateString))
            authorTextView.setText(event.getOrganizer());
        /*else
            convertView = null;*/
        return convertView;
    }
}
