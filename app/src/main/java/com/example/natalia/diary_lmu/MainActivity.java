package com.example.natalia.diary_lmu;

import android.app.Activity;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

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
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser() != null){
            finish();
            postData();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }

    }

    public void getData() throws IOException {
        System.out.println("test2");


       // URL url = new URL("https://api.telegram.org/bot594094944:AAHr-ucsrdRrS5wQraiHmGgog2vyCZ_9XXk/getUpdates");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.telegram.org/bot594094944:AAHr-ucsrdRrS5wQraiHmGgog2vyCZ_9XXk/getUpdates";

// Request a string response from the provided URL.
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", "test", error);
                    }
                }
        );
            // add it to the RequestQueue
                    queue.add(getRequest);


    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public static Boolean compareTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        System.out.println("Time" + dateFormat.format(date));
        return true;
        //int rnd = new Random().nextInt(array.length);
        // return array[rnd];
    }
    public void postData() {
        compareTime();
        RequestQueue queue = Volley.newRequestQueue(this);

        final String URL = "https://api.telegram.org/bot705197204:AAH3vR6vK938ftZPkzo4Q3mnRKtxylhzpEw/sendMessage";
        String[] greeting = {"Hi! How are you today?","Whats up?","Hello! How are you?", "Hi! Do you have some news?"};


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("chat_id", "325105554");
        params.put("text", getRandom(greeting));

        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        queue.add(request_json);


// add the request object to the queue to be executed
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