package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnLongClickListener {
    private static final String TAG = "FIREBASE";
    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordConform;
    private Button buttonlogin;
    public String email;
    public String password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connects between the values here and

        Intent notifyIntent = new Intent(this,NotificationReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail= findViewById(R.id.editTextEmailLogin);
        editTextPassword= findViewById(R.id.editTextPasswordLogin);
        editTextPasswordConform= findViewById(R.id.editTextPasswordLoginConfirm);

        buttonlogin = findViewById(R.id.buttonlogin);
        buttonlogin.setOnLongClickListener(this);

        SharedPreferences settings = getSharedPreferences("settings",MODE_PRIVATE);
        String email= settings.getString("email"," ");
        String password = settings.getString("password","");
        if(!email.equals("") && !password.equals("")){
            editTextPassword.setText(password);
            editTextEmail.setText(email);
            editTextPasswordConform.setText(password);
        }
    }

    public void login(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email", editTextEmail.getText().toString());
        editor.putString("password", editTextPassword.getText().toString());

        editor.apply();
        if (!editTextPasswordConform.getText().toString().equals(editTextPassword.getText().toString())) {
            editTextEmail.setText("");
            editTextPassword.setText("");
            editTextPasswordConform.setText("");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The confirmed password does not match the password");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (email != null && password != null) {
            login(email, password);
        }
    }
    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(LogInActivity.this,WelcomePG.class);
                            Toast.makeText(LogInActivity.this, "Authentication successed.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else {
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            editTextPasswordConform.setText("");
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    @Override
    public boolean onLongClick(View view) {
        editTextPassword.setText("");
        editTextEmail.setText("");
        editTextPasswordConform.setText("");
        return true;
    }

    public void ForgotPass(View view) {
        Intent intent = new Intent(this,ForgotActivity.class);
        startActivity(intent);
    }
}