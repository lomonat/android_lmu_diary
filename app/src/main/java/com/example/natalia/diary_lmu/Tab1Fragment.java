package com.example.natalia.diary_lmu;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.ContentResolver;
import android.graphics.Color;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Tab1Fragment extends Fragment {

    private Context mContext = null;
    private TextView dateTextView = null;
    private ImageView view1 = null;
    private TextView view2 = null;
    private RelativeLayout layout = null;

    Uri imageUri;
    ProgressBar progressBar;
    String view1Url;
    private static final int CHOOSE_IMAGE = 101;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initialFirebase();

        layout = new RelativeLayout(getActivity());
        mContext = getActivity();

        ScrollView scroller = new ScrollView(getActivity());

//        TextView text1 = new TextView(getActivity());
//        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                4, getActivity().getResources().getDisplayMetrics());
//        text1.setPadding(padding, padding, padding, padding);
//        text1.setText("Hello");
//
//        TextView text2 = new TextView(getActivity());
//        text2.setText("22222");
//
//        scroller.addView(text2);
        addDateTextView();
        addView1();
        addView2();
        scroller.addView(layout);

        return scroller;
//        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    void initialFirebase(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressBar = getActivity().findViewById(R.id.progressBarP);
        user  = mAuth.getCurrentUser();
    }

    private void addDateTextView(){

        dateTextView =  new TextView(mContext);
//        dateTextView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        dateTextView.setHeight(48);

        dateTextView.setBackgroundColor(Color.parseColor("#0F4C3A"));
        dateTextView.setTextColor(Color.WHITE);
        dateTextView.setText("08-Feb-19");

        dateTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = 120;
        dateTextView.setLayoutParams(params);
        layout.addView(dateTextView);
    }

    private void addView1() {
        view1 = new ImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dateTextView.setId(R.id.datetextview);
        params.addRule(RelativeLayout.BELOW, R.id.datetextview);
        view1.setLayoutParams(params);

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child(user.getUid()+ "/diarypics/"+user.getUid()+"_"+dateTextView.getText()+"_"+"diaryimage.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(view1);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "load image successed",
                        Toast.LENGTH_SHORT);
                toast.show();
                view1Url = storageRef.child(user.getUid()+ "/diarypics/"+user.getUid()+"_"+dateTextView.getText()+"_"+"diaryimage.jpg").getDownloadUrl().toString();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Picasso.get().load(R.drawable.food_simple).into(view1);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "load image failed" + e,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });
        layout.addView(view1);

    }



    private void addView2() {
        view2 = new TextView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        view1.setId(R.id.textview);
        params.addRule(RelativeLayout.BELOW, R.id.textview);
        view2.setLayoutParams(params);

        String text = "Today was a little better. I texted Sarah last night asking if she wanted to have lunch with me today, just the two of us, and she said sure. I told her that just because I’m hanging out with Jane, it doesn’t change anything about our friendship. After all, we’ve been friends since first grade! ";
        view2.setText(text+text+text+text+text);
        view2.setTextColor(0xFF000000);
        layout.addView(view2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == HomeActivity.RESULT_OK && data != null && data.getData() != null){
            imageUri =  data.getData();
            try {
                Context applicationContext = HomeActivity.getContextOfApplication();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                view1.setImageBitmap(bitmap);

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
        startActivityForResult(Intent.createChooser(intent, "Select Diary Image"), CHOOSE_IMAGE);
    }

    private void  uploadImageToFirebaseStorage(){

        FirebaseUser user = mAuth.getCurrentUser();

        StorageReference view1Ref = FirebaseStorage.getInstance().getReference(user.getUid()+ "/diarypics/"+user.getUid()+"_"+dateTextView.getText()+"_"+"diaryimage.jpg");

        if(imageUri != null){
//            progressBar.setVisibility(View.VISIBLE);
            view1Ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressBar.setVisibility(View.GONE);
                    view1Url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}

