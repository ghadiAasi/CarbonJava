package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter);
    }



    public void interacccount(View view) {
        Intent Intent = new Intent(this, LogInActivity.class);
        startActivity(Intent);
    }
    public void signup(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}