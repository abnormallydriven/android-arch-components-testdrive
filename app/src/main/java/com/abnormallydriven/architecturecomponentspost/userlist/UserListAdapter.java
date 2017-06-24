package com.abnormallydriven.architecturecomponentspost.userlist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.AdapterItemUserBinding;

import javax.inject.Inject;

class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private User[] users;

    @Inject
    UserListAdapter(){
        users = new User[0];
    }

    void update(@NonNull User[] updatedUsers){
        users = updatedUsers;
        //TODO user a diff util
        notifyDataSetChanged();
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItemUserBinding userItemBinding = AdapterItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users[position]);
    }

    @Override
    public int getItemCount() {
        return users.length;
    }
}
