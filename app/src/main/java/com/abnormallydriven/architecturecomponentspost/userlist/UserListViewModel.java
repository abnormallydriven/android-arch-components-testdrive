package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.abnormallydriven.architecturecomponentspost.common.NavigationController;
import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;
import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class UserListViewModel extends ViewModel {

    private final Executor diskExecutor;
    private final Executor uiExecutor;
    private final NavigationController navigationController;
    private final UserDao userDao;
    private final MutableLiveData<User[]> usersMutableLiveData;

    @Inject
    UserListViewModel(@Disk Executor diskExecutor,
                      @UI Executor uiExecutor,
                      NavigationController navigationController,
                      final UserDao userDao){
        this.diskExecutor = diskExecutor;
        this.uiExecutor = uiExecutor;
        this.navigationController = navigationController;
        this.userDao = userDao;
        usersMutableLiveData = new MutableLiveData<>();
        usersMutableLiveData.setValue(new User[0]);
    }

    LiveData<User[]> getUsers(){
        return usersMutableLiveData;
    }


    void onRefreshUserList(){
        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final User[] latestUsers = userDao.getAllUsers().getValue();
                uiExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        usersMutableLiveData.setValue(latestUsers);
                    }
                });
            }
        });
    }

    void onUserAddClick() {
        navigationController.navigateToUserAdd();
    }
}
