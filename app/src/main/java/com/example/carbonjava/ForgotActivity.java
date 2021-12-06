package com.example.carbonjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private static final String TAG = "FIREBASE";
    private EditText mailForgot;
    private EditText phoneForgot;
    private Button buttonsend;
    public String mail;
    public String phone;

    private FirebaseAuth mAuth;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        mAuth = FirebaseAuth.getInstance();

        mailForgot= findViewById(R.id.editTextEmailLoginForgot);
        phoneForgot= findViewById(R.id.editTextTextphone);


        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        String mail= settings.getString("email"," ");
        String phone = settings.getString("phone","");
        if(!mail.equals(""))
            mailForgot.setText(mail);
        if(!phone.equals("")) {
            phoneForgot.setText(phone);
        }
    }
    public void onClick(DialogInterface dialogInterface, int i){
        if(i==dialogInterface.BUTTON_POSITIVE){
            dialogInterface.cancel();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
    public void send(View view) {
            mail = mailForgot.getText().toString();
            phone = phoneForgot.getText().toString();



        if( phone.equals("") && !mail.equals("")) {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("you will receive a mail on that account if you have a Carbon java account");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",  this);
            AlertDialog dialog= builder.create();
            dialog.show();

        }
        else if(mail == "" && phone != "") {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("you will receive a text message on that number if you have an account");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",  this);
            AlertDialog dialog= builder.create();
            dialog.show();

        }
        else{
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("ELSE");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",  this);
            AlertDialog dialog= builder.create();
            dialog.show();
        }


        }
    }