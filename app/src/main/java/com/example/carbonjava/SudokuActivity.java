package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class SudokuActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLS =300000;
    private TextView mTextViewConutDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;

    private int wins =0;
    private TextView level;
    private SudokuBoard gameBoard;
    private Sudocku gameBoardSudocku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        mTextViewConutDown =findViewById(R.id.clockSudoku);

        gameBoard =findViewById(R.id.sudokuBoard);
        gameBoardSudocku = gameBoard.getSudocku();

        level = findViewById(R.id.level);
        Intent intent = getIntent();
        String levelString = intent.getStringExtra("level");
        level.setText(levelString);

        gameBoardSudocku.showSudockuSee();
        startTimer();
        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                boolean full = true;
                for(int r =0;r<9;r++){
                    for(int c=0;c<9;c++){
                        if(gameBoardSudocku.getSudoku()[r][c] == 0){
                            full=false;
                        }
                    }
                }
                if(full){
                    wins++;
                }
                gameBoardSudocku.restartGame();
                gameBoardSudocku.showSudockuSee();
            }
        }.start();
        mTimerRunning=true;

    }

    private void updateCountDownText() {
        int minutes=(int)(mTimeLeftInMillis/1000)/60;
        int seconds =(int)(mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d,%02d",minutes,seconds);
        mTextViewConutDown.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        mCountDownTimer.cancel();
        mTimerRunning=false;
        super.onDestroy();
    }

    public void BTNOne(View view){
        gameBoardSudocku.setNumberPos(1);
        gameBoard.invalidate();
    }
    public void BTNTwo(View view){
        gameBoardSudocku.setNumberPos(2);
        gameBoard.invalidate();
    }
    public void BTNThree(View view){
        gameBoardSudocku.setNumberPos(3);
        gameBoard.invalidate();
    }
    public void BTNFour(View view){
        gameBoardSudocku.setNumberPos(4);
        gameBoard.invalidate();
    }
    public void BTNFive(View view){
        gameBoardSudocku.setNumberPos(5);
        gameBoard.invalidate();
    }

    public void BTNSix(View view){
        gameBoardSudocku.setNumberPos(6);
        gameBoard.invalidate();
    }
    public void BTNSeven(View view){
        gameBoardSudocku.setNumberPos(7);
        gameBoard.invalidate();
    }
    public void BTNEight(View view){
        gameBoardSudocku.setNumberPos(8);
        gameBoard.invalidate();
    }
    public void BTNNine(View view){
        gameBoardSudocku.setNumberPos(9);
        gameBoard.invalidate();
    }

    public void restart(View view) {
        boolean full=true;
        for(int r =0;r<9;r++){
            for(int c=0;c<9;c++){
                if(gameBoardSudocku.getSudoku()[r][c] == 0){
                    full=false;
                }
            }
        }
        if(full){
            wins++;
        }
        mTimeLeftInMillis = START_TIME_IN_MILLS;
        updateCountDownText();
        gameBoardSudocku.restartGame();
        gameBoardSudocku.showSudockuSee();
    }
    //class SolveBoardThreat implements Runnable{
      //  @Override
        //public void run() {
          //  gameBoardSudocku.makeSudoku(gameBoard);
        //}

    //}
}