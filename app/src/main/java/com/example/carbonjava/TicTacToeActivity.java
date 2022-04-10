package com.example.carbonjava;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    private static long START_TIME_IN_MILLS =300000;
    private TextView mTextViewConutDown;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;

    private TextView Xwinning;
    private TextView Owinning;
    private int wins=0;
    private Button playAgainButton, homeButton;
    private TextView playerTurn;
    private TicTacToeBoard ticTacToeBoard;
    private String pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_tack);


        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(this);


        mTextViewConutDown = findViewById(R.id.clockTicTac);
        playerTurn =findViewById(R.id.textTicTac);
        playerTurn.setOnClickListener(this);

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(this);

        Xwinning = findViewById(R.id.WinningX);
        Owinning = findViewById(R.id.WinningY);

        Xwinning.setOnClickListener(this);
        Owinning.setOnClickListener(this);

        ticTacToeBoard.setUpGame(playAgainButton,homeButton,playerTurn,Xwinning,Owinning);
        startTimer();
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
                START_TIME_IN_MILLS =300000;
                wins++;
                wins();
                Xwinning.setText("0");
                Owinning.setText("0");
                ticTacToeBoard.resetGame();
                ticTacToeBoard.invalidate();
            }
        }.start();

    }

    private void updateCountDownText() {
        int minutes=(int)(mTimeLeftInMillis/1000)/60;
        int seconds =(int)(mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewConutDown.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        mCountDownTimer.cancel();
        super.onDestroy();
    }
    @Override
    public void onClick(View view) {
        if(view == playAgainButton){
            wins++;
            ticTacToeBoard.resetGame();
            ticTacToeBoard.invalidate();
        }
        if(view == homeButton){
            wins();
            Intent intent = new Intent(this, WelcomePG.class);
            startActivity(intent);
        }
    }
    private void wins(){
        if(wins>3){
            screenshot();
        }
    }
    private void screenshot(){
        String filename = Environment.getExternalStorageDirectory()+ "/ScreenShooter" + ".jpg";

        View root = getWindow().getDecorView();
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        root.setDrawingCacheEnabled(false);

        File file = new File(filename);
        file.getParentFile().mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

                Uri uri = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri,"image/*");
                startActivity(intent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            bitToString(bitmap);
            addScreenShot(pic);
    }

    public void bitToString(Bitmap bitmap){
        ByteArrayOutputStream baos =new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        this.pic= Base64.encodeToString(arr, Base64.DEFAULT);
    }
    public void addScreenShot(String pic){
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = firebaseDatabase.getReference("Users/"+user);
        String key = myRef.push().getKey();
        Item image=new Item(pic,"Tic Tac Toe",key,Xwinning.getText().toString()+","+Owinning.getText().toString());

        myRef = firebaseDatabase.getReference("Users/"+user+"/"+key);
        myRef.setValue(image);

    }
}