package com.vcu.groupr.relicrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CatalogEdit extends AppCompatActivity {

    private Button mSubmitButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactsDatabaseReference;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_data_entry);
        final Artifact artifact = (Artifact) getIntent().getSerializableExtra("artifact");
        getTextView(R.id.artifactName).setText(artifact.getName());
        getTextView(R.id.artifactType).setText(artifact.getType());
        getTextView(R.id.description).setText(artifact.getDescription());
        getTextView(R.id.artifactAge).setText(Integer.toString(artifact.getAge()));
        getTextView(R.id.locationFound).setText(artifact.getLocation());
        getTextView(R.id.date).setText(artifact.getDate());
        getTextView(R.id.price).setText(Double.toString(artifact.getPrice()));
        getTextView(R.id.url).setText(artifact.getUrl());

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mArtifactsDatabaseReference = mFirebaseDatabase.getReference().child("artifacts");
        mKey = getIntent().getStringExtra("key");

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = readField(R.id.artifactName);
                String type = readField(R.id.artifactType);
                String description = readField(R.id.description);
                int age = Integer.parseInt(readField(R.id.artifactAge));
                String location = readField(R.id.locationFound);
                String date = readField(R.id.date);
                double price = Double.parseDouble(readField(R.id.price));
                String url = readField(R.id.url);
                Artifact artifact = new Artifact(name,type,description,age,location,date,price,url);
                mArtifactsDatabaseReference.child(mKey).removeValue();
                mArtifactsDatabaseReference.push().setValue(artifact);
                onBackPressed();
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
