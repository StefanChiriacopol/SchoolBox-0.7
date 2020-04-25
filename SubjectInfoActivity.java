package com.example.schoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.security.auth.Subject;

public class SubjectInfoActivity extends AppCompatActivity {
    TextView SubjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);

        //getting Intent Info
        Intent intent = getIntent();
        int SubjectID = intent.getIntExtra("SubjectId",0);
        Log.i("Id subject",SubjectID+"");
    }

}
