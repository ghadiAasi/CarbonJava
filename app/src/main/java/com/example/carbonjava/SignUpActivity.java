package com.example.carbonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity implements View.OnLongClickListener {
    private static final String TAG = "FIREBASE";
    private EditText editTextName;
    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private FirebaseAuth mAuth;

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail =findViewById(R.id.editTextEmailSignin);
        editTextPassword =findViewById(R.id.editTextPasswordSignin);
        editTextUserName =findViewById(R.id.editTextUserNameSignin);
        editTextName =findViewById(R.id.editTextNameSignin);
        editTextPhone = findViewById(R.id.editTextPhoneSignin);
    }

    public void welcome(View view) {
        signup(editTextEmail.getText().toString(), editTextPassword.getText().toString(), editTextName.getText().toString());
    }

    public void signup(String name,String password, String fullname){
        mAuth.createUserWithEmailAndPassword(name, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i =new Intent(SignUpActivity.this, WelcomePG.class);
                            startActivity(i);

                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullname).build();
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener((userUpdateTask)->{
                                if(userUpdateTask.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "User created successfully!",Toast.LENGTH_SHORT).show();
                                    SignUpActivity.this.finish();
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Email already exists.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onLongClick(View view) {
        editTextUserName.setText("");
        editTextName.setText("");
        editTextPhone.setText("");
        editTextPassword.setText("");
        editTextEmail.setText("");
        return true;
    }

    public void loginFsign(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}