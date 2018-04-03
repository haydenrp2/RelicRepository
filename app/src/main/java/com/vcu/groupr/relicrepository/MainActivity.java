package com.vcu.groupr.relicrepository;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mCatalogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCatalogButton = (Button) findViewById(R.id.catalogButton);

        mCatalogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("test");
                Intent intent = new Intent(MainActivity.this,Catalog.class);
                startActivity(intent);
            }
        });
    }
}
