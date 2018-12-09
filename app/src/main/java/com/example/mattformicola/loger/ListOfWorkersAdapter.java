/*
package com.example.mattformicola.loger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ListOfWorkersAdapter extends RecyclerView.Adapter<ListOfWorkersAdapter.MyViewHolder> {

    private ArrayList<User> mUserList = new ArrayList<>();
    private LayoutInflater inflater;

    private ArrayList<String> email;
    private ArrayList<String> phone;
    private ArrayList<String> zipCode;

*/
/*
    ArrayList<String> email,ArrayList<String> phone, ArrayList<String> zipCode
*//*

    //ListOfWorkersAdapter(LayoutInflater inflater, ArrayList<User> mUserList) {
        this.inflater = inflater;
        this.mUserList = mUserList;


       */
/* this.email = email;
        this.phone = phone;
        this.zipCode = zipCode;*//*

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView emailTv;
        TextView phoneTv;
        TextView zipTv;

        MyViewHolder(View v) {
            super(v);
            emailTv = v.findViewById(R.id.textEmail);
            phoneTv = v.findViewById(R.id.textPhoneNumber);
            zipTv = v.findViewById(R.id.textWorkerZip);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder  (@NonNull ViewGroup viewGroup, int i) {
        inflater = LayoutInflater.from(viewGroup.getContext());
        Log.d("HEREEEEEEE", "");
        View view = inflater.inflate(R.layout.custom_recycler_list_element, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        User user = mUserList.get(position);
        myViewHolder.emailTv.setText(user.getFirstName());
        myViewHolder.phoneTv.setText(user.getPhoneNumber());
        myViewHolder.zipTv.setText(user.getZip());
        //mUserList.add(user);

        */
/*myViewHolder.emailTv.setText(email.get(position));
        myViewHolder.phoneTv.setText(phone.get(position));
        myViewHolder.zipTv.setText(zipCode.get(position));*//*


    }

    @Override
    public int getItemCount() {
        Log.d("Size of mUserList", "" + mUserList.size());
        //Log.d("Size of recycler", ""+email.size());
        return mUserList.size();
    }
*/
//}