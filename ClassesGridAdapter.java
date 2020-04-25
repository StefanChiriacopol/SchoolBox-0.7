package com.example.schoolbox;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClassesGridAdapter extends ArrayAdapter<ClassObject> {
    private final Context mContext;
    int mResource;


    public ClassesGridAdapter(@NonNull Context context, int resource, @NonNull List<ClassObject> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getClassName();
        String teacher = getItem(position).getClassTeacher();
        String nameLetters = getItem(position).getClassLetters();
        String color = getItem(position).getClassColor();

        ClassObject Class = new ClassObject(name, nameLetters, teacher, color);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.NameTextView);
        TextView tvLetters = convertView.findViewById(R.id.NameLettersTextView);


        tvName.setText(name);
        tvLetters.setText(nameLetters);
        tvLetters.setTextColor(Color.parseColor(color));

        return convertView;
    }
}