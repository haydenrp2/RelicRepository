package com.vcu.groupr.relicrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Catalog extends AppCompatActivity{

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);
    }
}
