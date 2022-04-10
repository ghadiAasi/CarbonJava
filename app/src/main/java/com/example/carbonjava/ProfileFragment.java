package com.example.carbonjava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private ImageView profilePic;
    private ListView listView;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_profile, container, false);

        TextView userText = rootView.findViewById(R.id.usersName);
        TextView userEmail = rootView.findViewById(R.id.usersText);
        listView = (ListView) rootView.findViewById(R.id.listView);

        userText.setText(mAuth.getCurrentUser().getDisplayName());
        userEmail.setText(mAuth.getCurrentUser().getEmail());

        profilePic = rootView.findViewById(R.id.imageViewProfile);

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = firebaseDatabase.getReference("Users/"+user);
        String key = myRef.getKey();


        Button camera = rootView.findViewById(R.id.buttonCamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileCamera.class);
                startActivityForResult(intent, 101);
            }
        });
        Button screenshot = rootView.findViewById(R.id.Array);
        screenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ArrayListActivity1.class);
                startActivity(intent);
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");

        db.getReference("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Item> items = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Item item =dataSnapshot.getValue(Item.class);
                    items.add(item);
                }
                refreshList(items);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "ERRRRRRRRROR");
            }
        });

        return rootView;

    }
    private void refreshList(List<Item> items){
        CustomLevelAdapter adapter = new CustomLevelAdapter(getActivity() ,R.layout.list_item_level, items);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            if (resultCode == Activity.RESULT_OK){
                String base64Image = data.getStringExtra("picture");
                Bitmap bmp = stringToBitmap(base64Image);
                profilePic.setImageBitmap(bmp);
            }
        }
    }
    private Bitmap stringToBitmap(String image){
        try{
            byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);

            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
