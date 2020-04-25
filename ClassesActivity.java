package com.example.schoolbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {
    GridView classesGridView;
    ImageView new_subject_btn, delete_all_subjects_btn;
    ArrayList<ClassObject> classesArrayList = new ArrayList<>();
    TextView lettersTxtView;
    DatabaseHelper mDatabase = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        setTitle("Classes Activity");

        //setting values
        classesGridView = findViewById(R.id.ClassesGridView);
        new_subject_btn = findViewById(R.id.AddClassBtn);
        lettersTxtView =  findViewById(R.id.NameLettersTextView);
        delete_all_subjects_btn = findViewById(R.id.MinusSubjectsBtn);

        //fill the GridView

        Cursor data = mDatabase.getSubjectsData();
        while(data.moveToNext()){
            String name = data.getString(1);
            String teacher = data.getString(3);
            String letters = data.getString(2);
            String color = data.getString(4);
            ClassObject subject = new ClassObject(name,letters,teacher,color);
            classesArrayList.add(subject);
        }


        //setting GridView adapter
        ClassesGridAdapter adapter = new ClassesGridAdapter(this,R.layout.class_list_item_layout,classesArrayList);
        classesGridView.setAdapter(adapter);

        //onItemClick


        classesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //creating Extras
                ClassObject aux = (ClassObject) parent.getItemAtPosition(position);
                String name = aux.getClassName();
                Cursor data = mDatabase.getItemSubjectID(name);
                int itemId = -1;
                while (data.moveToNext()){
                    itemId = data.getInt(0);
                }

                //intent
                Intent intent = new Intent(ClassesActivity.this, SubjectInfoActivity.class);
                intent.putExtra("SubjectId", itemId);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(ClassesActivity.this).toBundle());
            }
        });


        //backBtn
        ImageView backArrow = findViewById(R.id.BackArrow4);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ClassesActivity.this, MainActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(ClassesActivity.this).toBundle());
            }
        });

        //new subject btn

        new_subject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ClassesActivity.this, NewSubjectActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(ClassesActivity.this).toBundle());
            }
        });

        // delete all Btn

        delete_all_subjects_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassesActivity.this);
                builder.setMessage(R.string.delete_subjects);
                builder.setPositiveButton(R.string.positiveDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.deleteSubjectData();
                        mDatabase.deleteMarkData();
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

    }
}
