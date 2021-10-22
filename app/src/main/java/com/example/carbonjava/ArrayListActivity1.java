package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrayListActivity1 extends AppCompatActivity  {

    private ListView myListView;
    private CustomAdapter myAdapter;
    private ArrayList<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list1);

        list = new ArrayList<>();
        list.add(new Item("this is my first Item",R.drawable.coding, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.coding, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.coding, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));


        myListView= findViewById(R.id.myListViewArray);
        myAdapter= new CustomAdapter(this,R.layout.itemrole,list);
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Item: " + list.get(i), Toast.LENGTH_LONG).show();
            }
    });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}