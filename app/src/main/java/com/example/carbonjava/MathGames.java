package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MathGames extends AppCompatActivity implements View.OnClickListener {
    private TextView level;
    private TextView question;
    private double answer;
    private EditText givenAnswer;
    private String givenAnswerS;
    private TextView levelMath;
    private String levelString;
    private int count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_games);
        level = findViewById(R.id.levelMath);
        Intent intent = getIntent();
        levelString = intent.getStringExtra("level");
        level.setText(levelString);

        levelMath = findViewById(R.id.levelMathN);
        question = findViewById(R.id.question);
        givenAnswer =findViewById(R.id.answer);
        levelMath.setText("count");

        makeGame();
    }
    public void makeGame(){
        double answer = 0;
        String question = "";
        int x = (int) (Math.random() * 100)*count;
        int y = (int) (Math.random() * 100)*count;
        if(levelString.equals("easy")) {
            if (((int) ((Math.random()) * 19)) % 6 == 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    answer = x / y;
                    question = (x + " " + "/" + " " + y + " =?");
                }

            } else if (((int) ((Math.random()) * 19)) % 5 != 0) {
                answer = y * x;
                question = (x + " " + "*" + " " + y + " =?");
            } else if (((int) ((Math.random()) * 19)) % 3 != 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    answer = x - y;
                    question = (x + " " + "-" + " " + y + " =?");
                } else {
                    answer = y - x;
                    question = (x + " " + "-" + " " + y + " =?");
                }
            } else {
                answer = y + x;
                question = (x + " " + "+" + " " + y + " =?");
            }
        }
        else{
            int z = (int) (Math.random() * 100)*count;
            if (((int) ((Math.random()) * 19)) % 6 == 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    if (((int) ((Math.random()) * 19)) % 2 != 0) {

                    }
                    else{

                    }
                } else {
                    answer = y / x;
                    question = (y + " " + "/" + " " + x + " =?");
                }
            } else if (((int) ((Math.random()) * 19)) % 5 != 0) {
                answer = y * x;
                question = (x + " " + "*" + " " + y + " =?");
            } else if (((int) ((Math.random()) * 19)) % 3 != 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    answer = x - y;
                    question = (x + " " + "-" + " " + y + " =?");
                } else {
                    answer = y - x;
                    question = (x + " " + "-" + " " + y + " =?");
                }
            } else {
                answer = y + x;
                question = (x + " " + "+" + " " + y + " =?");
            }
        }
        this.answer = answer;
        this.question.setText(question);
    }

    @Override
    public void onClick(View view) {

    }

    public void checkGame(View view) {
        givenAnswerS = givenAnswer.getText().toString();
        String answerS = ""+this.answer+"";
        if(answerS.equals(givenAnswerS)){
            makeGame();
            count++;
        }
        else{
            makeGame();
            //Todo add a message
        }
    }
}