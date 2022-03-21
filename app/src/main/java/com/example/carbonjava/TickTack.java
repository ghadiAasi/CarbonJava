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
import android.widget.ImageView;
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

public class TickTack extends AppCompatActivity implements View.OnClickListener {

    private TextView Xwinning;
    private TextView Owinning;
    private ImageView image;
    private Button playAgainButton, homeButton;
    private TextView playerTurn;
    private TickTackToeBoard ticTacToeBoard;
    private String pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_tack);

        Button button = findViewById(R.id.screenshot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenshot();
            }
        });

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(this);

        playerTurn =findViewById(R.id.textTicTac);
        playerTurn.setOnClickListener(this);

        image = findViewById(R.id.imageView);

        homeButton =findViewById(R.id.homeButton);
        homeButton.setOnClickListener(this);

        Xwinning = findViewById(R.id.WinningX);
        Owinning = findViewById(R.id.WinningY);

        Xwinning.setOnClickListener(this);
        Owinning.setOnClickListener(this);

        ticTacToeBoard.setUpGame(playAgainButton,homeButton,playerTurn,Xwinning,Owinning);
    }

    @Override
    public void onClick(View view) {

        if(view == playAgainButton){
            ticTacToeBoard.resetGame();
            ticTacToeBoard.invalidate();
        }
        if(view == homeButton){
            Intent intent = new Intent(this,WelcomePG.class);
            startActivity(intent);
        }
    }
    private void screenshot(){
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
            image.setImageBitmap(bitmap);
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
        Item image=new Item(pic,"Tic Tac Toe",key,0);
        myRef = firebaseDatabase.getReference("Users/"+user+"/"+key);
        myRef.setValue(image);
    }
}