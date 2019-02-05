package com.example.natalia.diary_lmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class Tab3Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        GridView gridView = new GridView(getActivity());
        final DiaryMonthAdapter diaryMonthAdapter = new DiaryMonthAdapter(getActivity(), diaries);
        gridView.setAdapter(diaryMonthAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaries[position];
                Intent intent = new Intent(getActivity().getApplicationContext(),DiaryContentActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", "test");
                intent.putExtras(bundle);

                startActivity(intent);
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "This is a message displayed in a Toast",
//                        To ast.LENGTH_SHORT);
//                toast.show();
            }
        });



        return gridView;
//        return inflater.inflate(R.layout.fragment_tab3, container, false);

    }

    private Diary[] diaries = {
            new Diary(R.string.summary_day_1, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/34950/pexels-photo.jpg"),
            new Diary(R.string.summary_day_2, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/33109/fall-autumn-red-season.jpg"),
            new Diary(R.string.summary_day_3, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/4827/nature-forest-trees-fog.jpeg"),
            new Diary(R.string.summary_day_4, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_5, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_6, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/1268558/pexels-photo-1268558.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
            new Diary(R.string.summary_day_7, R.drawable.ic_launcher_foreground,
                    "https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"),
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
}