package com.example.schoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class NewSubjectActivity extends AppCompatActivity {
    ImageView backBtn, saveBtn;
    TextView abbreviation;
    String name, teacher, letters, color, title;
    EditText nameEditTxt, teacherEditTxt, LettersEditTxt;
    boolean wasChoosed;
    PopupMenu popup;
    int abrev =0;
    GridView colorsGrid;
    DatabaseHelper mDatabase = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        backBtn = findViewById(R.id.BackArrow5);
        abbreviation = findViewById(R.id.AbbreviationTxtView);
        nameEditTxt = findViewById(R.id.SubjectNameEditText);
        teacherEditTxt = findViewById(R.id.TeacherEditText);
        LettersEditTxt = findViewById(R.id.LettersEditTxt);
        colorsGrid = findViewById(R.id.ColorGridView);
        saveBtn = findViewById(R.id.SaveBtnImageView);

        //nameEditText
        nameEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = nameEditTxt.getText().toString();
            }
        });

        //teacherEditText
        teacherEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                teacher = teacherEditTxt.getText().toString();
            }
        });

        //Letters Edit Text
        LettersEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                letters = LettersEditTxt.getText().toString();
            }
        });

        //backBtn
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewSubjectActivity.this, ClassesActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewSubjectActivity.this).toBundle());
            }
        });


        //menu
        popup = new PopupMenu(NewSubjectActivity.this, abbreviation);
        popup.getMenuInflater().inflate(R.menu.subject_abbreviation_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                title = item.getTitle().toString();

                    if (title.equals(getString(R.string.TwoLettersOption))) {
                        abrev=2;
                    }
                    if (title.equals(getString(R.string.ThreeLetersOption))) {
                        abrev=3;
                    }
                    if (title.equals(getString(R.string.OneLettersOption))) {
                        abrev=1;
                    }
                    if (title.equals(getString(R.string.FourLettersOption))) {
                        abrev = 4;
                    }
                    LettersEditTxt.setVisibility(View.VISIBLE);
                    LettersEditTxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(abrev), new InputFilter.AllCaps()});
                return false;
            }
        });

        //abbreviationTxtView item showing
        abbreviation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
            }
        });


        //setting the Color GridView
        ArrayList<Integer> colorList = new ArrayList<>(Arrays.asList(
                R.drawable.oval_negru1,R.drawable.oval_negru2,R.drawable.oval_negru3,R.drawable.oval_negru4,R.drawable.oval_negru5,
                R.drawable.oval_maro1,R.drawable.oval_maro2,R.drawable.oval_maro3,R.drawable.oval_maro4,R.drawable.oval_maro5,
                R.drawable.oval_mov3,R.drawable.oval_mov2,R.drawable.oval_mov1,R.drawable.oval_mov4,R.drawable.oval_mov5,
                R.drawable.oval_rosu1,R.drawable.oval_rosu2,R.drawable.oval_rosu3,R.drawable.oval_rosu4,R.drawable.oval_rosu5,
                R.drawable.oval_roz1,R.drawable.oval_roz2,R.drawable.oval_roz3,R.drawable.oval_roz4,R.drawable.oval_roz5,
                R.drawable.oval_porto1,R.drawable.oval_porto2,R.drawable.oval_porto3,R.drawable.oval_porto4,R.drawable.oval_porto5,
                R.drawable.oval_verde_d1,R.drawable.oval_verde_d2,R.drawable.oval_verde_d3,R.drawable.oval_verde_d4,R.drawable.oval_verde_d5,
                R.drawable.oval_verde_i1,R.drawable.oval_verde_i2,R.drawable.oval_verde_i3,R.drawable.oval_verde_i4,R.drawable.oval_verde_i5,
                R.drawable.oval_alb_i1,R.drawable.oval_alb_i2,R.drawable.oval_alb_i3,R.drawable.oval_alb_i4,R.drawable.oval_alb_i5,
                R.drawable.oval_alb_d2,R.drawable.oval_alb_d1,R.drawable.oval_alb_d3,R.drawable.oval_alb_d4,R.drawable.oval_alb_d5));
        ColorGridViewAdapter colorGridViewAdapter = new ColorGridViewAdapter(NewSubjectActivity.this,colorList);
        colorsGrid.setAdapter(colorGridViewAdapter);

            //onItemClick
        colorsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;
                Drawable drawable = img.getDrawable();
                color = getColor(drawable);
                Log.i("Color" ,color+"");
            }
        });

        //saveBtn
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name!=null && letters!=null && color!=null){
                    mDatabase.addSubjectData(name,letters,teacher,color);
                    mDatabase.addMarkData(name,getString(R.string.Excellent),5,10);
                    Intent intent = new Intent(NewSubjectActivity.this,ClassesActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewSubjectActivity.this).toBundle());
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));
                    TextView text = (TextView) layout.findViewById(R.id.textToast);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,50);
                    toast.setView(layout);
                    toast.setDuration(Toast.LENGTH_LONG);
                    text.setText(R.string.toastCannotSaveSubject);
                    toast.show();
                }
            }
        });

    }

    private String getColor(Drawable drawable){
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_d1).getConstantState()){
            colorToast();
            return "#00B2D6";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_d2).getConstantState()){
            colorToast();
            return "#03A99D";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_d3).getConstantState()){
            colorToast();
            return "#29AAE3";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_d4).getConstantState()){
            colorToast();
            return "#56B9E0";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_d5).getConstantState()){
            colorToast();
            return "#AFDBE6";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_i1).getConstantState()){
            colorToast();
            return "#04458F";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_i2).getConstantState()){
            colorToast();
            return "#0167B2";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_i3).getConstantState()){
            colorToast();
            return "#1072BB";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_i4).getConstantState()){
            colorToast();
            return "#457FBF";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_alb_i5).getConstantState()){
            colorToast();
            return "#628CC8";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_i1).getConstantState()){
            colorToast();
            return "#006837";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_i2).getConstantState()){
            colorToast();
            return "#009144";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_i3).getConstantState()){
            colorToast();
            return "#00A652";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_i4).getConstantState()){
            colorToast();
            return "#3DB54B";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_i5).getConstantState()){
            colorToast();
            return "#23B574";
        }

        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_d1).getConstantState()){
            colorToast();
            return "#6EB650";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_d2).getConstantState()){
            colorToast();
            return "#8DC643";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_d3).getConstantState()){
            colorToast();
            return "#C0D441";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_d4).getConstantState()){
            colorToast();
            return "#DAE028";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_verde_d5).getConstantState()){
            colorToast();
            return "#F9ED25";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_negru1).getConstantState()){
            colorToast();
            return "#000000";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_negru2).getConstantState()){
            colorToast();
            return "#262626";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_negru3).getConstantState()){
            colorToast();
            return "#464646";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_negru4).getConstantState()){
            colorToast();
            return "#636363";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_negru5).getConstantState()){
            colorToast();
            return "#7D7D7D";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_mov1).getConstantState()){
            colorToast();
            return "#683093";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_mov2).getConstantState()){
            colorToast();
            return "#932B8E";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_mov3).getConstantState()){
            colorToast();
            return "#9D1F5F";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_mov4).getConstantState()){
            colorToast();
            return "#5A3D91";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_mov5).getConstantState()){
            colorToast();
            return "#6E529C";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_rosu1).getConstantState()){
            colorToast();
            return "#683093";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_rosu2).getConstantState()){
            colorToast();
            return "#932B8E";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_rosu3).getConstantState()){
            colorToast();
            return "#9D1F5F";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_rosu4).getConstantState()){
            colorToast();
            return "#5A3D91";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_rosu5).getConstantState()){
            colorToast();
            return "#6E529C";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_roz1).getConstantState()){
            colorToast();
            return "#E4007B";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_roz2).getConstantState()){
            colorToast();
            return "#E72A86";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_roz3).getConstantState()){
            colorToast();
            return "#EB5094";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_roz4).getConstantState()){
            colorToast();
            return "#EF6FA2";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_roz5).getConstantState()){
            colorToast();
            return "#F07893";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_porto1).getConstantState()){
            colorToast();
            return "#F05D27";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_porto2).getConstantState()){
            colorToast();
            return "#E76F33";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_porto3).getConstantState()){
            colorToast();
            return "#F79321";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_porto4).getConstantState()){
            colorToast();
            return "#FBB03B";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_porto5).getConstantState()){
            colorToast();
            return "#FFD640";
        }


        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_maro1).getConstantState()){
            colorToast();
            return "#412414";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_maro2).getConstantState()){
            colorToast();
            return "#603A16";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_maro3).getConstantState()){
            colorToast();
            return "#744D24";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_maro4).getConstantState()){
            colorToast();
            return "#8D6339";
        }
        if(drawable.getConstantState()==getResources().getDrawable(R.drawable.oval_maro5).getConstantState()){
            colorToast();
            return "#A67C52";
        }


        else {
            return null;
        }
    }

    public void colorToast (){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.textToast);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,50);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        text.setText(R.string.toastColorChoosed);
        toast.show();

    }

}
