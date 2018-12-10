package com.example.mattformicola.loger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfRequestersActivity  extends AppCompatActivity {

    //Firebase
    DatabaseReference mDatabase;
    DatabaseReference radioRef;
    RecyclerView recyclerView;
    UserListViewAdapter adapter;

    ArrayList<UserList> ulist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_requesters_acitivty);
        radioRef = FirebaseDatabase.getInstance().getReference().child("Users").child("radio").child("2131230870");
        Log.d("radio ID is ", ""+radioRef.toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // refreshButton = findViewById(R.id.refreshButton);
        recyclerView = findViewById(R.id.RecyclerView_Requesters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Log.d("Recycler" , "log1");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


       //mDatabase.orderByChild("radio").equalTo(2131230870).addValueEventListener(new ValueEventListener() {

        mDatabase.orderByChild("radio").equalTo(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ulist = new ArrayList<UserList>();
                Log.d("Recycler" , "log2");
//                Log.d("UII",""+dataSnapshot.toString());

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    UserList u = dataSnapshot1.getValue(UserList.class);
                    Log.d("UII",""+dataSnapshot1.toString());
                    ulist.add(u);
                }


                adapter = new UserListViewAdapter(ListOfRequestersActivity.this,ulist);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListOfRequestersActivity.this, "dang shits broke ", Toast.LENGTH_SHORT).show();

            }



        });

        Log.d("we are ", "oncreate");
        Log.d("We are here", "" + ulist.size());
        adapter = new UserListViewAdapter(ListOfRequestersActivity.this,ulist);
//        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Log.d("Recycler" , "log1");

        /*adapter = new UserListViewAdapter(ListOfWorkersActivity.this,ulist);
        recyclerView.setAdapter(adapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_inbox, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.inbox){
            Toast.makeText(this, "Clicked on Inbox", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ListOfRequestersActivity.this, ListOfMessagesActivity.class));
        }
        if(id == android.R.id.home){
            finish();
        }

        if(id == R.id.logout){
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ListOfRequestersActivity.this, MainActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}

