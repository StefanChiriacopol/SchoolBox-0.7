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

public class MarksGridViewAdapter extends ArrayAdapter<Mark> {
    private final Context mContext;
    int mResource;

    public MarksGridViewAdapter(@NonNull Context context, int resource, @NonNull List<Mark> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getMark_Subject_Name();
        String stage = getItem(position).getMarkStage();
        int stageInt = getItem(position).getMarkStageInt();
        String color = getItem(position).getMarkStageColor();
        double grade = getItem(position).getGrade();


        Mark mark = new Mark(name,grade,stage,stageInt);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.Subject_TxtView);
        TextView tvMark = convertView.findViewById(R.id.MarkTxtView);
        TextView tvStage = convertView.findViewById(R.id.Stage_Mark_TxtView);

        //getting grade
        String gradeString = Double.toString(grade);
        String gradeFinal;
        String final_grade = null;
        String aux_grade = null;
        if(gradeString.contains(".")){
            int idx = gradeString.lastIndexOf('.');
            final_grade=gradeString.substring(0, idx);
            aux_grade = gradeString.substring(idx + 1);
        }
        if(aux_grade.equals("0")){
            gradeFinal=final_grade;
        } else {
            tvMark.setTextSize(50);
            gradeFinal=gradeString;
        }


        tvName.setText(name);
        tvMark.setText(gradeFinal);
        tvStage.setText(stage);
        tvStage.setTextColor(Color.parseColor(color));

        return convertView;
    }
}
