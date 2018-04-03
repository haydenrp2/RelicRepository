package com.vcu.groupr.relicrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CatalogAdd extends AppCompatActivity{

    Button mSubmitButton;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactsDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_add);

        mSubmitButton = (Button) findViewById(R.id.submitButton);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mArtifactsDatabaseReference = mFirebaseDatabase.getReference().child("artifacts");

        // Send button sends a message and clears the EditText
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = readField(R.id.artifactName);
                String type = readField(R.id.artifactType);
                String description = readField(R.id.description);
                int age = Integer.parseInt(readField(R.id.artifactAge));
                String location = readField(R.id.locationFound);
                String date = readField(R.id.date);
                double price = Double.parseDouble(readField(R.id.price));
                String url = readField(R.id.url);
                Artifact artifact = new Artifact(name,type,description,age,location,date,price,url);
                mArtifactsDatabaseReference.push().setValue(artifact);
                onBackPressed();
            }
        });
    }

    private String readField(int id){
        return ((EditText)findViewById(id)).getText().toString();
    }
}
