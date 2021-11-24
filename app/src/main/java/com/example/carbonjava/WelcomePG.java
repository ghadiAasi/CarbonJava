package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomePG extends AppCompatActivity implements DialogInterface.OnClickListener {
    private TextView textViewWelcome;
    public Button buttonPlay;
    public Button buttonProfile;
    public Button buttonStorage;
    public Button buttonExam;
    private Intent musicIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_pg);
     //   textViewWelcome= findViewById(R.id.textViewWelcome);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonExam= findViewById(R.id.buttonExams);
        buttonProfile =findViewById(R.id.buttonProfile);
        buttonStorage = findViewById(R.id.buttonStorage);

        String email =getIntent().getStringExtra("email");
        String password= getIntent().getStringExtra("password");
        String name= getIntent().getStringExtra("name");
        textViewWelcome.setText("Welcome"+ name);

        musicIntent = new Intent(this,MusicService.class);
        startService(musicIntent);
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
    public void onBackPressed(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES",this);
        builder.setNegativeButton("NO",this);
        AlertDialog dialog= builder.create();
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_one,menu);
        //bring xml and put it on the activity
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingsMenu:
                Toast.makeText(WelcomePG.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exitMenu:
                // closeApplication();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void game(View view) {
        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        int level  = settings.getInt("level", 0);

        Intent intent = new Intent(this,TheGameActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    public void storage(View view) {
        Intent intent = new Intent(this,StorageActivity.class);
        startActivity(intent);
    }

    public void exams(View view) {
        Intent intent = new Intent(this,InrollVideoActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void back(View view){

    }


}