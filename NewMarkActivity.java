package com.example.schoolbox;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewMarkActivity extends AppCompatActivity {
    ImageView backBtn, saveBtn;
    RadioGroup radioGroup;
    TextView chooseHere;
    EditText subjectEditText, AnotherOptionEditTxt;
    DatabaseHelper mDatabase = new DatabaseHelper(this);
    String name;
    double grade = 0;
    boolean anotherIsChoosed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mark);

        //setting Values
        backBtn = findViewById(R.id.BackArrow10);
        radioGroup = findViewById(R.id.MarkRadioGroup);
        chooseHere = findViewById(R.id.MarkOptionTxtView);
        subjectEditText = findViewById(R.id.SubjectNameMarkEditText);
        saveBtn = findViewById(R.id.SaveMarkImageView);
        AnotherOptionEditTxt = findViewById(R.id.AnotherOptionEditTxt);

        //back Btn
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewMarkActivity.this, MarksActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewMarkActivity.this).toBundle());
            }
        });

        //choose here on click
        chooseHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.setVisibility(View.VISIBLE);
            }
        });

        //get option
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedOptionId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedOptionId);
                String option = radioButton.getText().toString();
                if (!option.equals(getString(R.string.Another_procent))) {
                    grade = getOptionInt(option);
                } else {
                    AnotherOptionEditTxt.setVisibility(View.VISIBLE);
                    AnotherOptionEditTxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                }

            }
        });

        //AnotherOption EditTxt
        AnotherOptionEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String aux = AnotherOptionEditTxt.getText().toString();
                try {
                    double grade_aux =Double.parseDouble(aux);
                    if(grade_aux<=10){
                        grade=grade_aux;
                    } else {
                        int aux2 = (int) grade_aux;
                        int first_digit = (int) grade_aux/10;
                        int second_digit = (int) grade_aux%10;
                        String auxString = first_digit+"."+second_digit;
                        grade = Double.parseDouble(auxString);
                        Log.i("Numbers:",auxString);

                    }
                } catch (NumberFormatException e){
                    fastToast(getString(R.string.properProcent));
                    grade=0;
                }
            }
        });

        //get subject
        subjectEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = subjectEditText.getText().toString();
            }
        });

        //save Btn
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name != null && grade != 0) {
                    boolean isSubjectOk = mDatabase.checkSubject(name);
                    if (isSubjectOk) {
                        mDatabase.addMarkListData(name, grade);
                        Intent intent = new Intent(NewMarkActivity.this, MarksActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewMarkActivity.this).toBundle());
                        fastToast(getString(R.string.markAdded));
                    } else {
                        fastToast(getString(R.string.ToastNoSuchSubject));
                    }
                } else {
                    fastToast(getString(R.string.toastCannotSaveMark));
                }
            }
        });

    }

    public int getOptionInt(String option) {
        if (option.equals(getString(R.string.grade1))) {
            return 1;
        }
        if (option.equals(getString(R.string.grade2))) {
            return 2;
        }
        if (option.equals(getString(R.string.grade3))) {
            return 3;
        }
        if (option.equals(getString(R.string.grade4))) {
            return 4;
        }
        if (option.equals(getString(R.string.grade5))) {
            return 5;
        }
        if (option.equals(getString(R.string.grade6))) {
            return 6;
        }
        if (option.equals(getString(R.string.grade7))) {
            return 7;
        }
        if (option.equals(getString(R.string.grade8))) {
            return 8;
        }
        if (option.equals(getString(R.string.grade9))) {
            return 9;
        }
        if (option.equals(getString(R.string.grade10))) {
            return 10;
        } else {
            return 0;
        }
    }

    public void fastToast(String messsage) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.textToast);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        text.setText(messsage);
        toast.show();
    }
}
