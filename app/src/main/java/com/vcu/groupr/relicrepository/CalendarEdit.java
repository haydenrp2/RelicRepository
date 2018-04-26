package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalendarEdit extends AppCompatActivity {

    private Button mSubmitButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEventsDatabaseReference;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_data_entry);
        final Event event = (Event) getIntent().getSerializableExtra("event");
        getTextView(R.id.organizer).setText(event.getOrganizer());
        getTextView(R.id.date).setText(event.getDate());
        getTextView(R.id.time).setText(event.getTime());
        getTextView(R.id.location).setText(event.getLocation());
        getTextView(R.id.notes).setText(event.getNotes());

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mEventsDatabaseReference = mFirebaseDatabase.getReference().child("events");
        mKey = getIntent().getStringExtra("key");

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organizer = readField(R.id.organizer);
                String date = readField(R.id.date);
                String time = readField(R.id.time);
                String location = readField(R.id.location);
                String notes = readField(R.id.notes);
                Event event = new Event(organizer,date,time,location,notes);
                mEventsDatabaseReference.child(mKey).removeValue();
                mEventsDatabaseReference.push().setValue(event);
                Intent intent = new Intent(CalendarEdit.this, CalendarEvent.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("event",event);
                intent.putExtra("key",mKey);
                startActivity(intent);
                CalendarEdit.this.finish();
                return;
            }
        });
    }
    private TextView getTextView(int id){
        return (TextView)findViewById(id);
    }
    private String readField(int id){
        return ((EditText)findViewById(id)).getText().toString();
    }
}