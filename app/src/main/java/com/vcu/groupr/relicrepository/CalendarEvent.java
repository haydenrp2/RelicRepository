package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalendarEvent extends AppCompatActivity{

    private Button mDeleteButton;
    private Button mEditButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEventsDatabaseReference;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event);
        final Event event = (Event)getIntent().getSerializableExtra("event");
        getTextView(R.id.organizerTextView).setText(event.getOrganizer());
        getTextView(R.id.dateTextView).setText(event.getDate());
        getTextView(R.id.timeTextView).setText(event.getTime());
        getTextView(R.id.locationTextView).setText(event.getLocation());
        getTextView(R.id.notesTextView).setText(event.getNotes());

        mDeleteButton = (Button) findViewById(R.id.deleteButton);
        mEditButton = (Button) findViewById(R.id.editButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mEventsDatabaseReference = mFirebaseDatabase.getReference().child("events");
        mKey = getIntent().getStringExtra("key");

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventsDatabaseReference.child(mKey).removeValue();
                onBackPressed();
            }
        });
        mEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarEvent.this, CalenderEdit.class);
                intent.putExtra("event",event);
                intent.putExtra("key",mKey);
                startActivity(intent);
            }
        });
    }
    private TextView getTextView(int id){
        return (TextView)findViewById(id);
    }
}
