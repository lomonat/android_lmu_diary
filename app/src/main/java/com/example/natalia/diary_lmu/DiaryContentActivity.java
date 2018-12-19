package com.example.natalia.diary_lmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class DiaryContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_content);

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        Toast toast = Toast.makeText(getApplicationContext(),
                        "name is " + name,
                        Toast.LENGTH_SHORT);
        toast.show();
    }
}
