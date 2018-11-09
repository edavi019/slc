package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextZipCode, editTextPhoneNumber;

    private FirebaseAuth mAuth;


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


    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String FirstName = editTextFirstName.getText().toString().trim();
        String LastName = editTextLastName.getText().toString().trim();
        String ZipCode = editTextZipCode.getText().toString().trim();
        String PhoneNumber = editTextPhoneNumber.getText().toString().trim();

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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Registered UnSuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                registerUser();
                break;

            case R.id.textView:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

