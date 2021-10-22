package com.example.carbonjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonsingup;
    private Button buttonguest;
    private Button buttonlogin;
    public String email;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail= findViewById(R.id.editTextEmailLogin);
        editTextPassword= findViewById(R.id.editTextPasswordLogin);
        buttonguest= findViewById(R.id.buttonguest);
        buttonlogin= findViewById(R.id.buttonlogin);
        buttonsingup= findViewById(R.id.buttonsingup);

        buttonlogin.setOnLongClickListener(this);
        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        String email= settings.getString("email"," ");
        String password = settings.getString("password","");
        if(!email.equals("") && !password.equals("")){
            editTextPassword.setText(password);
            editTextEmail.setText(email);
        }
    }
    public void login(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        Intent intent = new Intent(this,WelcomePG.class);
        boolean finish = false;
        if(!editTextEmail.getText().toString().equals("")){
            if(editTextEmail.getText().toString().length()>4){
                if(editTextEmail.getText().toString().indexOf('@')!=0){
                    if(editTextEmail.getText().toString().indexOf('.')!=0){
                        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("email",editTextEmail.getText().toString());
                        editor.commit();
                        intent.putExtra("email",editTextEmail.getText().toString());
                        finish=true;
                    }
                }
            }
        }
        if(!editTextPassword.getText().toString().equals("")){
            if(editTextPassword.getText().toString().length()>8){
                int nums=0;
                for(int i=0; i<9;i++) {
                    char c =(char)i;
                    if (editTextPassword.getText().toString().indexOf(c) !=0) {
                        nums++;
                    }
                }
                if (nums !=0) {
                    SharedPreferences settings =getSharedPreferences("settings",MODE_PRIVATE);
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("password",editTextPassword.getText().toString());
                    editor.commit();
                    intent.putExtra("password", editTextPassword.getText().toString());
                }
                else{finish=false;}
            }
        }
        if(finish){
            startActivity(intent);
        }
    }


    public void signup(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void guest(View view) {

        Intent intent = new Intent(this,WelcomePG.class);
        SharedPreferences settings =getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("email"," ");
        editor.commit();
        intent.putExtra("email"," ");
        editor.putString("password"," ");
        editor.commit();
        intent.putExtra("password", " ");
        startActivity(intent);
    }
    @Override
    public boolean onLongClick(View view) {
        editTextPassword.setText("");
        editTextEmail.setText("");
        return true;
    }
}