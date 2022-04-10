package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        myListView= findViewById(R.id.myListViewArray);

        String UID = maFirebaseAuth.getUid();
        Toast.makeText(this,"UID"+UID,Toast.LENGTH_LONG).show();
        DatabaseReference myRef = database.getReference("Users/"+UID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Item item =dataSnapshot.getValue(Item.class);
                    list.add(item);

                    myAdapter= new CustomAdapter(getApplicationContext(),R.layout.itemrole,list);
                    myListView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}