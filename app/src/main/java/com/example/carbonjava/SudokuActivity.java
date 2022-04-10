package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
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

public class SudokuActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private static long START_TIME_IN_MILLS =600000;
    private TextView mTextViewConutDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;

    private int wins =0;
    private TextView mistake;
    private TextView level;
    private String pic;
    private SudokuBoard gameBoard;
    private SudokuLogic gameBoardSudocku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        mTextViewConutDown =findViewById(R.id.clockSudoku);

        mistake = findViewById(R.id.Mistake);
        gameBoard =findViewById(R.id.sudokuBoard);
        gameBoardSudocku = gameBoard.getSudocku();



        level = findViewById(R.id.level);
        Intent intent = getIntent();
        String levelString = intent.getStringExtra("level");
        level.setText(levelString);

        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoardSudocku.showSudockuSee(levelString);
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
                START_TIME_IN_MILLS = 600000;
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
                    wins();
                }
                gameBoardSudocku.restartGame();
                gameBoardSudocku.showSudockuSee(level.getText().toString());
            }
        }.start();
        mTimerRunning=true;
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
        mTimerRunning=false;
        super.onDestroy();
    }

    public void BTNOne(View view){
        gameBoardSudocku.setNumberPos(1);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNTwo(View view){
        gameBoardSudocku.setNumberPos(2);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNThree(View view){
        gameBoardSudocku.setNumberPos(3);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNFour(View view){
        gameBoardSudocku.setNumberPos(4);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNFive(View view){
        gameBoardSudocku.setNumberPos(5);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }

    public void BTNSix(View view){
        gameBoardSudocku.setNumberPos(6);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNSeven(View view){
        gameBoardSudocku.setNumberPos(7);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNEight(View view){
        gameBoardSudocku.setNumberPos(8);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        gameBoard.invalidate();
    }
    public void BTNNine(View view){
        gameBoardSudocku.setNumberPos(9);
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
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
            wins();
        }
        mistake.setText("Mistakes "+this.gameBoardSudocku.getMistakes()+"/3");
        mTimeLeftInMillis = START_TIME_IN_MILLS;
        updateCountDownText();
        gameBoardSudocku.restartGame();
        gameBoardSudocku.showSudockuSee(level.getText().toString());
    }

    public void back(View view) {
        wins();
        Intent intent = new Intent(this,WelcomePG.class);
        startActivity(intent);
    }
    private void wins(){
        if(wins>3){
            takeScrennShot();
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void takeScrennShot() {
        Date date = new Date();
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
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        this.pic= Base64.encodeToString(arr, Base64.DEFAULT);
    }
    public void addScreenShot(String pic){
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://carbonjava-4211d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = firebaseDatabase.getReference("Users/"+user);
        String key = myRef.push().getKey();
        Item image=new Item(pic,"Sudoku ",key,level.getText().toString());

        myRef = firebaseDatabase.getReference("Users/"+user+"/"+key);
        myRef.setValue(image);
    }
}