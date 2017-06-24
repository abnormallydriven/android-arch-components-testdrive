package com.abnormallydriven.architecturecomponentspost.adduser;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;
import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.concurrent.Executor;

import javax.inject.Inject;

//TODO test the clear method for this viewModel
public class AddUserViewModel extends ViewModel {

    private final Executor diskExecutor;
    private final Executor uiExector;
    @NonNull
    private final UserDao userDao;


    @NonNull
    private final ObservableBoolean isValidForm;

    @NonNull
    private final ObservableField<String> firstName;

    @NonNull
    private final ObservableField<String> lastName;

    @NonNull
    private final ObservableField<String> age;

    @NonNull
    private final ObservableField<String> gender;

    @NonNull
    private final ObservableBoolean isSavingData;

    @NonNull
    private final ObservableBoolean shouldShowSavedSuccessMessage;

    @Inject
    AddUserViewModel(@NonNull @Disk Executor diskExecutor,
                     @NonNull @UI Executor uiExecutor,
                     @NonNull UserDao userDao) {
        this.diskExecutor = diskExecutor;
        this.uiExector = uiExecutor;
        this.userDao = userDao;
        isValidForm = new ObservableBoolean(false);

        firstName = new ObservableField<>("");
        lastName = new ObservableField<>("");
        age = new ObservableField<>("");
        gender = new ObservableField<>("");
        isSavingData = new ObservableBoolean(false);
        shouldShowSavedSuccessMessage = new ObservableBoolean(false);

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

    public ObservableField<String> getGender() {
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
            try{
                Integer.valueOf(age.get());
                isValidForm.set(true);
            } catch(NumberFormatException ex){
                //TODO: raise event to let user know age must be an int
                isValidForm().set(false);
            }
        }
    }

    @NonNull
    public ObservableBoolean getIsSavingData() {
        return isSavingData;
    }

    public void onAddUserClick() {
        if(isSavingData.get()){
            return;
        }

        isSavingData.set(true);

        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.inserUser(new User(firstName.get(), lastName.get(), Integer.valueOf(age.get()), gender.get()));
                uiExector.execute(new Runnable() {
                    @Override
                    public void run() {

                        firstName.set("");
                        lastName.set("");
                        age.set("");
                        gender.set("");

                        isSavingData.set(false);
                        shouldShowSavedSuccessMessage.set(true);
                    }
                });
            }
        });
    }

    @NonNull
    public ObservableBoolean getShouldShowSavedSuccessMessage() {
        return shouldShowSavedSuccessMessage;
    }

    public void onShowSaveSuccessMessage() {
        shouldShowSavedSuccessMessage.set(false);
    }

    private class FormValidationPropertyCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            validateForm();
        }
    }
}
