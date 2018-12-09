/*
package com.example.mattformicola.loger;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayListOfWorkersActivity extends AppCompatActivity {


    //Firebase
    private DatabaseReference mDatabase;
    FirebaseRecyclerOptions<User> options;
    FirebaseRecyclerAdapter<User, ListOfWorkersAdapter> adapter;
    //android Layout
    Button btnAdd;
    private EditText editTextDatabase;
    RecyclerView recyclerView;
    //Array List
    String TAG ="Got to this Point";
    //TextView
    TextView phone,email,zip;

    private ArrayList<User> mUserList = new ArrayList<>();

   */
/* private ArrayList<String> emailList = new ArrayList<>();
    private ArrayList<String> phoneList = new ArrayList<>();
    private ArrayList<String> zipList = new ArrayList<>();*//*



    //    private ArrayAdapter<String> adapter;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_workers);

        */
/*phoneList.add("23423423");
        phoneList.add("234234234");

        emailList.add("asdfasdf@sdf.com");
        emailList.add("22323@sdf.com");

        zipList.add("23423");
        zipList.add("45545");*//*


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef  = database.child("Users");
       // mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        email =  findViewById(R.id.textEmail);
        phone =  findViewById(R.id.textPhoneNumber);
        zip = findViewById(R.id.textWorkerZip);


        editTextDatabase = findViewById(R.id.editTextDatabase);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.database_list_view_Workers);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);



      //  final Query workerQuery = mDatabase.child("Worker").equalTo("2131230871");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        mUserList.add(user);

                        */
/*String email = ds.child("email").getValue(String.class);
                        String phone = ds.child("PhoneNumber").getValue(String.class);
                        String zip = ds.child("zip").getValue(String.class);*//*

                        Log.d("phone", "is" + ds.getValue());

                    }
                    //mAdapter = new ListOfWorkersAdapter(mUserList);
                */
/*mAdapter = new ListOfWorkersAdapter(emailList,phoneList, zipList );*//*


                //recyclerView.setAdapter(mAdapter);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });


        mAdapter = new ListOfWorkersAdapter(DisplayListOfWorkersActivity.this, mUserList);
        */
/*mAdapter = new ListOfWorkersAdapter(emailList,phoneList, zipList );*//*

        recyclerView.setAdapter(mAdapter);



    }

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDatabase.push().setValue(editTextDatabase.getText());
//            }
//        });
//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String string = dataSnapshot.getValue(String.class);
//                arrayList.add(string);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                String string = dataSnapshot.getValue(String.class);
//                arrayList.remove(string);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }


  */
/*  String TAG = "Got to this Point";
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    List<String> workerList;
    WorkerZip WorkerZip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_workers);
        workerList = new ArrayList<>();
        mDatabase = firebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot worker: dataSnapshot.getChildren()){
                    Log.d("worker", "is" + worker.getValue());
                    WorkerZip = worker.getValue(WorkerZip.class);
                    workerList.add(WorkerZip.WorkerZip);
                }
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });
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

*//*





*/
