package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class SudokuActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLS =600000;
    private TextView mTextViewConutDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;
    private TextView level;
    private String levelString;
    private SudokuBoard gameBoard;
    private Sudocku gameBoardSudocku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        gameBoard =findViewById(R.id.sudokuBoard);
        gameBoardSudocku = gameBoard.getSudocku();

        level = findViewById(R.id.level);
        Intent intent = getIntent();
        levelString = intent.getStringExtra("level");
        level.setText(levelString);
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
}