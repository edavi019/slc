package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PickActivity extends AppCompatActivity implements View.OnClickListener {

    Button workerbtn;
    Button requesterbtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        workerbtn = findViewById(R.id.WORKERBTN);
        requesterbtn = findViewById(R.id.REQUESTERBTN);

        workerbtn.setOnClickListener(this);
        requesterbtn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user) {
//        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();



    }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.WORKERBTN:
                    startActivity(new Intent(this, WorkerZipActivity.class));
                    break;

                case R.id.REQUESTERBTN:
                    startActivity(new Intent(this, ListOfWorkersActivity.class));
                    break;


            }
        }
    }