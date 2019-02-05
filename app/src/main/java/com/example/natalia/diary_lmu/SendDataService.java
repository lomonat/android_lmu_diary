
/*
Service runs in background even if your application is in background but
 it always runs on main thread because of which I created a separate thread to perform your background operations.:

Service is started as sticky one as even if because of
any reason your service got destroyed in background it will automatically get restarted

https://developer.android.com/guide/components/services.html

 */

package com.example.natalia.diary_lmu;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SendDataService extends Service {
    private final LocalBinder mBinder = new LocalBinder();
    protected Handler handler;

    public class LocalBinder extends Binder {
        public SendDataService getService() {
            return SendDataService .this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postData();
                    }
                }, 0, 24*60*60*1000);
            }
        });
       // return android.app.Service.START_STICKY;
        return START_STICKY;
    }

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public void postData() {
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
}