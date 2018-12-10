package com.example.mattformicola.loger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegNewActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextUsername, editTextPhoneNumber, editTextZip;
    Button btnRegister;
    int radioButtonWorker;
    int radioButtonRequester;
    RadioGroup radioGroup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextZip = findViewById(R.id.editTextZip);
        btnRegister = findViewById(R.id.register_btn);
        radioGroup = findViewById(R.id.radioGroupJob);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                final String firstName = editTextFirstName.getText().toString();
                final String lastName = editTextLastName.getText().toString();
                final String userName = editTextUsername.getText().toString();
                final String phone = editTextPhoneNumber.getText().toString();
                final String zip = editTextZip.getText().toString();

                final int radio = radioGroup.getCheckedRadioButtonId();

                String userActivity = "";
                boolean checked = ((RadioButton) v).isChecked();
                switch (v.getId()){
                    case R.id.radioButtonWorker:
                        if (checked)
                            userActivity = "worker";
                        break;
                    case R.id.radioButtonRequester:
                        if (checked)
                            userActivity = "requester";
                        break;
                }


                radioButtonRequester = R.id.radioButtonRequester;
                radioButtonWorker = R.id.radioButtonWorker;


                Log.d("Email is", email);
                Log.d("password is", password);
//                Log.d("radio is", radioButtonRequester.getText().toString());
//                Log.d("radio is ", radioButtonWorker.getText().toString());


                final String finalUserActivity = userActivity;
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegNewActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RegNewActivity.this, "WORKED",
                                            Toast.LENGTH_SHORT).show();

                                    // Write a message to the database
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                                    User person = new User(email, firstName, lastName, userName, phone, zip, radio);

                                    DatabaseReference myRef = database.getReference("Users");
                                    Log.d("Current user is", "" + user.getUid());
                                    myRef.child(user.getUid()).setValue(person);

                                    if (radioButtonRequester == radio) {
                                        Log.d("Radio button Requester", "Checked");
                                       // myRef.child("User").child("radio").setValue(finalUserActivity);
                                        startActivity(new Intent(RegNewActivity.this, ListOfWorkersActivity.class));
                                    }
                                    else{
                                        Log.d("Radio button Worker", "Checked");
                                        //myRef.child("User").child("radio").setValue(finalUserActivity);
                                        startActivity(new Intent(RegNewActivity.this, ListOfRequestersActivity.class));
                                    }


                                } else {
                                    Toast.makeText(RegNewActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


                //startActivity(new Intent(this,DisplayListOfWorkersActivity.class));


            }
        });


    }

    public void radioButtonRequesterClicked(View v) {
        String userActivity = "";
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
            case R.id.radioButtonWorker:
                if (checked)
                    userActivity = "worker";
                break;
            case R.id.radioButtonRequester:
                if (checked)
                    userActivity = "requester";
                break;
        }
    }

    void updateUI(String s) {

    }

}
