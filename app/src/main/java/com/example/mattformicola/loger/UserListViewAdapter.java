package com.example.mattformicola.loger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.MyViewHolder > {

    Context context;
    ArrayList<UserList> userLists;
    FirebaseUser fuser;


    public UserListViewAdapter(Context c , ArrayList<UserList> u){
        context = c;
        userLists = u;
        fuser = FirebaseAuth.getInstance().getCurrentUser();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        /*inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.custom_recycler_list_element, viewGroup, false);*/
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_recycler_list_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.userNameTv.setText(userLists.get(position).getUserName());
        myViewHolder.emailTv.setText(userLists.get(position).getEmail());
        myViewHolder.zipTv.setText(userLists.get(position).getZip());

        myViewHolder.card.setOnClickListener(v -> {

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference myRef = database.getReference("ChatIndexes");
            String curr = fuser.getEmail();
            String otherUser = userLists.get(position).getEmail();
            String toSendToFirebase = curr.substring(0, curr.length() - 4) + "***" + otherUser.substring(0, otherUser.length() - 4);
            myRef.child(toSendToFirebase).setValue(1);

            Log.d("Current user is", "" + fuser.getEmail());
            Log.d("Wants to talk to ", "" + userLists.get(position).getEmail());
//                context.startActivity(new Intent(context, MessageActivity.class));
            Intent in = new Intent(context, MessageActivity.class);
            in.putExtra("userid", fuser.getUid());
            in.putExtra("wantsToTalk", userLists.get(position).getEmail());
            in.putExtra("key", toSendToFirebase);
            context.startActivity(in);
        });
    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        TextView userNameTv;
        TextView emailTv;
        TextView zipTv;
        ConstraintLayout card;

        public MyViewHolder(@NonNull View v) {
            super(v);
            userNameTv = v.findViewById(R.id.textuserName);
            emailTv = v.findViewById(R.id.textEmail);
            zipTv = v.findViewById(R.id.textWorkerZip);
            card = v.findViewById(R.id.user_card);
        }
    }

}
