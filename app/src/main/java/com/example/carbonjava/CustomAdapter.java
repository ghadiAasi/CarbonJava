package com.example.carbonjava;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> objects;
    private int resource;

    private FirebaseDatabase database =FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String UID = mAuth.getUid();
    private DatabaseReference myRef =database.getReference("Cart/"+UID);

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
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

         Item screenShot =getItem(position);

         if(screenShot != null) {
             String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
             FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
             DatabaseReference myRef = firebaseDatabase.getReference("Users/"+user+"/"+screenShot.getKey());

             ImageView imageView = view.findViewById(R.id.imageItem);
             TextView textView = view.findViewById(R.id.gameName);
             textView.setText(screenShot.getDescription());

             Button itemButton = view.findViewById(R.id.itemButtonDelete);

             imageView.setImageBitmap(StringToBitMap(screenShot.getPic()));
             itemButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view){
                     if(objects!=null) {
                         myRef.removeValue();
                         Toast.makeText(context, "this item was deleted", Toast.LENGTH_SHORT).show();
                         objects.remove(position);
                         notifyDataSetChanged();
                     }
                 }
             });

         }

         return view;
    }
    public Bitmap StringToBitMap(String image){
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
