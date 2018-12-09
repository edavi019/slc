package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextUsername, editTextPhoneNumber, editTextZip;
    Button btnRegister;
    RadioGroup radioJobGroup;
    RadioButton radioButtonWorker;
    RadioButton radioButtonRequester;
    private FirebaseAuth mAuth;
    String TAG = "elliot";
    //DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextZip = findViewById(R.id.editTextZip);

        mAuth = FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        radioJobGroup = findViewById(R.id.radioGroupJob);
        btnRegister = findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser   = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        Log.d("value here:", email);
        final String password = editTextPassword.getText().toString().trim();
        final String FirstName = editTextFirstName.getText().toString().trim();
        final String LastName = editTextLastName.getText().toString().trim();
        final String UserName = editTextUsername.getText().toString().trim();
        final String PhoneNumber = editTextPhoneNumber.getText().toString().trim();
        final String Zip = editTextZip.getText().toString().trim();
        final int Radio = 1;

        Log.d("REACHED", "ME2");

//        if (email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Please enter a valid email");
//            editTextEmail.requestFocus();
//        }
//
//        if (password.isEmpty()) {
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//        }
//
//        if (password.length() < 6) {
//            editTextPassword.setError("Minimum length of six characters is required");
//            editTextPassword.requestFocus();
//        }
//
//        if (FirstName.isEmpty()) {
//            editTextFirstName.setError("FirstName is required");
//            editTextFirstName.requestFocus();
//        }
//
//        if (lastName.isEmpty()) {
//            editTextLastName.setError("lastName is required");
//            editTextLastName.requestFocus();
//        }
//
//        if (phoneNumber.isEmpty()) {
//            editTextPhoneNumber.setError("Phone Number is required");
//            editTextPhoneNumber.requestFocus();
//        }
//
//          if(!Patterns.PHONE.matcher(phoneNumber).matches()){
//              editTextPhoneNumber.setError("Please enter a valid phone number");
//              editTextPhoneNumber.requestFocus();
//          }
        Log.d("REACHED", "ME3");
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("REACHED", "ME");
                                if (task.isSuccessful()) {
//                                    User User = new User(
//                                            email, FirstName, lastName, userName, phoneNumber
//                                    );
//                                    Log.d(TAG, "createUserWithEmail:success" + FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                    FirebaseDatabase.getInstance().getReference("Users")
//                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            Log.d("REACHED", "ME4");
//                                            if (task.isSuccessful()) {
//                                                //displays success
//                                                Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_LONG).show();
//
//                                            } else {
//                                                //displays failure to register
//                                                Toast.makeText(getApplicationContext(), "User Registered UnSuccessfully", Toast.LENGTH_LONG).show();
//
//                                            }
//                                        }
//                                    });
                                } else {
                                    Log.d("REACHED", "ME6");
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });

        Log.d("REACHED" , "ME5");
        User User = new User(
                email, FirstName, LastName, UserName, PhoneNumber, Zip, Radio
        );

//        Log.d(TAG, "createUserWithEmail:success" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("REACHED", "ME4");
                if (task.isSuccessful()) {
                    //displays success
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_LONG).show();

                } else {
                    //displays failure to register
                    Toast.makeText(getApplicationContext(), "User Registered UnSuccessfully", Toast.LENGTH_LONG).show();

                }
            }
        });
        /*if(radioButtonWorker.isChecked()){
            startActivity(new Intent(this, DisplayListOfRequestersAcitivty.class));

        }else{
            return;
        }
        */
        startActivity(new Intent(this, DisplayListOfRequestersAcitivty.class));

    }

    public void updateUI(FirebaseUser user) {
//        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                int selectId = radioJobGroup.getCheckedRadioButtonId();
                radioButtonRequester = findViewById(selectId);
                radioButtonWorker = findViewById(selectId);
//                if (radioButtonWorker.isChecked()){
//                    startActivity(new Intent(this, DisplayListOfRequestersAcitivty.class));
//                }else{
//                    startActivity(new Intent(this, DisplayListOfWorkersActivity.class));
//                }
                registerUser();
               // Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_LONG).show();
                break;

        }
    }
}

