package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePG extends AppCompatActivity{
    private TextView textViewWelcome;
    public Button buttonPlay;
    public Button buttonProfile;
    public Button buttonStorage;
    public Button buttonExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_pg);
        textViewWelcome= findViewById(R.id.textViewWelcome);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonExam= findViewById(R.id.buttonExam);
        buttonProfile =findViewById(R.id.buttonProfile);
        buttonStorage = findViewById(R.id.buttonStorage);

        String email =getIntent().getStringExtra("email");
        String password= getIntent().getStringExtra("password");
        String name= getIntent().getStringExtra("name");
        textViewWelcome.setText("Welcome"+ name);
    }
    public void onClick(DialogInterface dialogInterface, int i){
        if(i==dialogInterface.BUTTON_POSITIVE){
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if(i==dialogInterface.BUTTON_NEGATIVE){
            dialogInterface.cancel();
        }
    }

    public void game(View view) {
        Intent intent = new Intent(this,TheGameActivity.class);
        startActivity(intent);
    }

    public void storage(View view) {
        Intent intent = new Intent(this,StorageActivity.class);
        startActivity(intent);
    }

    public void exams(View view) {
        Intent intent = new Intent(this,TheExamsActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }


}