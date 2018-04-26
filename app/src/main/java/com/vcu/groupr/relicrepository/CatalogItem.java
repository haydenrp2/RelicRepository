package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CatalogItem extends AppCompatActivity{

    private Button mDeleteButton;
    private Button mEditButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactsDatabaseReference;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event);
        final Artifact artifact = (Artifact)getIntent().getSerializableExtra("artifact");
        getTextView(R.id.textView1).setText(artifact.getName());
        getTextView(R.id.textView2).setText(artifact.getType());
        getTextView(R.id.textView3).setText(artifact.getDescription());
        getTextView(R.id.textView4).setText(Integer.toString(artifact.getAge()));
        getTextView(R.id.textView5).setText(artifact.getLocation());
        getTextView(R.id.textView6).setText(artifact.getDate());
        getTextView(R.id.textView7).setText(Double.toString(artifact.getPrice()));
        getTextView(R.id.textView8).setText(artifact.getUrl());

        mDeleteButton = (Button) findViewById(R.id.deleteButton);
        mEditButton = (Button) findViewById(R.id.editButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mArtifactsDatabaseReference = mFirebaseDatabase.getReference().child("artifacts");
        mKey = getIntent().getStringExtra("key");

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArtifactsDatabaseReference.child(mKey).removeValue();
                onBackPressed();
            }
        });
        mEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogItem.this, CatalogEdit.class);
                intent.putExtra("artifact",artifact);
                intent.putExtra("key",mKey);
                startActivity(intent);
            }
        });
    }
    private TextView getTextView(int id){
        return (TextView)findViewById(id);
    }
}
