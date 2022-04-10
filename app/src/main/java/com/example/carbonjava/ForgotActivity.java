package com.example.carbonjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private EditText mailForgot;
    private EditText nameForgot;
    public String mail;
    public String name;

    private FirebaseAuth mAuth;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        mAuth = FirebaseAuth.getInstance();

        mailForgot= findViewById(R.id.editTextEmailLoginForgot);
        nameForgot= findViewById(R.id.editTextNameForgot);


        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        String mail= settings.getString("email"," ");
        if(!mail.equals(""))
            mailForgot.setText(mail);
    }
    public void onClick(DialogInterface dialogInterface, int i){
    }
    public void send(View view) {
            mail = mailForgot.getText().toString();
            name = nameForgot.getText().toString();
            Intent intent;
        if( name.equals(mAuth.getCurrentUser().getDisplayName()) &&(mAuth.getCurrentUser().getEmail()).equals(mail)) {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("you will receive a mail on that account ");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",  this);
            AlertDialog dialog= builder.create();
            dialog.show();
        }
        else{
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("You don't have an account");
            builder.setCancelable(false);
            builder.setPositiveButton("Sign up",  this);
            builder.setNegativeButton("OK",  this);
            AlertDialog dialog= builder.create();
            dialog.show();
                intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
        }
        }

}