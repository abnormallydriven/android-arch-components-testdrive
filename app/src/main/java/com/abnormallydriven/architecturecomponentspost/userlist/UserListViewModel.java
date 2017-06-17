package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.abnormallydriven.architecturecomponentspost.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.di.Disk;

import java.util.concurrent.Executor;

import javax.inject.Inject;


public class UserListViewModel extends ViewModel {

    private final Executor diskExecutor;
    private final UserDao userDao;
    private final MutableLiveData<User[]> usersMutableLiveData;

    @Inject
    UserListViewModel(@Disk Executor diskExecutor, final UserDao userDao){
        this.diskExecutor = diskExecutor;
        this.userDao = userDao;
        usersMutableLiveData = new MutableLiveData<>();
        usersMutableLiveData.setValue(new User[0]);
    }


    public void onRefreshUserList(){
        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersMutableLiveData.setValue(userDao.getAllUsers().getValue());
            }
        });
    }

}
