package com.example.myapplication.ui.lab1_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Lab1_1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Lab1_1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}