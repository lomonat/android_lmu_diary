package com.example.natalia.diary_lmu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.ImageView;

public class Tab1Fragment extends Fragment {

    private Context mContext = null;
    private ImageView view1 = null;
    private TextView view2 = null;
    private RelativeLayout layout = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
        addView1();
        addView2();
        scroller.addView(layout);

        return scroller;
//        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    private void addView1() {
        view1 = new ImageView(mContext);
        view1.setLayoutParams(new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view1.setImageResource(R.drawable.food_simple);
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

}

