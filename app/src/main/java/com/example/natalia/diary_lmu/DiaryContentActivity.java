package com.example.natalia.diary_lmu;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DiaryContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_content);

        Bundle bundle = this.getIntent().getExtras();
        String diary_date = bundle.getString("diary_date");

        TextView dateTextView = findViewById(R.id.day_time_tv);
        dateTextView.setText(diary_date);

        final ImageView testImageView = findViewById(R.id.firebaseTestImageView);

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("test_folder/test_image.jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(testImageView);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "load image successed",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "load image failed" + e,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

//        GlideApp.with(this /* context */)
//                .load(storageRef)
//                .into(findViewById(R.id.firebaseTestImageView));

    }
}
