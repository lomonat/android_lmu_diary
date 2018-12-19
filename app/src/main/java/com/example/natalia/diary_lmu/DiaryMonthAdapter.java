package com.example.natalia.diary_lmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import com.squareup.picasso.Picasso;

public class DiaryMonthAdapter extends BaseAdapter{
    private final Context mContext;
    private final Diary[] diaries;

    public DiaryMonthAdapter(Context context, Diary[] diaries) {
        this.mContext = context;
        this.diaries = diaries;
    }

    @Override
    public int getCount() {
        return diaries.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Diary diary = diaries[position];
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_diary, null);

            final ImageView imageViewCoverArt = convertView.findViewById(R.id.imageview_cover_art);
            final TextView summaryTextView = convertView.findViewById(R.id.textview_diary_summary);
            final ImageView imageViewDayIcon = convertView.findViewById(R.id.imageview_dayIcon);

            final ViewHolder viewHolder = new ViewHolder(summaryTextView, imageViewCoverArt, imageViewDayIcon);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.imageViewCoverArt.setImageResource(diary.getImageResource());
        viewHolder.summaryTextView.setText(mContext.getString(diary.getSummary()));

        String dayIconName = "icons8_calendar_" + String.valueOf(position + 1) + "_80";
        Uri dayIconUri=Uri.parse("android.resource://com.example.natalia.diary_lmu/drawable/" + dayIconName);
        viewHolder.imageViewDayIcon.setImageURI(dayIconUri);

        Picasso.get().load(diary.getImageUrl()).error(R.drawable.ic_launcher_background).into(viewHolder.imageViewCoverArt);

        return convertView;
    }

    private class ViewHolder {
        private final TextView summaryTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewDayIcon;

        public ViewHolder( TextView summaryTextView, ImageView imageViewCoverArt, ImageView imageViewDayIcon) {
            this.summaryTextView = summaryTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewDayIcon = imageViewDayIcon;
        }
    }

}
