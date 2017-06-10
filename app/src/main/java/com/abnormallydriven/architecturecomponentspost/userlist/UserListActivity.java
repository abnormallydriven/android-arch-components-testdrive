package com.abnormallydriven.architecturecomponentspost.userlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.data.UserDao;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
    }
}
