package com.example.schoolbox;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class ColorGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private List<Integer> mThumbIds;

    public ColorGridViewAdapter (@NonNull Context context, List<Integer> ThumbIds) {
        mContext = context;
        mThumbIds = ThumbIds;
    }


    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mThumbIds.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if(imageView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,184));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        imageView.setImageResource(mThumbIds.get(position));
        return imageView;

    }

}
