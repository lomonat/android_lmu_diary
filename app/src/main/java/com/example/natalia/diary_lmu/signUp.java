package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signUp extends AppCompatActivity implements View .OnClickListener{

    EditText mailSign, passwordSign;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.sButtonSign).setOnClickListener(this);
        mailSign = (EditText) findViewById(R.id.mailTextSign);
        passwordSign = (EditText) findViewById(R.id.pwTextSign);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    private void registerUser(){
        String email = mailSign.getText().toString().trim();
        String password = passwordSign.getText().toString().trim();

       if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           mailSign.setError("correct E-Mail is required");
           mailSign.requestFocus();
           return;
       }

        if(email.isEmpty()){
            mailSign.setError("E-Mail is required");
            mailSign.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordSign.setError("Password is required");
            passwordSign.requestFocus();
            return;
        }

        if(password.length() < 6){
            passwordSign.setError("Password needs more than 6 signs");
            passwordSign.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){

                    finish();
                    Toast.makeText(getApplicationContext(), "User registered successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(signUp.this, ProfileActivity.class));

                } else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sButtonSign:
                registerUser();
                break;
        }
    }
}
