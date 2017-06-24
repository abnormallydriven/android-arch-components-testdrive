package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Nullable
    private UserListViewModel userListViewModel;

    @Nullable
    private ActivityUserListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupViewModel(savedInstanceState);
        setupView();
    }

    private void setupViewModel(Bundle savedInstanceState) {
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


    }

    private void setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);

        binding.userListRecyclerView.setLayoutManager(linearLayoutManager);
        binding.userListRecyclerView.setAdapter(userListAdapter);

        binding.addUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userListViewModel != null) {
                    userListViewModel.onUserAddClick();
                }
            }
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifeCycleRegistry;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("layoutManagerState", linearLayoutManager.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        linearLayoutManager.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        binding.userListRecyclerView.setAdapter(null);
        super.onDestroy();
    }
}
