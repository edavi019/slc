package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextZipCode, editTextPhoneNumber;
    Button btnRegister;
    private FirebaseAuth mAuth;
    String TAG = "ELLIOT";
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");


        btnRegister = findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void registerUser() {
         final String email = editTextEmail.getText().toString().trim();
         final String password = editTextPassword.getText().toString().trim();
         final String FirstName = editTextFirstName.getText().toString().trim();
         final String LastName = editTextLastName.getText().toString().trim();
         final String ZipCode = editTextZipCode.getText().toString().trim();
             final String PhoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of six characters is required");
            editTextPassword.requestFocus();
            return;
        }

        if (FirstName.isEmpty()) {
            editTextFirstName.setError("FirstName is required");
            editTextFirstName.requestFocus();
            return;
        }
        if (LastName.isEmpty()) {
            editTextLastName.setError("LastName is required");
            editTextLastName.requestFocus();
            return;
        }
        if (ZipCode.isEmpty()) {
            editTextZipCode.setError("Zip Code is required");
            editTextZipCode.requestFocus();
            return;
        }
        if (PhoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Phone Number is required");
            editTextPhoneNumber.requestFocus();
            return;
        }
      //  if(!Patterns.PHONE.matcher(PhoneNumber).matches()){
      //      editTextPhoneNumber.setError("Please enter a valid phone number");
      //      editTextPhoneNumber.requestFocus();
      //      return;
      //  }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                Users users = new Users(
                                       email, FirstName, LastName, PhoneNumber, ZipCode

                                );

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                     .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        //displays success
                                        Toast.makeText(getApplicationContext(),"User Registered Successful", Toast.LENGTH_LONG).show();
                                    }else{
                                        //displays failure to register
                                        Toast.makeText(getApplicationContext(),"User Registered UnSuccessfully", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void updateUI(FirebaseUser user){
        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                registerUser();
                break;

            case R.id.textView:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

