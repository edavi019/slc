package com.example.mattformicola.loger;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class DisplayListOfRequestersAcitivty extends AppCompatActivity {

    //Firebase
/*    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usersRef = rootRef.child("Users");*/
    //android Layout
    Button btnAdd;
    private EditText editTextDatabase;
    RecyclerView recyclerView;
    //Array List
    String TAG = "Got to this Point";

    private ArrayList<String> phoneList = new ArrayList<>();
    private ArrayList<String> emailList = new ArrayList<>();
    private ArrayList<String> zipList = new ArrayList<>();


    //    private ArrayAdapter<String> adapter;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_list_of_workers);
        //phoneList.add("23423423");
        //phoneList.add("234234234");

        emailList.add("asdfasdf@sdf.com");
        emailList.add("22323@sdf.com");

        zipList.add("23423");
        zipList.add("45545");

        // mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        //query = FirebaseDatabase.getInstance().getReference().child("Users");


        editTextDatabase = findViewById(R.id.editTextDatabase);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.ReyclerView_Workers);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("Users");
        usersRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String phone = ds.child("PhoneNumber").getValue(String.class);
                String email = ds.child("email").getValue(String.class);
                String zip = ds.child("zip").getValue(String.class);
                phoneList.add(phone);
                emailList.add(email);
                zipList.add(zip);

               // String value = dataSnapshot.getValue(String.class);
              //  Log.d(TAG, "Value is: " + value);


            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        /*@Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //User value = ds.getValue(User.class);
                    User user = ds.child("User").getValue(User.class);

                    String phone = user.getPhoneNumber();
                    Log.d(TAG, "String is: " + phone);
                    String email = user.getEmail();
                    Log.d(TAG, "String is: " + email);
                    String zip = user.getZip();
                    Log.d(TAG, "String is: " + zip);
                  *//*  User.setPhoneNumber(phone);
                    User.setEmail(email);
                    User.setZip(zip);*//*
         */


        //phoneList.add(phone.Phone);

        //String value = dataSnapshot.getValue(String.class);
        //Log.d(TAG, "Value is: " + value);



    });

        //usersRef.addListenerForSingleValueEvent(ValueEventLi);

       // mAdapter = new ListOfWorkersAdapter(emailList, zipList, phoneList);

        recyclerView.setAdapter(mAdapter);


    }
}
