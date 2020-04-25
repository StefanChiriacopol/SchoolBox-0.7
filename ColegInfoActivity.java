package com.example.schoolbox;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class ColegInfoActivity extends AppCompatActivity {
    String prenumeString;
    String numeFamString;
    String emailString;
    String etichetaString;
    int number2;
    DatabaseHelper mDatabase = new DatabaseHelper(this);


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleg_info);

        final TextView imageTextView = findViewById(R.id.image_TextView);

        final EditText prenume = findViewById(R.id.PrenumeEditText);
        final EditText numeFam = findViewById(R.id.Nume_familieEditText);
        final EditText email = findViewById(R.id.EmailEditText);
        final EditText number = findViewById(R.id.NumberEditText);
        final EditText eticheta = findViewById(R.id.EtichetaEditText);

        prenume.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                prenumeString = prenume.getText().toString();
                char[] characters = prenumeString.toCharArray();
                if (characters.length != 0){
                    char firstChar = characters[0];
                    imageTextView.setText(String.valueOf(firstChar));}

                if(prenumeString.contains(" ")){
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));
                    TextView text = (TextView) layout.findViewById(R.id.textToast);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,50);
                    toast.setView(layout);
                    text.setText(R.string.toast3);
                    toast.show();
                }
            }
        });
        numeFam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                numeFamString = numeFam.getText().toString();
                if(numeFamString.contains(" ")){
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));
                    TextView text = (TextView) layout.findViewById(R.id.textToast);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,50);
                    toast.setView(layout);
                    text.setText(R.string.toast3);
                    toast.show();
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailString = email.getText().toString();
            }
        });
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                number2 = Integer.parseInt(number.getText().toString());
            }
        });
        eticheta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etichetaString = eticheta.getText().toString();
            }
        });



        ImageView backArrow = findViewById(R.id.BackArrow1);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ColegInfoActivity.this, MainActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(ColegInfoActivity.this).toBundle());
            }
        });

        ImageView saveClassmate = findViewById(R.id.SaveClassmateButton);
        saveClassmate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prenumeString!=null && etichetaString!=null){
                    String fullName;
                    if(numeFamString==null){
                        fullName=prenumeString;
                    } else {
                        fullName = prenumeString + " " + numeFamString;
                    }
                    mDatabase.addData(fullName,etichetaString,emailString,number2);
                    Intent intent1 = new Intent(ColegInfoActivity.this, MainActivity.class);
                    startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(ColegInfoActivity.this).toBundle());
                }
                else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));
                    TextView text = (TextView) layout.findViewById(R.id.textToast);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,50);
                    toast.setView(layout);
                    text.setText(R.string.toast2);
                    toast.show();
                }}
        });
    }
}
