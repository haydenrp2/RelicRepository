package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView mCalendarView;
    private ListView mEventListView;
    private EventAdapter mEventAdapter;
    private Button mAddEvent;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEventsDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseListAdapter<Event> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mEventListView = (ListView) findViewById(R.id.calendarList);
        mAddEvent = (Button) findViewById(R.id.addEvent);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        final ArrayList<Event> allEvents = new ArrayList<>();
        final List<Event> events = new ArrayList<>();
        final List<String> mKeys = new ArrayList<String>();
        mEventsDatabaseReference = mFirebaseDatabase.getReference().child("events");
        mEventAdapter = new EventAdapter(this,R.layout.item_catalog,events);
        mEventListView.setAdapter(mEventAdapter);
        mCalendarView.setSelectedDate(new Date());

        mAddEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this,CalendarAdd.class);
                startActivity(intent);
            }
        });
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Event event = dataSnapshot.getValue(Event.class);
                if(s == null){
                    allEvents.add(0,event);
                    mKeys.add(0, key);
                } else {
                    int previousIndex = mKeys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if(nextIndex == allEvents.size()){
                        allEvents.add(event);
                        mKeys.add(key);
                    } else {
                        allEvents.add(nextIndex,event);
                        mKeys.add(nextIndex,key);
                    }
                }
                if(parseDate(mCalendarView.getSelectedDate()).equals(event.getDate()))
                    mEventAdapter.add(event);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Event event = dataSnapshot.getValue(Event.class);
                int index = mKeys.indexOf(key);
                Event remove = allEvents.get(index);
                if(mEventAdapter.getPosition(remove) != -1)
                    mEventAdapter.add(event);
                allEvents.remove(remove);
                allEvents.add(index,event);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = mKeys.indexOf(key);
                Event event = dataSnapshot.getValue(Event.class);
                allEvents.remove(event);
                mEventAdapter.remove(event);
                mKeys.remove(index);
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            public void onCancelled(DatabaseError databaseError) {}
        };
        mEventsDatabaseReference.addChildEventListener(mChildEventListener);

        mEventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) parent.getItemAtPosition(position);
                Intent intent = new Intent(Calendar.this, CalendarEvent.class);
                intent.putExtra("event",event);
                intent.putExtra("key",mKeys.get(position));
                startActivity(intent);
            }
        });
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mEventAdapter.clear();
                String dateString = parseDate(date);
                for(int i = 0; i < allEvents.size(); i++){
                    if(allEvents.get(i).getDate().equals(dateString))
                        mEventAdapter.add(allEvents.get(i));
                }
            }
        });
    }

    public String parseDate(CalendarDay calendarDay){
        return (calendarDay.getMonth() + 1) + "/" + calendarDay.getDay() + "/" + calendarDay.getYear();
    }
}
