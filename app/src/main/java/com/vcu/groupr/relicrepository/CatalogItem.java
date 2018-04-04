package com.vcu.groupr.relicrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CatalogItem extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_item);
        Artifact artifact = (Artifact)getIntent().getSerializableExtra("artifact");
        getTextView(R.id.textView1).setText(artifact.getName());
        getTextView(R.id.textView2).setText(artifact.getType());
        getTextView(R.id.textView3).setText(artifact.getDescription());
        getTextView(R.id.textView4).setText(Integer.toString(artifact.getAge()));
        getTextView(R.id.textView5).setText(artifact.getLocation());
        getTextView(R.id.textView6).setText(artifact.getDate());
        getTextView(R.id.textView7).setText(Double.toString(artifact.getPrice()));
        getTextView(R.id.textView8).setText(artifact.getUrl());
    }
    private TextView getTextView(int id){
        return (TextView)findViewById(id);
    }
}
