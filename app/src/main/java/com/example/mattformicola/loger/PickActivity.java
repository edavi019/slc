package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.WORKER:
                finish();
                startActivity(new Intent(this, MapsActivity.class));
                break;

        }
    }
}