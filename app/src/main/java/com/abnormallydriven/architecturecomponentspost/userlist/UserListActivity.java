package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityUserListBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserListActivity extends AppCompatActivity implements LifecycleRegistryOwner{

    @Inject
    LifecycleRegistry lifeCycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Inject
    UserListAdapter userListAdapter;

    @Nullable
    private UserListViewModel userListViewModel;

    @Nullable
    private ActivityUserListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);

        userListViewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(UserListViewModel.class);

        if(null == savedInstanceState){
            userListViewModel.onRefreshUserList();
        }

        userListViewModel.getUsers().observe(this, new Observer<User[]>() {
            @Override
            public void onChanged(@Nullable User[] users) {
                if(users != null){
                    userListAdapter.update(users);
                } else {
                    userListAdapter.update(new User[0]);
                }
            }
        });

        binding.addUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userListViewModel.onUserAddClick();
            }
        });

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifeCycleRegistry;
    }
}
