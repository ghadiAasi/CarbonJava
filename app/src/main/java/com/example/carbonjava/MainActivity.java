package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail= findViewById(R.id.editTextEmailLogin);
        editTextPassword= findViewById(R.id.editTextPasswordLogin);
        editTextPasswordConform= findViewById(R.id.editTextPasswordLoginConfirm);

        buttonlogin= findViewById(R.id.buttonlogin);
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

        if (editTextPasswordConform != editTextPassword) {
            editTextPassword.setText("");
            editTextEmail.setText("");
            editTextPasswordConform.setText("");

            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("The confirmed password does not match the password");
            AlertDialog dialog= builder.create();
            dialog.show();
        } else {
            Intent intent = new Intent(this, WelcomePG.class);
            boolean finish = false;
            if (!editTextEmail.getText().toString().equals("")) {
                if (editTextEmail.getText().toString().length() > 4) {
                    if (editTextEmail.getText().toString().indexOf('@') != 0) {
                        if (editTextEmail.getText().toString().indexOf('.') != 0) {
                            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("email", editTextEmail.getText().toString());
                            intent.putExtra("email", editTextEmail.getText().toString());

                            editor.commit();

                            finish = true;
                        }
                    }
                }
            }
            if (!editTextPassword.getText().toString().equals("")) {
                if (editTextPassword.getText().toString().length() > 8) {
                    int nums = 0;
                    for (int i = 0; i < 9; i++) {
                        char c = (char) i;
                        if (editTextPassword.getText().toString().indexOf(c) != 0) {
                            nums++;
                        }
                    }
                    if (nums != 0) {
                        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password", editTextPassword.getText().toString());
                        editor.commit();
                        intent.putExtra("password", editTextPassword.getText().toString());
                    } else {
                        finish = false;
                    }
                }
            }
            if (finish) {
                startActivity(intent);
            }
            login2(email,password);
        }
    }
    public void login2(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i =new Intent(MainActivity.this, TheGameActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public boolean onLongClick(View view) {
        editTextPassword.setText("");
        editTextEmail.setText("");
        return true;
    }

    public void ForgotPass(View view) {
        Intent intent =new Intent(this,ForgotActivity.class);
        startActivity(intent);
    }
}