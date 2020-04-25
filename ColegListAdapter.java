package com.example.schoolbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ColegListAdapter extends ArrayAdapter<coleg> {

    private Context mContext;
    int mResource;

    public ColegListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<coleg> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String name = getItem(position).getName();
        String label = getItem(position).getLabel();
        String email = getItem(position).getEmail();
        int number = getItem(position).getNumber();

        coleg coleg = new coleg(name,label,email,number);

        LayoutInflater inflanter = LayoutInflater.from(mContext);
        convertView= inflanter.inflate(mResource,parent, false);

        TextView tvNume = convertView.findViewById(R.id.NumeTextView);
        TextView tvEticheta =convertView.findViewById(R.id.EtichetaTextView);

        tvNume.setText(name);
        tvEticheta.setText(label);

        return  convertView;
    }
}