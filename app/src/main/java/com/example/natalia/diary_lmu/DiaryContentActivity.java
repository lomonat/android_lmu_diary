package com.example.natalia.diary_lmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class DiaryContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_content);

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
//        Toast toast = Toast.makeText(getApplicationContext(),
//                        "name is " + name,
//                        Toast.LENGTH_SHORT);
//        toast.show();

    }

    private void testFirebase(){
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // ## Create a Reference

        // [START create_storage_reference]
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        // Points to the root reference
        storageRef = storage.getReference();

        // [END create_storage_reference]

        // [START create_child_reference]
        // Create a child reference
        // imagesRef now points to "images"
        StorageReference imagesRef = storageRef.child("images");
// Points to "images"
        imagesRef = storageRef.child("images");

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"
        StorageReference spaceRef = storageRef.child("images/space.jpg");
        // [END create_child_reference]

// Points to "images/space.jpg"
// Note that you can use variables to create child values
        String fileName = "space.jpg";
        spaceRef = imagesRef.child(fileName);

// File path is "images/space.jpg"
        String path = spaceRef.getPath();

// File name is "space.jpg"
        String name = spaceRef.getName();

// Points to "images"
        imagesRef = spaceRef.getParent();
    }
}
