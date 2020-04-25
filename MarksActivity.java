package com.example.schoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {
    ImageView addBtn,deletBtn;
    GridView marksGridView;
    ArrayList<Mark> MarksArrayList = new ArrayList<>();
    DatabaseHelper mDatabase = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        addBtn = findViewById(R.id.addMarkBtn);
        marksGridView = findViewById(R.id.MarksGridView);
        deletBtn = findViewById(R.id.deleteAllMarkBtn);

        //backArrowBtn
        ImageView backArrow = findViewById(R.id.BackArrow3);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MarksActivity.this, MainActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(MarksActivity.this).toBundle());
            }
        });


        //add Btn
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarksActivity.this,NewMarkActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MarksActivity.this).toBundle());
            }
        });

        //deleteBtn
        deletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MarksActivity.this);
                builder.setMessage(R.string.deleteMarks);
                builder.setPositiveButton(R.string.positiveDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.deleteMarkListData();
                        Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton(R.string.negativeDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            }
        });

        //populating the GridView
        Cursor data = mDatabase.getMarkData();
        while(data.moveToNext()){
            String name = data.getString(1);
            double grade = mDatabase.getGrade(name);
            int stageInt = mDatabase.getMarkStageIntValue(grade);
            String stage = getFastString(stageInt);
            Mark mark = new Mark(name,grade,stage,stageInt);
            MarksArrayList.add(mark);
        }


        //setting GridView adapter
        MarksGridViewAdapter adapter = new MarksGridViewAdapter(this,R.layout.marks_grid_view_item_layout,MarksArrayList);
        marksGridView.setAdapter(adapter);
    }

    public String getFastString(int stage){
        if(stage==1){
            return getString(R.string.ReallyBad);
        }
        if(stage==2){
            return getString(R.string.Bad);
        }
        if(stage==3){
            return getString(R.string.Stable);
        }
        if(stage==4){
            return getString(R.string.Good);
        }
        if(stage==5){
            return getString(R.string.Excellent);
        }
        else{
            return null;
        }
    }
}
