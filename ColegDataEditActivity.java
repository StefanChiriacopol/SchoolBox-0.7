package com.example.schoolbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ColegDataEditActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    //Declarations for variables
    EditText Last_nameEdits, First_nameEdits, Label_nameEdits, NumberEdits, EmailEdits;
    Button deleteBtn, saveBtn;
    DatabaseHelper mDatabase = new DatabaseHelper(this);
    ImageView backBtn, EmailBtn, CallBtn, MessageBtn;
    TextView imageTextView;
    int id;
    String FirstName, LastName, Label, Email;
    int Number;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleg_data_edit);

        Last_nameEdits = findViewById(R.id.LastNameEdits);
        First_nameEdits = findViewById(R.id.FirstNameEdits);
        Label_nameEdits = findViewById(R.id.LabelEdits);
        NumberEdits = findViewById(R.id.NumberEdits);
        EmailEdits = findViewById(R.id.EmailEdits);
        deleteBtn = findViewById(R.id.DeleteButton);
        saveBtn = findViewById(R.id.SaveEditsButton);
        backBtn = findViewById(R.id.BackArrow2);
        imageTextView = findViewById(R.id.image_TextView2);
        EmailBtn = findViewById(R.id.EmailBtn);
        CallBtn = findViewById(R.id.callBtn);
        MessageBtn = findViewById(R.id.MessageBtn);

        //Button Back Arrow
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ColegDataEditActivity.this, MainActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(ColegDataEditActivity.this).toBundle());
            }
        });


        //getting Data from previous activity
        Intent intent = getIntent();
        id = intent.getIntExtra("Id", 0);

        //showing it to EditTexts
        Cursor data = mDatabase.getSpecificData(id);

        try {
            if (data.getCount() != 0) {
                if (data.moveToFirst()) {

                    //Personal Info String method
                    String fullName = data.getString(data.getColumnIndex(DatabaseHelper.COL_2));
                    String firstName, lastName;

                    if (fullName.contains(" ")) {
                        int idx = fullName.lastIndexOf(' ');
                        firstName = fullName.substring(0, idx);
                        lastName = fullName.substring(idx + 1);

                        FirstName = firstName;
                        LastName = lastName;

                        First_nameEdits.setText(firstName);
                        Last_nameEdits.setText(lastName);
                    } else {
                        firstName = fullName;

                        FirstName = fullName;
                        LastName = null;

                        First_nameEdits.setText(firstName);
                    }

                    //continue
                    Label = data.getString(data.getColumnIndex(DatabaseHelper.COL_3));
                    Label_nameEdits.setText(Label);

                    //checking phone number
                    int phoneNum = Integer.parseInt(data.getString(data.getColumnIndex(DatabaseHelper.COL_5)));
                    if (phoneNum <= 0) {
                    } else {
                        Number = phoneNum;
                        NumberEdits.setText(data.getString(data.getColumnIndex(DatabaseHelper.COL_5)));
                    }

                    //continue
                    Email = data.getString(data.getColumnIndex(DatabaseHelper.COL_4));
                    EmailEdits.setText(Email);

                    //setting imageTextView text
                    char[] characters = firstName.toCharArray();
                    if (characters.length != 0) {
                        char firstChar = characters[0];
                        imageTextView.setText(String.valueOf(firstChar));
                    }
                }

            }
        } finally {
            if (data != null) {
                data.close();
            }
        }

        //deleteBtn
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.deleteDataFromRawColID(id);
                ToastMessage(R.string.toast4);
                Intent intent2 = new Intent(ColegDataEditActivity.this, MainActivity.class);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(ColegDataEditActivity.this).toBundle());
            }
        });


        //setting the EditTexts getText

        Last_nameEdits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LastName = Last_nameEdits.getText().toString();
                if (LastName.contains(" ")) {
                    ToastMessage(R.string.toast3);
                }
            }
        });
        First_nameEdits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FirstName = First_nameEdits.getText().toString();
                if (FirstName.contains(" ")) {
                    ToastMessage(R.string.toast3);
                }
            }
        });
        Label_nameEdits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Label = Label_nameEdits.getText().toString();
            }
        });
        NumberEdits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String Number_String = NumberEdits.getText().toString();
                try {
                    Number = Integer.parseInt(Number_String);
                } catch (NumberFormatException ex) {
                    Number = 0;
                }

            }

        });
        EmailEdits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Email = EmailEdits.getText().toString();
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LastName != null) {
                    name = FirstName + " " + LastName;
                } else {
                    name = FirstName;
                }
                String idString = String.valueOf(id);
                boolean isUpdated = mDatabase.UpdateData(idString, name, Label, Email, Number);
                if (isUpdated == true) {

                    //displayMessage
                    ToastMessage(R.string.toast1);
                    Intent intent2 = new Intent(ColegDataEditActivity.this, MainActivity.class);
                    startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(ColegDataEditActivity.this).toBundle());
                }
            }
        });


        //CallBtn

        CallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        //MessageBtn

        MessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = String.valueOf(Number);
                if (Number!=0){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",number,null));
                    startActivity(intent);}
                else {
                    ToastMessage(R.string.toastCall);
                }
            }
        });


        //EmailBtn

        EmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email == null){
                    ToastMessage(R.string.toastNoEmail);}
                else{
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String [] email_to = {Email};
                    intent.putExtra(Intent.EXTRA_EMAIL, email_to);
                    intent.setType("message/rfc822");
                    startActivity(intent);
                }
            }
        });


    }

    private void ToastMessage(int message) {
        //displayMessage
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.textToast);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.setView(layout);
        text.setText(message);
        toast.show();
    }

    private void makePhoneCall(){
        String number = String.valueOf(Number);
        if(Number!=0){

            if (ContextCompat.checkSelfPermission(ColegDataEditActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(ColegDataEditActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {
                String country = getUserCountry(this);
                String dial;
                if (country.equals("ro")){
                    dial = "tel:+40" + number;
                }
                else{
                    dial = "tel:"+number;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            ToastMessage(R.string.toastCall);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            } else{
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_custom,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.textToast);
                Toast toast = new Toast(this);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.setView(layout);
                text.setText(R.string.toastPermissionDenied);
                toast.show();

            }
        }
    }

    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }
}
