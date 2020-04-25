package com.example.schoolbox;

public class FocusOnItem {
    private String Letters;
    private String State;
    private int stateInt;

    public FocusOnItem(String letters, String state, int stateInt){
        this.Letters=letters;
        this.State=state;
        this.stateInt=stateInt;
    }

    public String getItemLetters(){return Letters;}
    public String getItemState(){return State;}
    public int getItemStateInt(){return stateInt;}

}
