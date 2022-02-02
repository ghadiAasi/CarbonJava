package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WelcomePG extends AppCompatActivity implements DialogInterface.OnClickListener {
    private TextView textViewWelcome;
    public Button buttonPlay;
    public Button buttonExam;
    private Intent musicIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_pg);
     //   textViewWelcome= findViewById(R.id.textViewWelcome);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonExam= findViewById(R.id.buttonTicktack);

        String email =getIntent().getStringExtra("email");
        String password= getIntent().getStringExtra("password");
        String name= getIntent().getStringExtra("name");

        musicIntent = new Intent(this,MusicService.class);
        startService(musicIntent);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GamesFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_game:
                    selectedFragment = new GamesFragment();
                    break;
                case R.id.nav_chat:
                    selectedFragment = new SmartGamesFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

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
        //SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        //int level  = settings.getInt("level", 0);

        Intent intent = new Intent(this,ShotsGame.class);
        //intent.putExtra("level", level);
        startActivity(intent);
    }
    public void tickTack(View view) {
        Intent intent = new Intent(this,TickTack.class);
        startActivity(intent);
    }
}