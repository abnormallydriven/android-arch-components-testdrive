package com.abnormallydriven.architecturecomponentspost.adduser;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.VisibleForTesting;

import com.abnormallydriven.architecturecomponentspost.common.di.Disk;
import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class AddUserViewModel extends ViewModel {

    private final Executor diskExecutor;
    private final Executor uiExector;

    private final ObservableBoolean isValidForm;
    private final ObservableField<String> firstName;
    private final ObservableField<String> lastName;
    private final ObservableField<String> age;
    private final ObservableField<String> gender;

    @Inject
    AddUserViewModel(@Disk Executor diskExecutor,
                     @UI Executor uiExector) {
        this.diskExecutor = diskExecutor;
        this.uiExector = uiExector;
        isValidForm = new ObservableBoolean(false);

        firstName = new ObservableField<>("");
        lastName = new ObservableField<>("");
        age = new ObservableField<>("");
        gender = new ObservableField<>("");


        Observable.OnPropertyChangedCallback formValidationCallback = new FormValidationPropertyCallback();

        firstName.addOnPropertyChangedCallback(formValidationCallback);
        lastName.addOnPropertyChangedCallback(formValidationCallback);
        age.addOnPropertyChangedCallback(formValidationCallback);
        gender.addOnPropertyChangedCallback(formValidationCallback);
    }


    public ObservableBoolean isValidForm(){
        return isValidForm;
    }

    public ObservableField<String> getFirstName() {
        return firstName;
    }

    public ObservableField<String> getLastName() {
        return lastName;
    }

    public ObservableField<String> getAge() {
        return age;
    }

    public ObservableField getGender() {
        return gender;
    }


    @VisibleForTesting
    protected void validateForm(){

        if(firstName.get().isEmpty()){
            isValidForm.set(false);
        } else if(lastName.get().isEmpty()){
            isValidForm.set(false);
        } else if(age.get().isEmpty()){
            isValidForm.set(false);
        } else if(gender.get().isEmpty()){
            isValidForm.set(false);
        } else {
            isValidForm.set(true);
        }

    }

    private class FormValidationPropertyCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            validateForm();
        }
    }
}
