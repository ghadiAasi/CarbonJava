package com.example.carbonjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MathGames extends AppCompatActivity implements DialogInterface.OnClickListener{
    private static long START_TIME_IN_MILLS =100000;
    private TextView mTextViewConutDown;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;

    private int whichButton;
    private Button BTNFirst;
    private Button BTNSecond;
    private Button BTNThird;
    private Button BTNFourth;

    private TextView level;
    private TextView question;
    private double answer;
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

        mTextViewConutDown = findViewById(R.id.clockMath);
        levelMath = findViewById(R.id.levelMathN);
        question = findViewById(R.id.question);

        String l= ""+(this.count-1)+"";
        levelMath.setText(l);

        BTNFirst=findViewById(R.id.BTNFirst);
        BTNSecond=findViewById(R.id.BTNSecond);
        BTNThird=findViewById(R.id.BTNThird);
        BTNFourth=findViewById(R.id.BTNFourth);

        startTimer();
        makeGame();
        setAnswers();
    }

    private void setAnswers() {
        String l= ""+(this.count-1)+"";
        levelMath.setText(l);

        if(((int) ((Math.random()) * 19)) % 6 == 0) {
            BTNFirst.setText(""+this.answer+"");
            whichButton =1;
            BTNSecond.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNThird.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNFourth.setText(""+((int) ((Math.random()) * 1000))+".0");
        }else if(((int) ((Math.random()) * 19)) % 5 == 0){
            BTNFirst.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNSecond.setText(""+this.answer+"");
            whichButton =2;
            BTNThird.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNFourth.setText(""+((int) ((Math.random()) * 1000))+".0");
        }else if(((int) ((Math.random()) * 19)) % 2 == 0){
            BTNFirst.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNSecond.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNThird.setText(""+this.answer+"");
            whichButton =3;
            BTNFourth.setText(""+((int) ((Math.random()) * 1000))+".0");
        }else{
            BTNFirst.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNSecond.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNThird.setText(""+((int) ((Math.random()) * 1000))+".0");
            BTNFourth.setText(""+this.answer+"");
            whichButton =4;
        }
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
                START_TIME_IN_MILLS = 100000;
                makeGame();
                setAnswers();
                count=1;
            }

        }.start();
    }

    private void updateCountDownText() {
        int minutes=(int)(mTimeLeftInMillis/1000)/60;
        int seconds =(int)(mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewConutDown.setText(timeLeftFormatted);
    }

    public void makeGame(){
        double answer = 0;
        String question = "";
        int x = (int) (Math.random() * 100)*count+1;
        int y = (int) (Math.random() * 10)*count+1;
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
                    question = (y + " " + "-" + " " + x + " =?");
                }
            } else {
                answer = y + x;
                question = (y + " " + "+" + " " + x + " =?");
            }
        }
        else{
            int z = (int) (Math.random() * 100)*count+1;
            if (((int) ((Math.random()) * 19)) % 6 == 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    if (((int) ((Math.random()) * 19)) % 2 != 0) {
                        answer = x/z +y;
                        question = (x + "  / "  + z +" + " + y +  " =?");
                    }
                    else{
                        answer = z/y +x;
                        question = (z + "  / " + y + " + "+x+" =?");
                    }
                } else {
                    answer = y*x*z;
                    question = (y + " * " + x + " * " + z +" =?");
                }
            } else if (((int) ((Math.random()) * 19)) % 5 != 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    answer = (z * x)/y;
                    question = (z + " * " + x + " / "+ y+ " =?");
                } else {
                    answer = (z*y)/x;
                    question = (z + " * " + y + " / "+ x+ " =?");
                }
            } else if (((int) ((Math.random()) * 19)) % 3 != 0) {
                if (((int) ((Math.random()) * 19)) % 2 != 0) {
                    answer = (x - y)/z;
                    question = (x + " " + "-" + " " + y + " / "+z+" =?");
                } else {
                    answer = (x-y)*z;
                    question = (x + " - " + y + " * "+ z+ " =?");
                }
            } else {
                answer = z + y + x;
                question = (x + " " + "+" +" "+ z + " " + y + " =?");
            }
        }

        this.answer = answer;
        this.question.setText(question);
    }

    public void continueM(View view) {
        if(START_TIME_IN_MILLS == 0){
            START_TIME_IN_MILLS = 100000;
        }
        count=1;
        makeGame();
        setAnswers();
    }

    public void back(View view) {
        Intent intent = new Intent(this, SmartGamesFragment.class);
        startActivity(intent);
    }

    public void BTNA(View view) {
        if(view == BTNFirst && whichButton ==1){
            count++;
            String l= ""+(this.count-1)+"";
            levelMath.setText(l);
            makeGame();
            setAnswers();
        }
        else if(view == BTNSecond && whichButton ==2){
            count++;
            String l= ""+(this.count-1)+"";
            levelMath.setText(l);
            makeGame();
            setAnswers();
        }
        else if(view == BTNThird && whichButton ==3){
            count++;
            String l= ""+(this.count-1)+"";
            levelMath.setText(l);
            makeGame();
            setAnswers();
        }
        else if(view == BTNFourth && whichButton ==4){
            count++;
            String l= ""+(this.count-1)+"";
            levelMath.setText(l);
            makeGame();
            setAnswers();
        }
        else {
            count = 1;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("wrong, think faster and better");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", this);
            AlertDialog dialog = builder.create();
            dialog.show();
            makeGame();
            setAnswers();
        }
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}