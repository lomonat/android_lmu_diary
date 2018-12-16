package com.example.natalia.diary_lmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            final TextView nameTextView = convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, authorTextView, imageViewCoverArt, imageViewFavorite);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
//    viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        viewHolder.nameTextView.setText(mContext.getString(diary.getName()));
        viewHolder.authorTextView.setText(mContext.getString(diary.getAuthor()));
        viewHolder.imageViewFavorite.setImageResource(diary.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);

        Picasso.get().load(diary.getImageUrl()).into(viewHolder.imageViewCoverArt);

        return convertView;
    }

    private class ViewHolder {
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;

        public ViewHolder(TextView nameTextView, TextView authorTextView, ImageView imageViewCoverArt, ImageView imageViewFavorite) {
            this.nameTextView = nameTextView;
            this.authorTextView = authorTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
        }
    }

}
