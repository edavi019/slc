package com.example.mattformicola.loger.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mattformicola.loger.MessageActivity;
import com.example.mattformicola.loger.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListOfMessagesAdapter extends RecyclerView.Adapter<ListOfMessagesAdapter.MyViewHolderMessages > {

    Context context;
    ArrayList<String> conversations;
    FirebaseUser fuser;


    public ListOfMessagesAdapter(Context c , ArrayList<String> u){
        context = c;
        conversations = u;
        fuser = FirebaseAuth.getInstance().getCurrentUser();
    }


    @NonNull
    @Override
    public MyViewHolderMessages onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        /*inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.custom_recycler_list_element, viewGroup, false);*/
        return new MyViewHolderMessages(LayoutInflater.from(context).inflate(R.layout.custom_conversation_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMessages myViewHolder, final int position) {

        myViewHolder.userNameTv.setText(conversations.get(position));
        myViewHolder.conversation_card.setOnClickListener(v -> {

            Toast.makeText(context, "position: "+ position, Toast.LENGTH_SHORT).show();
            Intent in = new Intent(context, MessageActivity.class);

            in.putExtra("userid", fuser.getUid());

            String temp = conversations.get(position);
            String[] emails = temp.split("\\*\\*\\*");
            emails[0] = emails[0]+".com";
            emails[1] = emails[1]+".com";

            String toSend = (emails[0].equals(fuser.getEmail())) ? emails[1] : emails[0];
            Log.d("to Send", toSend);

            in.putExtra("wantsToTalk", toSend);
            in.putExtra("key", temp);
            context.startActivity(in);


        });

    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    class MyViewHolderMessages extends RecyclerView.ViewHolder{
        // each data item is just a string in this case

        TextView userNameTv;
        RelativeLayout conversation_card;

        MyViewHolderMessages(@NonNull View v) {
            super(v);
            userNameTv = v.findViewById(R.id.conversation_tv);
            conversation_card = v.findViewById(R.id.conversation_card);
        }
    }

}
