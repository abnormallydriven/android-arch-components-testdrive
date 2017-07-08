package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityAddMeasurementsBinding;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddMeasurementsActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    AddMeasurementsViewModel viewModel;

    private ActivityAddMeasurementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupView();
    }

    private void setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_measurements);
        binding.setViewModel(viewModel);

        viewModel.shouldShowSaveSuccessMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(viewModel.shouldShowSaveSuccessMessage.get()){
                    showMeasurementSavedMessage();
                }
            }
        });

        if(viewModel.shouldShowSaveSuccessMessage.get()){
            showMeasurementSavedMessage();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_measurements_menu, menu);
        Drawable drawable = menu.findItem(R.id.save_measurement_item).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, android.R.color.white));
        menu.findItem(R.id.save_measurement_item).setIcon(drawable);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.save_measurement_item){
            viewModel.onSaveMeasurementForUserClick(getMeasurementFromFields());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMeasurementSavedMessage() {
        viewModel.shouldShowSaveSuccessMessage.set(false);
        Snackbar.make(binding.getRoot(), "Measurement Saved Successfully!", Snackbar.LENGTH_INDEFINITE).show();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(AddMeasurementsViewModel.class);
    }

    private Measurement getMeasurementFromFields(){
        final Measurement currentMeasurement = new Measurement();

        currentMeasurement.setLowerLeftBicep(safelyConvertFloat(binding.lowerLeftBicepContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLowerRightBicep(safelyConvertFloat(binding.lowerRightBicepContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLeftBicep(safelyConvertFloat(binding.leftBicepContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setRightBicep(safelyConvertFloat(binding.rightBicepContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLeftCalf(safelyConvertFloat(binding.leftCalfContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setRightCalf(safelyConvertFloat(binding.rightCalfContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setChest(safelyConvertFloat(binding.chestContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLeftForearm(safelyConvertFloat(binding.leftForearmContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setRightForearm(safelyConvertFloat(binding.rightForearmContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setHighHip(safelyConvertFloat(binding.highHipContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setHip(safelyConvertFloat(binding.hipContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLowerLeftThigh(safelyConvertFloat(binding.lowerLeftThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLowerRightThigh(safelyConvertFloat(binding.lowerRightThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setMidLeftThigh(safelyConvertFloat(binding.midLeftThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setMidRightThigh(safelyConvertFloat(binding.midRightThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setUpperLeftThigh(safelyConvertFloat(binding.upperLeftThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setUpperRightThigh(safelyConvertFloat(binding.upperRightThighContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setAbdominalWaist(safelyConvertFloat(binding.abdominalWaistContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setLowerWaist(safelyConvertFloat(binding.lowerWaistContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setNarrowestWaist(safelyConvertFloat(binding.narrowestWaistContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setWeight(safelyConvertFloat(binding.weightContainer.measurementValueEditText.getText().toString()));
        currentMeasurement.setMeasurementDate(new Date());
        currentMeasurement.setUserId(getUserFromIntent().getId());

        return currentMeasurement;
    }

    private float safelyConvertFloat(String floatString){
        try{
            return Float.valueOf(floatString);
        } catch(NumberFormatException ex){
            return 0.0f;
        }
    }

    private User getUserFromIntent(){
        return getIntent().getParcelableExtra(AddMeasurementsActivityIntentFactory.USER_EXTRA_KEY);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
