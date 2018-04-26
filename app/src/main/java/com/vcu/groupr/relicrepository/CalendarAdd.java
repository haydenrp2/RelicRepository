package com.vcu.groupr.relicrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalendarAdd extends AppCompatActivity {

    Button mSubmitButton;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEventsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_data_entry);

        mSubmitButton = (Button) findViewById(R.id.submitButton);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mEventsDatabaseReference = mFirebaseDatabase.getReference().child("events");

        // Send button sends a message and clears the EditText
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String organizer = readField(R.id.organizer);
                String date = readField(R.id.date);
                String time = readField(R.id.time);
                String location = readField(R.id.location);
                String notes = readField(R.id.notes);
                Event event = new Event(organizer,date,time,location,notes);
                mEventsDatabaseReference.push().setValue(event);
                onBackPressed();
            }
        });
    }

    private String readField(int id){
        return ((EditText)findViewById(id)).getText().toString();
    }
}