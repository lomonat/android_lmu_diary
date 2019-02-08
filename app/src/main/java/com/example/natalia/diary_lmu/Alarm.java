package com.example.natalia.diary_lmu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

import static android.os.PowerManager.*;


public class Alarm extends BroadcastReceiver {
    //the method will be fired when the alarm is triggerred
    @Override
    public void onReceive(Context context, Intent intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarmBelal", "Alarm just fired");
        Toast.makeText(context, "This is my Notification", Toast.LENGTH_SHORT).show();
        postData(context);
    }

    public void postData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        final String URL = "https://api.telegram.org/bot692281507:AAERP60yDfavnA95RG5JkfEaftSrPo5LcBk/sendMessage";
        String[] greeting = {"Hi! How are you today?","Whats up?","Hello! How are you?", "Hi! Do you have some news?"};


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("chat_id", "797216763");
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

}