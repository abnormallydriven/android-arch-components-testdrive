package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.abnormallydriven.architecturecomponentspost.common.NavigationController;
import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class UserListViewModel extends ViewModel {

    private final Executor diskExecutor;
    private final NavigationController navigationController;
    private final UserDao userDao;
    private final MediatorLiveData<User[]> usersMutableLiveData;

    @Inject
    UserListViewModel(@Disk Executor diskExecutor,
                      NavigationController navigationController,
                      final UserDao userDao){
        this.diskExecutor = diskExecutor;
        this.navigationController = navigationController;
        this.userDao = userDao;
        usersMutableLiveData = new MediatorLiveData<>();
        usersMutableLiveData.setValue(new User[0]);
    }

    LiveData<User[]> getUsers(){
        return usersMutableLiveData;
    }


    void onRefreshUserList(){
        //TODO spinners
        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final LiveData<User[]> liveData = userDao.getAllUsers();
                usersMutableLiveData.addSource(liveData, new Observer<User[]>() {
                    @Override
                    public void onChanged(@Nullable User[] users) {
                        usersMutableLiveData.setValue(users);
                    }
                });
            }
        });
    }

    void onUserAddClick() {
        navigationController.navigateToUserAdd();
    }

    void onUserSelectedClick(User selectedUser) {
        navigationController.navigateToUserMeasurements(selectedUser);
    }
}
