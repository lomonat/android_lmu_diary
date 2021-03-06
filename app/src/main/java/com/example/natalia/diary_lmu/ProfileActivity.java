package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.URI;
import java.security.KeyStore;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    EditText userName;
    EditText telegramId;
    ImageView userPic;
    Uri imageUri;
    ProgressBar progressBar;
    String profileImageUrl;
    TextView textView;
    TextView textVeri;
    TextView notify;
    FirebaseUser user;
    private static final int CHOOSE_IMAGE = 101;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;

    private String tempId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        userName = findViewById(R.id.editTextP);
        telegramId = findViewById(R.id.editTextTelegram);
        userPic = findViewById(R.id.imageViewP);
        progressBar = findViewById(R.id.progressBarP);
        textView = findViewById(R.id.textViewP);
        textVeri = findViewById(R.id.textViewVeri);
        notify = findViewById(R.id.textViewNotify);
        user  = mAuth.getCurrentUser();

        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showImageChooser();
            }
        });

        findViewById(R.id.buttonSaveP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        findViewById(R.id.imageButtonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(ProfileActivity.this, "You Logged Out Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        if(notify == null){
            Toast.makeText(this, "IM NULL", Toast.LENGTH_SHORT).show();
        }

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, NotificationActivity.class));
            }
        });

        loadUserInformation();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }

       if(!user.isEmailVerified()) {
           user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   Toast.makeText(ProfileActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
               }
           });
       }

    }

    private void loadUserInformation() {

        if (user != null) {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child(user.getUid()+ "/profilepics/"+user.getUid()+"profileimage.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(userPic);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "load image successed",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    profileImageUrl = storageRef.child(user.getUid()+ "/profilepics/"+user.getUid()+"profileimage.jpg").getDownloadUrl().toString();

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

            if (user.getDisplayName() != null) {
                userName.setText(user.getDisplayName());
            }

            if(user.isEmailVerified()){
                textVeri.setText("Your email is verified.");
            }else{
                textVeri.setText("Your email is not verified (Click to Verify).");
                textVeri.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ProfileActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
            
            loadTelegramId();
        }
    }

    private void loadTelegramId() {
        DatabaseReference ref = database.getReference("TelegramIds/user/" + user.getUid() + "/TelegramId");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null) {
                    telegramId.setText(dataSnapshot.getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "The read failed: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getData() {
        // Read from the database
        DatabaseReference ref = database.getReference("Names/users/" + user.getUid());

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

    private void saveUserInformation(){
        String name;
        String id;
        if(userName.getText() != null && telegramId.getText()!= null ) {
            name = userName.getText().toString();
            id = telegramId.getText().toString();

            if (name.isEmpty()) {
                userName.setError("Name required");
                userName.requestFocus();
                return;
            }

            if (id.isEmpty()) {
                telegramId.setError("The Telegram ID required");
                telegramId.requestFocus();
                return;
            }

            if (uploadId(id)) {
                telegramId.setError("The Telegram ID is not correct");
                telegramId.requestFocus();
                return;
            }

        }else{
            name = "Name";
            id = "TelegramID";
        }
        if(user != null && profileImageUrl != null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(name).setPhotoUri(Uri.parse(profileImageUrl)).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ProfileActivity.this, "Your profile is updated", Toast.LENGTH_SHORT).show();
                       if(user.isEmailVerified()) {
                           finish();
                           Bundle bundle = new Bundle();
                           bundle.putString("userName", user.getDisplayName());
                           Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                           intent.putExtras(bundle);
                           startActivity(intent);
                       }
                    }
                }
            });
        }

    }

    private boolean uploadId(String id) {
            String myID = id;
       if( checkTelegramId(myID)) {

           ref = database.getReference("TelegramIds/user");

           DatabaseReference telegramRef = ref.child("" + user.getUid());

           telegramRef.child("TelegramId").setValue(id);
           return false;
       }
       return true;
    }

    private boolean checkTelegramId(String id) {
        String mxID = id;
        //cheking if the telegram ID is correkt
        if(id.length() < 9){
            telegramId.setError("The Telegram ID consists of 9 Numbers");
            telegramId.requestFocus();
            return false;
        }

        DatabaseReference ref = database.getReference("Names/users/" + mxID + "/Name");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null) {
                    tempId = dataSnapshot.getValue().toString();
                }else{
                    tempId = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "The read failed: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        if(tempId == null){
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
          imageUri =  data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                userPic.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }

    private void  uploadImageToFirebaseStorage(){

        FirebaseUser user = mAuth.getCurrentUser();

        StorageReference profileImageRef = FirebaseStorage.getInstance().getReference(user.getUid()+ "/profilepics/"+user.getUid()+"profileimage.jpg");

        if(imageUri != null){
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    profileImageUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
