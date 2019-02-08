package com.example.natalia.diary_lmu;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class DBInteraction  extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference ref;


    //FirebaseAuth user  = mAuth.getCurrentUser();
    String id = "797216763";
    String text = " ";
    public List<DayEntry> dayEntries = new ArrayList();

    public String getData(String period) {
  //  public  String getData(String period) {
        // Read from the database
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Data/users/" + id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial alue and again
                // whenever data at this location is updated.

               // if (period == "day") {
                   // dataForDay(dataSnapshot);

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
           /*  if(today == child.getKey()) {
                 parseToClass(child);
             } */
                       // parseToClass(child);

                        for (DataSnapshot item_snapshot : child.getChildren()) {


                            DayEntry dayEntry = item_snapshot.getValue(DayEntry.class);
                            Log.i("CHILD3", dayEntry.content);
                            Log.i("CHILD3", dayEntry.date);
                            text = text + dayEntry.date + ": " + dayEntry.content + System.lineSeparator();

                          //  dayEntries.add(dayEntry);
                          //     Log.i("CHILD311111", String.valueOf(dayEntries));

                        }


                    }
              //  }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("no", "Failed to read value.", error.toException());
            }
        });
        //return null;
       // return dayEntries;
        return text;

    }

    public void dataForDay(DataSnapshot data) {


        for (DataSnapshot child : data.getChildren()) {
           /*  if(today == child.getKey()) {
                 parseToClass(child);
             } */
            parseToClass(child);

        }

    }

    public List<DayEntry> parseToClass(DataSnapshot data) {
  //  public String parseToClass(DataSnapshot data) {

        for (DataSnapshot item_snapshot : data.getChildren()) {


            DayEntry dayEntry = item_snapshot.getValue(DayEntry.class);
            Log.i("CHILD3", dayEntry.content);
            Log.i("CHILD3", dayEntry.date);
            text = text + dayEntry.date + ": " + dayEntry.content + System.lineSeparator();

           // dayEntries.add(dayEntry);
         //   Log.i("CHILD311111", String.valueOf(dayEntries));

        }

        return dayEntries;
       // return text;

    }
}
