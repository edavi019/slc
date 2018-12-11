package com.example.mattformicola.loger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
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

import java.util.prefs.Preferences;

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
                final String password = editTextPassword.getText().toString();
                final String firstName = editTextFirstName.getText().toString();
                final String lastName = editTextLastName.getText().toString();
                final String userName = editTextUsername.getText().toString();
                final String phone = editTextPhoneNumber.getText().toString();
                final String zip = editTextZip.getText().toString();

                int radio = radioGroup.getCheckedRadioButtonId();

                String userActivity = "";

                switch (radio){
                    case R.id.radioButtonWorker:
                            userActivity = "worker";
                        break;
                    case R.id.radioButtonRequester:
                            userActivity = "requester";
                        break;
                }

                Log.d("current selection", userActivity);

                radioButtonRequester = R.id.radioButtonRequester;
                radioButtonWorker = R.id.radioButtonWorker;

                final int radioFinal = (userActivity.equals("worker") ? 1 : 2);

                Log.d("Email is", email);
                Log.d("password is", password);


                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Minimum length of six characters is required");
                    editTextPassword.requestFocus();
                }
                if(firstName.isEmpty()){
                    editTextFirstName.setError("Please enter your First Name!");
                    editTextFirstName.requestFocus();
                }
                if(lastName.isEmpty()){
                    editTextLastName.setError("Please enter your Last Name!");
                    editTextLastName.requestFocus();
                }
                if(userName.isEmpty()){
                    editTextUsername.setError("Please enter a User Name!");
                    editTextUsername.requestFocus();
                }
                if(phone.isEmpty()){
                    editTextPhoneNumber.setError("Please enter a Phone Number!");
                    editTextPhoneNumber.requestFocus();
                }
                if(!Patterns.PHONE.matcher(phone).matches()){
                    editTextPhoneNumber.setError("Please enter valid Phone Number!");
                    editTextPhoneNumber.requestFocus();
                }
                if(zip.isEmpty()){
                    editTextZip.setError("Please enter a Zip Code");
                    editTextZip.requestFocus();
                }

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

                                    User person = new User(email, firstName, lastName, userName, phone, zip, radioFinal);

                                    DatabaseReference myRef = database.getReference("Users");
                                    Log.d("Current user is", "" + user.getUid());
                                    myRef.child(user.getUid()).setValue(person);

                                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(RegNewActivity.this);
                                    SharedPreferences.Editor edit = pref.edit();
                                    edit.putInt("workerOrNot", radioFinal);
                                    edit.apply();

                                    if (radioFinal == 1) {
                                        Log.d("Radio button Requester", "Checked");
                                        startActivity(new Intent(RegNewActivity.this, ListOfWorkersActivity.class));
                                    }
                                    else{
                                        Log.d("Radio button Worker", "Checked");
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

    void updateUI(String s) {

    }

}
