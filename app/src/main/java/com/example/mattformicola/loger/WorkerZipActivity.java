package com.example.mattformicola.loger;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerZipActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextWorkerZip;
    Button btnWorkerZip;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    String TAG ="Got to this Point";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_zip);

        editTextWorkerZip = findViewById(R.id.editTextWorkerZip);

        btnWorkerZip = findViewById(R.id.confirmZip);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("WorkerZip");

        btnWorkerZip.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void SaveWorkerZip(){
        Log.d(TAG,"got here");
        String WorkerZip = editTextWorkerZip.getText().toString().trim();


        if(WorkerZip.isEmpty()){
            editTextWorkerZip.setError("Zip Code Is Required");
            editTextWorkerZip.requestFocus();
            return;
        }

       // WorkerZip workerZip = new WorkerZip(WorkerZip);
       // mDatabase.push().setValue(workerZip);


    }



    public void updateUI(FirebaseUser user) {
//        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();



    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmZip:
                SaveWorkerZip();
                Toast.makeText(this,"ZipCode has been saved",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
