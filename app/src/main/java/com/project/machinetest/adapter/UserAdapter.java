package com.project.machinetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.machinetest.R;
import com.project.machinetest.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> userList;
        
    public UserAdapter(Context context,List<User> userList) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.tvFullaccountnumber.setText(userList.get(position).getFullaccountnumber());
        holder.tvHeadname.setText(userList.get(position).getHeadname());
        holder.tvCustomername.setText(userList.get(position).getCustomername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullaccountnumber,tvHeadname,tvCustomername;

        public UserViewHolder(View v) {
            super(v);
            tvFullaccountnumber = (TextView) v.findViewById(R.id.tvFullaccountnumber);
            tvHeadname = (TextView) v.findViewById(R.id.tvHeadname);
            tvCustomername = (TextView) v.findViewById(R.id.tvCustomername);
        }

    }
    
}