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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomePG extends AppCompatActivity implements DialogInterface.OnClickListener {
    private Intent musicIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_pg);

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = firebaseDatabase.getReference("Users/" + user);

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
                case R.id.nav_smartgames:
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_report:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Rate this app");
                builder.setCancelable(false);
                builder.setNegativeButton("10/10",this);
                builder.setPositiveButton("it can be better",this);
                AlertDialog dialog=builder.create();
                dialog.show();
                builder.setMessage("thanks for rating");
                break;

            case R.id.action_mybests:
                intent=new Intent(this, ArrayListActivity1.class);
                startActivity(intent);
                break;

            case R.id.action_logout:
                AlertDialog.Builder builder2 =new AlertDialog.Builder(this);
                builder2.setMessage("are you sure?");
                builder2.setCancelable(false);
                builder2.setPositiveButton("yes",this);
                builder2.setNegativeButton("No", this);
                AlertDialog dialog2=builder2.create();
                dialog2.show();
                intent=new Intent(this, LogInActivity.class);
                startActivity(intent);
                break;

        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionItemSelected
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

}