package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends AppCompatActivity{

    private ListView mArtifactListView;
    private ArtifactAdapter mArtifactAdapter;
    private Button mAddButton;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArtifactsDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseListAdapter<Artifact> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);

        mArtifactListView = (ListView) findViewById(R.id.listView);
        mAddButton = (Button) findViewById(R.id.addButton);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        List<Artifact> artifacts = new ArrayList<>();
        final List<String> mKeys = new ArrayList<String>();
        mArtifactsDatabaseReference = mFirebaseDatabase.getReference().child("artifacts");
        mArtifactAdapter = new ArtifactAdapter(this,R.layout.item_catalog,artifacts);
        mArtifactListView.setAdapter(mArtifactAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Catalog.this,CatalogAdd.class);
                startActivity(intent);
            }
        });
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Artifact artifact = dataSnapshot.getValue(Artifact.class);
                if(s == null){
                    mArtifactAdapter.insert(artifact, 0);
                    mKeys.add(0, key);
                } else {
                    int previousIndex = mKeys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if(nextIndex == mArtifactAdapter.getCount()){
                        mArtifactAdapter.add(artifact);
                        mKeys.add(key);
                    } else {
                        mArtifactAdapter.insert(artifact,nextIndex);
                        mKeys.add(nextIndex,key);
                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Artifact artifact = dataSnapshot.getValue(Artifact.class);
                int index = mKeys.indexOf(key);
                Artifact remove = mArtifactAdapter.getItem(index);
                mArtifactAdapter.remove(remove);
                mArtifactAdapter.insert(artifact,index);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = mKeys.indexOf(key);
                Artifact artifact = dataSnapshot.getValue(Artifact.class);
                mArtifactAdapter.remove(artifact);
                mKeys.remove(index);
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            public void onCancelled(DatabaseError databaseError) {}
        };
        mArtifactsDatabaseReference.addChildEventListener(mChildEventListener);

        mArtifactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artifact artifact = (Artifact) parent.getItemAtPosition(position);
                Intent intent = new Intent(Catalog.this, CatalogItem.class);
                intent.putExtra("artifact",artifact);
                intent.putExtra("key",mKeys.get(position));
                startActivity(intent);
            }
        });
    }
}
