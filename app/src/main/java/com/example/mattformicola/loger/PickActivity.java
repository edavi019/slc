package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        Button workerbtn = findViewById(R.id.WORKERBTN);
        Button requesterbtn = findViewById(R.id.REQUESTERBTN);

        workerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WorkerZipCode.class);
                startActivity(i);
            }
        });
        requesterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });


        // workerbtn.setOnClickListener(new View.OnClickListener() {
        //     Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        //     startActivity;
       /* });


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.WORKER:
                finish();
                startActivity(new Intent(this, MapsActivity.class));
                break;

        }
    }

    */
    }
}