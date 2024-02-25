package com.example.myapplication.ui.lab1_2;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLab12Binding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Lab1_2Fragment extends Fragment {
    private static final String TAG = "lab 1_2";
    private FragmentLab12Binding binding;
    private Button refreshBtn;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Lab1_2ViewModel lab12ViewModel =
                new ViewModelProvider(this).get(Lab1_2ViewModel.class);

        binding = FragmentLab12Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        lab12ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        refreshBtn = binding.buttonRefresh;
        listView = binding.listViewRssFeed;
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRssFeed();
            }
        });
        getRssFeed();
        return root;
    }

    public void getRssFeed(){
        // Create API service
        ApiService apiService = ApiClient.getApiService();

        // Make API call
        Call<RssFeed> call = apiService.getRssFeed();
        call.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                // Hide the progress bar
//                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "on response: " + response.isSuccessful());
                if (response.isSuccessful() && response.body() != null) {
                    RssFeed rssFeed = response.body();
                    // Process the RSS feed data
                    List<RssItem> items = rssFeed.getChannel().getItems();
                    setListView(items);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                // Hide the progress bar
//                progressBar.setVisibility(View.GONE);

                // Handle failure
                Log.e(TAG, "fail to get :" + t.getMessage());
            }
        });
    }

    public void setListView(List<RssItem> items){
        ArrayAdapter<RssItem> adapter = new CustomAdapter(getContext(), items);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}