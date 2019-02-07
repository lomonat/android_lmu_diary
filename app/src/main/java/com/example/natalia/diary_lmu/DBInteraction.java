package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DBInteraction  extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference ref;


    //FirebaseAuth user  = mAuth.getCurrentUser();
    String id = "797216763";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
     //   ref = database.getReference("Data/users/" + user.getUid());


    }
public void getData() {
    // Read from the database
    ref = database.getReference("Names/users/" + id);
    ref.child("Name");
    ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            Object value = dataSnapshot.getValue();
            Log.d("yes", (String) value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("no", "Failed to read value.", error.toException());
        }
    });
}

   /* database.child("notes").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            List notes = new ArrayList<>();
            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                Note note = noteDataSnapshot.getValue(Note.class);
                notes.add(note);
            }
            adapter.updateList(notes);
        } */
}
