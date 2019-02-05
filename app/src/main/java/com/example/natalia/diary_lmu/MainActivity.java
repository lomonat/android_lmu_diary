package com.example.natalia.diary_lmu;

import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import android.util.Patterns;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth mAuth;
    EditText mailSign, passwordSign;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");

        mAuth = FirebaseAuth.getInstance();
        mailSign = (EditText) findViewById(R.id.mailText);
        passwordSign = (EditText) findViewById(R.id.pwText);
        progressBar = (ProgressBar) findViewById(R.id.progressBarS);

        findViewById(R.id.rButton).setOnClickListener(this);
        findViewById(R.id.sButton).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent = new Intent(this.getBaseContext(), SendDataService.class);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }   */
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser() != null){
            finish();
         //   postData();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }

    }


    private void userLogin(){
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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();

                    startActivity(new Intent(MainActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rButton:
                finish();
                startActivity(new Intent(this, signUp.class));
                break;
            case R.id.sButton:
                userLogin();
                break;
        }
    }
}