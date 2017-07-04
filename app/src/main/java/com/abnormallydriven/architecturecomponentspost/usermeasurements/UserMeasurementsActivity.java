package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityUserMeasurementsBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserMeasurementsActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Inject
    UserMeasurementsAdapter userMeasurementsAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ActivityUserMeasurementsBinding binding;

    private UserMeasurementViewModel userMeasurementViewModel;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_measurements);
        setupViewModel(savedInstanceState);
        setupView();
    }

    private void setupView() {
        binding.measurementsRecyclerView.setLayoutManager(linearLayoutManager);
        binding.measurementsRecyclerView.setAdapter(userMeasurementsAdapter);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        userMeasurementViewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(UserMeasurementViewModel.class);

        if(savedInstanceState == null){
            selectedUser = getIntent().getParcelableExtra("user");
            userMeasurementViewModel.onRefreshMeasurements(selectedUser.getId());
        }

        userMeasurementViewModel.getUserMeasurements().observe(this, new Observer<Measurement[]>() {
            @Override
            public void onChanged(@Nullable Measurement[] measurements) {
                if(measurements != null){
                    userMeasurementsAdapter.update(measurements);
                } else {
                    userMeasurementsAdapter.update(new Measurement[0]);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_measurements_menu, menu);
        Drawable drawable = menu.findItem(R.id.add_measurement_item).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, android.R.color.white));
        menu.findItem(R.id.add_measurement_item).setIcon(drawable);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_measurement_item){
            userMeasurementViewModel.onAddMeasurementClick(selectedUser);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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
        binding.measurementsRecyclerView.setAdapter(null);
        super.onDestroy();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
