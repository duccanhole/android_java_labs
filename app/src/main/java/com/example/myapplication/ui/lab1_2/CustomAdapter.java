package com.example.myapplication.ui.lab1_2;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<RssItem> {
    public CustomAdapter(@NonNull Context context, List<RssItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }

        RssItem currentItem = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = listItemView.findViewById(R.id.subtitleTextView);

        if (currentItem != null) {
            titleTextView.setText(currentItem.getTitle());
            descriptionTextView.setText(Html.fromHtml(currentItem.getDescription()));
        }

        return listItemView;
    }
}
