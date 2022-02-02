package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void camera(View view) {
        Intent intent =new Intent(this,ProfileCamera.class);
        startActivity(intent);
    }

    public void Array(View view) {
        Intent intent =new Intent(this,ArrayListActivity1.class);
        startActivity(intent);
    }
}