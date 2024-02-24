package com.example.myapplication.ui.lab1_1;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.myapplication.databinding.FragmentLab11Binding;

public class Lab1_1Fragment extends Fragment implements LoaderManager.LoaderCallbacks<D> {
    private static final String TAG = "Lab 1_1";
    private static final int LOADER_ID = 1;
    private FragmentLab11Binding binding;
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;
    private Button mSearchButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Lab1_1ViewModel galleryViewModel =
                new ViewModelProvider(this).get(Lab1_1ViewModel.class);

        binding = FragmentLab11Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        mBookInput = binding.bookInput;
        mTitleText = binding.titleText;
        mAuthorText = binding.authorText;
        mSearchButton = binding.searchButton;
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBooks(view);
            }
        });

        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
        return root;
    }

    public void searchBooks(View view) {
        // Get the search string from the input field.
        String queryString = mBookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
//        InputMethodManager inputManager = (InputMethodManager)
//                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(getContext().getCurrentFocus().getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty,
        // add the search term to the arguments Bundle and start the loader.
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            mAuthorText.setText("");
            mTitleText.setText("Loading ...");
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            LoaderManager.getInstance(this).restartLoader(LOADER_ID, null, this);
//            getContext().getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // Otherwise update the TextView to tell the user there is no connection or no search term.
        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText("Please enter a search term");
            } else {
                mAuthorText.setText("");
                mTitleText.setText("Please check your network connection and try again.");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @NonNull
    @Override
    public Loader<D> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<D> loader, D data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<D> loader) {

    }
}