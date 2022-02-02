package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ArrayListActivity1 extends AppCompatActivity  {

    private ListView myListView;
    private CustomAdapter myAdapter;
    private ArrayList<Item> list;
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list1);

        list = new ArrayList<>();
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
        list.add(new Item("this is my first Item",R.drawable.download, true,50));
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
        String UID = maFirebaseAuth.getUid();
        Toast.makeText(this,"UID"+UID,Toast.LENGTH_LONG).show();
        DatabaseReference myRef = database.getReference("users/"+UID);
        Item item = new Item("this is my first Item",R.drawable.download,true,50);
        myRef.push().setValue(item);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Item Item =dataSnapshot.getValue(Item.class);
                    list.add(item);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}