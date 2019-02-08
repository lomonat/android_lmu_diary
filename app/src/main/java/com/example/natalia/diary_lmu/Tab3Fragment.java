package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tab3Fragment extends Fragment {


    FirebaseUser user;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;

    String dateSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initialDiaryData();

        GridView gridView = new GridView(getActivity());
        final DiaryMonthAdapter diaryMonthAdapter = new DiaryMonthAdapter(getActivity(), diaries);
        gridView.setAdapter(diaryMonthAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaries[position];
                Intent intent = new Intent(getActivity().getApplicationContext(),DiaryContentActivity.class);

                Bundle bundle = new Bundle();
                int selectedFlag = position + 1;
                dateSelected = "0" + selectedFlag + "-Feb-19";
                bundle.putString("diary_date", dateSelected);
                bundle.putString("diaryImageUrl",diary.imageUrl);
                intent.putExtras(bundle);

                startActivity(intent);
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "This is a message displayed in a Toast",
//                        Toast.LENGTH_SHORT);
//                toast.show();
            }
        });



        return gridView;
//        return inflater.inflate(R.layout.fragment_tab3, container, false);

    }

    private Diary[] diaries = {
            new Diary(R.string.summary_day_1, R.drawable.ic_launcher_foreground,
                    dateSelected),
            new Diary(R.string.summary_day_2, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_3, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_4, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_5, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_6, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_7, R.drawable.ic_launcher_foreground,
                    ""),
            new Diary(R.string.summary_day_8, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/5317/food-salad-restaurant-person.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_9, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/247685/pexels-photo-247685.png?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_10, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/132694/pexels-photo-132694.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_11, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/839462/pexels-photo-839462.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_12, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/125510/pexels-photo-125510.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_13, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/38537/woodland-road-falling-leaf-natural-38537.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_14, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/17739/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_15, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/673803/pexels-photo-673803.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_16, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/306825/pexels-photo-306825.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_17, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/589816/pexels-photo-589816.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_18, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/397096/pexels-photo-397096.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_19, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/1337975/pexels-photo-1337975.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_20, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/720362/pexels-photo-720362.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_21, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/700535/pexels-photo-700535.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_22, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/247937/pexels-photo-247937.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_23, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/697243/pexels-photo-697243.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_24, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/821054/pexels-photo-821054.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_25, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/1068523/pexels-photo-1068523.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_26, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/917510/pexels-photo-917510.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_27, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/541518/pexels-photo-541518.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_28, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/1105666/pexels-photo-1105666.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_29, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/1210530/pexels-photo-1210530.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_30, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/210854/pexels-photo-210854.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            new Diary(R.string.summary_day_31, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/164697/pexels-photo-164697.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
    };

    void initialDiaryData(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user  = mAuth.getCurrentUser();


//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateTime = dateFormat.format(c.getTime());

//        user.getUid()+ "/diarypics/"+user.getUid()+ "_" + dateTime + "_" +"diaryimage.jpg").getDownloadUrl()
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        String downloadURL0 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"01-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL0 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_01-Feb-19_diaryimage.jpg?alt=media&token=f27a814c-30be-4dd9-99d3-ac84ad00fc33";
        diaries[0].setImageUrl(downloadURL0);


        String downloadURL1 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"02-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL1 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_02-Feb-19_diaryimage.jpeg?alt=media&token=76393bd9-b4af-423f-9007-f2c177475c55";
        diaries[1].setImageUrl(downloadURL1);

        String downloadURL2 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"03-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL2 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_03-Feb-19_diaryimage.jpeg?alt=media&token=ceaf51f8-1061-47c4-a011-2d72f3918c20";
        diaries[2].setImageUrl(downloadURL2);

        String downloadURL3 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"04-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL3 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_04-Feb-19_diaryimage.jpeg?alt=media&token=970f032c-2af3-4df8-9a20-59e8233fb001";
        diaries[3].setImageUrl(downloadURL3);

        String downloadURL4 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"05-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL4 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_05-Feb-19_diaryimage.jpeg?alt=media&token=f7b26371-0f34-450d-84a2-fa7b08f3de82";
        diaries[4].setImageUrl(downloadURL4);

        String downloadURL5 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"06-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL5 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_06-Feb-19_diaryimage.jpeg?alt=media&token=d4bd26a6-af45-49f8-b8e7-375c3dd9449e";
        diaries[5].setImageUrl(downloadURL5);

        String downloadURL6 = storageRef.child(user.getUid()+ "/diarypics/" +user.getUid()+"_"+"07-Feb-19"+"_"+"diaryimage.jpg").getDownloadUrl().toString();
        downloadURL6 = "https://firebasestorage.googleapis.com/v0/b/contentdatabase-ff384.appspot.com/o/1C8JDyWhErTqwphSkbM3STuyDgn1%2Fdiarypics%2F1C8JDyWhErTqwphSkbM3STuyDgn1%252Fdiarypics%252F1C8JDyWhErTqwphSkbM3STuyDgn1_07-Feb-19_diaryimage.jpg?alt=media&token=3106c4f6-da36-44db-bd32-857a540f7741";
        diaries[6].setImageUrl(downloadURL6);

//        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
//                dateSelected,
//                        Toast.LENGTH_LONG);
//                toast.show();
    }
}