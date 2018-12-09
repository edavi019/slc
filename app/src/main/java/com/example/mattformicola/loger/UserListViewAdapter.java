package com.example.mattformicola.loger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.MyViewHolder > {

    Context context;
    ArrayList<UserList> userLists;


    public UserListViewAdapter(Context c , ArrayList<UserList> u){
        context = c;
        userLists = u;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        /*inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.custom_recycler_list_element, viewGroup, false);*/
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_recycler_list_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.userNameTv.setText(userLists.get(position).getUserName());
        myViewHolder.emailTv.setText(userLists.get(position).getEmail());
        myViewHolder.phoneTv.setText(userLists.get(position).getPhone());
        myViewHolder.zipTv.setText(userLists.get(position).getZip());

    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        TextView userNameTv;
        TextView emailTv;
        TextView phoneTv;
        TextView zipTv;

        public MyViewHolder(@NonNull View v) {
            super(v);
            userNameTv = v.findViewById(R.id.textuserName);
            emailTv = v.findViewById(R.id.textEmail);
            phoneTv = v.findViewById(R.id.textPhoneNumber);
            zipTv = v.findViewById(R.id.textWorkerZip);
        }
    }

}
