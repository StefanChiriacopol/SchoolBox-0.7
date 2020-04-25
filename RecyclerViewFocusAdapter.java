package com.example.schoolbox;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFocusAdapter extends RecyclerView.Adapter<RecyclerViewFocusAdapter.RecyclerViewHolder>{

    private ArrayList<FocusOnItem> mArrayList;
    private String color;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView tvLetters;
        public TextView tvState;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLetters = itemView.findViewById(R.id.AbbreviationItemTextView);
            tvState = itemView.findViewById(R.id.StageItemTextView);
        }
    }

    public RecyclerViewFocusAdapter(ArrayList<FocusOnItem> arrayList) {
        mArrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.focus_on_item_layout, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        FocusOnItem currentItem = mArrayList.get(position);
        int stateInt = currentItem.getItemStateInt();
        holder.tvLetters.setText(currentItem.getItemLetters());
        holder.tvState.setText(currentItem.getItemState());
        if(stateInt==1){
            color="#FF0000";
        }
        if(stateInt==2){
            color="#FF7F00";
        }
        if(stateInt==3){
            color="#FEDC56";
        }
        if(stateInt==4){
            color= "#86C55A";
        }
        if(stateInt==5){
            color= "#1FA055";
        }
        holder.tvState.setTextColor(Color.parseColor(color));

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
