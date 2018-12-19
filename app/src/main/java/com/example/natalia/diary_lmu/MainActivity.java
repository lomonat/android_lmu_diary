package com.example.natalia.diary_lmu;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        System.out.println("database" +  database);
        DatabaseReference myRef = database.getReference("test");
        System.out.println("databaseref");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // data available in snapshot.value()
                System.out.println("snapshot " + snapshot.child("test1").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              System.out.println("Errorrr " +  databaseError);
            }
        });


        //  myRef.setValue("Hello, World!");
    }
}
