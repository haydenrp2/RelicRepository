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

import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity {

    CalendarView mCalendarView;
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

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mEventListView = (ListView) findViewById(R.id.calendarList);
        mAddEvent = (Button) findViewById(R.id.addEvent);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        List<Event> events = new ArrayList<>();
        final List<String> mKeys = new ArrayList<String>();
        mEventsDatabaseReference = mFirebaseDatabase.getReference().child("events");
        mEventAdapter = new EventAdapter(this,R.layout.item_catalog,events);
        mEventListView.setAdapter(mEventAdapter);

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
                    mEventAdapter.insert(event, 0);
                    mKeys.add(0, key);
                } else {
                    int previousIndex = mKeys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if(nextIndex == mEventAdapter.getCount()){
                        mEventAdapter.add(event);
                        mKeys.add(key);
                    } else {
                        mEventAdapter.insert(event,nextIndex);
                        mKeys.add(nextIndex,key);
                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Event event = dataSnapshot.getValue(Event.class);
                int index = mKeys.indexOf(key);
                Event remove = mEventAdapter.getItem(index);
                mEventAdapter.remove(remove);
                mEventAdapter.insert(event,index);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = mKeys.indexOf(key);
                Event event = dataSnapshot.getValue(Event.class);
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
                System.out.println(event == null);
                intent.putExtra("event",event);
                intent.putExtra("key",mKeys.get(position));
                startActivity(intent);
            }
        });

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
            }
        });
    }
}
