package com.example.madpractical_week6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> userList;
    private Context context;
    private final int normal_viewtype = 0;
    private final int viewtype_7 = 1;

    public UserAdapter(ArrayList<User> userList, Context context){

        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position){
        if (userList.get(position).getName().endsWith("7")){
            return viewtype_7;
        } else return normal_viewtype;
    }

    //private View.OnClickListener mOnClickListener;
    //@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch(viewType) {
            case normal_viewtype: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
                return new NormalViewHolder(view);
            }
            case viewtype_7: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user7, parent, false);
                return new SpecialViewHolder(view);
            }

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(holder.getItemViewType()){
            case normal_viewtype: {
                NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
                String name = userList.get(position).getName();
                String description = userList.get(position).getDescription();
                normalViewHolder.name.setText(name);
                normalViewHolder.description.setText(description);
                break;
            }

            case viewtype_7:{
                SpecialViewHolder specialViewHolder = (SpecialViewHolder) holder;
                String name = userList.get(position).getName();
                String description = userList.get(position).getDescription();
                specialViewHolder.name.setText(name);
                specialViewHolder.description.setText(description);
                break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // end with 7 layout
    class SpecialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public SpecialViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            description= itemView.findViewById(R.id.textDescription);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                User user = userList.get(position);
                showAlertDialog(user);
            }
        }
    }

    // normal layout
    class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public NormalViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            description= itemView.findViewById(R.id.textDescription);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                User user = userList.get(position);
                showAlertDialog(user);
            }
        }
    }

    private void showAlertDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Profile")
                .setMessage(user.getName())
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Open user profile activity
                        // Replace UserProfileActivity.class with your actual activity class
                        Intent intent = new Intent(context, MainActivity.class);
                        // Pass any necessary data to the profile activity using intent extras
                        intent.putExtra("USER_OBJECT", user);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Close", null)
                .show();
    }
}

