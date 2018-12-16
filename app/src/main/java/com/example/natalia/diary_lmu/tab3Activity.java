package com.example.natalia.diary_lmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class tab3Activity extends AppCompatActivity {

    private static final String favoritedDiaryNamesKey = "favoritedDiaryNamesKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        GridView gridView = findViewById(R.id.gridview);
        final DiaryMonthAdapter diaryMonthAdapter = new DiaryMonthAdapter(this, diaries);
        gridView.setAdapter(diaryMonthAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaries[position];
                diary.toggleFavorite();
                diaryMonthAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final List<Integer> favoritedDiaryNames = new ArrayList<>();
        for (Diary diary : diaries) {
            if (diary.getIsFavorite()) {
                favoritedDiaryNames.add(diary.getName());
            }
        }

        outState.putIntegerArrayList(favoritedDiaryNamesKey, (ArrayList)favoritedDiaryNames);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final List<Integer> favoritedDiaryNames =
                savedInstanceState.getIntegerArrayList(favoritedDiaryNamesKey);

        // warning: typically you should avoid n^2 loops like this, use a Map instead.
        // I'm keeping this because it is more straightforward
        for (int diaryName : favoritedDiaryNames) {
            for (Diary diary : diaries) {
                if (diary.getName() == diaryName) {
                    diary.setIsFavorite(true);
                    break;
                }
            }
        }
    }

    private Diary[] diaries = {
            new Diary(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/abc.jpg"),
            new Diary(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
            new Diary(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
            new Diary(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
            new Diary(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
            new Diary(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
            new Diary(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
            new Diary(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
            new Diary(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
            new Diary(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg")
    };
}
