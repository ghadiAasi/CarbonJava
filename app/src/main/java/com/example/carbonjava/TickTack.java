package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

public class TickTack extends AppCompatActivity {
    private static final long STAR_TIME_IN_MILLIS = 600000;
    private CountDownTimer mCountTimer;

    private boolean mTimerRunning;
    private TicTacToeBrains tick = new TicTacToeBrains();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_tack);
        tick.setPlayer(1);
        tick.setComputer(2);
    }

    public void addPlayerO(View view) {
       //    tick.setPlayer(2);
        //    tick.setComputer(1);

    }

    public void home(View view) {
        Intent intent = new Intent(this,WelcomePG.class);
        startActivity(intent);
    }
    public void touch1(){

    }
}