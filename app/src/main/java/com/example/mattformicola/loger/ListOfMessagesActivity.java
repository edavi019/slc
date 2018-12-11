package com.example.mattformicola.loger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mattformicola.loger.Adapter.ListOfMessagesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import android.widget.Toolbar;

public class ListOfMessagesActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    ArrayList<String> convos = new ArrayList<>();
    DatabaseReference mDatabase;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        recyclerView = findViewById(R.id.conversation_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Log.d("Current usser ", "is " + firebaseUser.getEmail());
//        convos.add("email1***email2");
//        convos.add("email12***email21");
//        convos.add("email41***email32");



        mDatabase = FirebaseDatabase.getInstance().getReference().child("ChatIndexes");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                convos = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    String value = dataSnapshot1.getKey();

                    String temp = firebaseUser.getEmail();
                    String currUser = temp.substring(0,temp.length()-4);
                    Log.d("After substring: ", currUser);

                    String userEmails[] = value.split("\\*\\*\\*");
                    if(userEmails[0].equals(currUser) || userEmails[1].equals(currUser)){
                        convos.add(value);
                    }


                }

                ListOfMessagesAdapter adapter = new ListOfMessagesAdapter(ListOfMessagesActivity.this, convos);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListOfMessagesActivity.this, "dang shits broke ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
