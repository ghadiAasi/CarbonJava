package com.example.carbonjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class CustomLevelAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int resource;

    public CustomLevelAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);

         Item item = getItem(position);
         TextView textView = (TextView)view.findViewById(R.id.textView);
         textView.setText(item.getDescription() + " - Level " + item.getLevel());
         return view;
    }
}
