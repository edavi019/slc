package com.example.mattformicola.loger;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mattformicola.loger.Adapter.ListOfMessagesAdapter;
import com.example.mattformicola.loger.Adapter.MessageAdapter;
import com.example.mattformicola.loger.Model.Chat;
import com.example.mattformicola.loger.Model.ImageUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessageActivity extends AppCompatActivity {


    CircleImageView profile_image;
    TextView UserName;

    DatabaseReference reference;

    ImageButton btn_send;
    EditText text_send;

    MessageAdapter messageAdapter;
    ArrayList<String> mchat = new ArrayList<>();
    ArrayList<String> sentBy = new ArrayList<>();
    FirebaseUser firebaseUser;

    RecyclerView recyclerView;
    Image defaultIMG;
    DatabaseReference mDatabase;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("CHAT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        profile_image = findViewById(R.id.profile_image);
        UserName = findViewById(R.id.UserName);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();

        final String wantsToTalkTo = intent.getStringExtra("wantsToTalk");
        final String chatKey = intent.getStringExtra("key");

        UserName.setText(wantsToTalkTo);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chat").child(chatKey);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat = new ArrayList<>();
                sentBy = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Log.d("Data received is", dataSnapshot1.toString());

                    HashMap<String, HashMap<String,String>> data = (HashMap<String, HashMap<String,String>>) dataSnapshot1.getValue();

                    Log.d("Final", "is " + data.get("from"));

                    String message = String.valueOf(data.get("message"));
                    String from = String.valueOf(data.get("from"));

                    mchat.add(message);
                    sentBy.add(from);

                }

                messageAdapter = new MessageAdapter(MessageActivity.this, mchat, sentBy);
                recyclerView.setAdapter(messageAdapter);

                messageAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MessageActivity.this, "dang shits broke ", Toast.LENGTH_SHORT).show();

            }



        });





        btn_send.setOnClickListener(v -> {
            String msg = text_send.getText().toString();
            if (!msg.equals("")){
                sendMessage(firebaseUser.getEmail(), msg);
            }
            else {
                Toast.makeText(MessageActivity.this,"You can't send empty messages!", Toast.LENGTH_SHORT).show();
            }
            text_send.setText("");
        });


    }

    private void sendMessage(String sender, String message){

        String chatKey = intent.getStringExtra("key");
        String timeStamp = String.valueOf(System.currentTimeMillis());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Chat").child(chatKey).child(timeStamp);


        HashMap<String, String> toSend = new HashMap<>();
        toSend.put("from", sender);
        toSend.put("message", message);

        reference.setValue(toSend);
    }

//    private void readMessages(final String myid, final String userid, final String imageurl) {
//        mchat = new ArrayList<>();
//
//        reference = FirebaseDatabase.getInstance().getReference("Chats");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mchat.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Chat chat = snapshot.getValue(Chat.class);
//                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
//                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)){
//                        mchat.add(chat);
//                    }
//
//                    messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
//                    recyclerView.setAdapter(messageAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }


}
