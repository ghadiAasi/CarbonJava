package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private Button buttonSigned;

    public String name;
    public String userName;
    public String phone;
    public String email;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail =findViewById(R.id.editTextEmailSignin);
        editTextPassword =findViewById(R.id.editTextPasswordSignin);
        editTextUserName =findViewById(R.id.editTextUserNameSignin);
        editTextName =findViewById(R.id.editTextNameSignin);
        editTextPhone = findViewById(R.id.editTextPhoneSignin);
        buttonSigned = findViewById(R.id.buttonSigned);

        Intent intent = new Intent(this,WelcomePG.class);
        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email",editTextEmail.getText().toString());
        editor.commit();
        intent.putExtra("email",editTextEmail.getText().toString());
    }

    public void welcome(View view) {
    }
}