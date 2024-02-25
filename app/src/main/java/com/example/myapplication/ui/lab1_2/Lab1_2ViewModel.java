package com.example.myapplication.ui.lab1_2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Lab1_2ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Lab1_2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}