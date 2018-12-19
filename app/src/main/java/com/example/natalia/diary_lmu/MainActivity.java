package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");

        findViewById(R.id.rButton).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rButton:
                startActivity(new Intent(this, signUp.class));
                break;
        }
    }
}
