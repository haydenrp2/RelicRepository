package com.vcu.groupr.relicrepository;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    private String username;
    private String email;
    private int phoneNumber;




    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactsDatabaseReference;
    private ChildEventListener mChildEventListener;

    public UserProfile (){


    }
}
