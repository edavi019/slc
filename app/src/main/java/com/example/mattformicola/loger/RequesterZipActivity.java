package com.example.mattformicola.loger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RequesterZipActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextRequesterZip;
    Button btnRequesterZip;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    String TAG = "Got to this Point";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_zip);

        editTextRequesterZip = findViewById(R.id.editTextWorkerZip);

        btnRequesterZip = findViewById(R.id.confirmZip);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("RequesterZip");

        btnRequesterZip.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void SaveRequesterZip() {
        Log.d(TAG, "got here");
        String RequesterZip = editTextRequesterZip.getText().toString().trim();


        if (RequesterZip.isEmpty()) {
            editTextRequesterZip.setError("Zip Code Is Required");
            editTextRequesterZip.requestFocus();
            return;
        }

        RequesterZip requesterZip = new RequesterZip(RequesterZip);
        mDatabase.child("email").setValue(requesterZip);
       // mDatabase.child()
        //mDatabase.child("RequesterZip").setValue(requesterZip);


    }


    public void updateUI(FirebaseUser user) {
//        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmZip:
                SaveRequesterZip();
                Toast.makeText(this, "ZipCode has been saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ListOfWorkersActivity.class));
                break;
        }

    }
}
