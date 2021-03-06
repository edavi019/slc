package com.example.mattformicola.loger;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    String TAG = "SIGN IN ";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        Button signIn = findViewById(R.id.signinBtn);
        Button register = findViewById(R.id.registerBtn);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        signIn.setOnClickListener(v -> {

            SignIn();
        });

        register.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), RegNewActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        if(currentUser!=null) {
            Log.d("Currently logged in", currentUser.getEmail());
            int worker = pref.getInt("workerOrNot", -1);
            Log.d("Woker or not", "" + worker);
            if(worker == 1){
                startActivity(new Intent(MainActivity.this, ListOfRequestersActivity.class));
            }
            else{
                startActivity(new Intent(MainActivity.this, ListOfWorkersActivity.class));
            }
        }
    }



    private void SignIn() {

         String email = editTextEmail.getText().toString().trim();
         String password = editTextPassword.getText().toString().trim();

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



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        int worker = preferences.getInt("workerOrNot", -1);

                        if(worker == 1){
                            startActivity(new Intent(MainActivity.this, ListOfRequestersActivity.class));
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, ListOfWorkersActivity.class));
                        }

                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                });
    }



    public void updateUI(FirebaseUser user) {
//        Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();



    }
}



