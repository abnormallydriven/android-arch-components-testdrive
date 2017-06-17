package com.abnormallydriven.architecturecomponentspost.userlist;

import android.support.v7.widget.RecyclerView;

import com.abnormallydriven.architecturecomponentspost.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.AdapterItemUserBinding;

class UserViewHolder extends RecyclerView.ViewHolder {

    private final AdapterItemUserBinding binding;

    public UserViewHolder(AdapterItemUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(User user){
        binding.setName(user.getFirstName() + " " + user.getLastName());
        binding.setGender(user.getGender());
    }
}
