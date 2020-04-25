package com.example.schoolbox;


public class Mark {
    private double grade;
    private String subject_name;
    private String stage;
    private int stageInt;

    public Mark(String subject_name,double grade,String stageString,int stage){
        this.grade=grade;
        this.subject_name=subject_name;
        this.stage=stageString;
        this.stageInt=stage;
    }

    public double getGrade(){return grade;}
    public void setGrade(float grade){this.grade=grade;}

    public String getMark_Subject_Name(){return subject_name;}
    public void setMark_Subject_Name(String subject_name){this.subject_name=subject_name;}

    public String getMarkStage(){return stage;}
    public void setMarkStage(String stage){
        this.stage=stage;
    }

    public int getMarkStageInt(){return stageInt;}
    public void setMarkStageInt(int stageInt){this.stageInt=stageInt;}

    public String getMarkStageColor(){
        if(stageInt==1){
            return "#FF0000";
        }
        if(stageInt==2){
            return "#FF7F00";
        }
        if(stageInt==3){
            return "#FEDC56";
        }
        if(stageInt==4){
            return "#86C55A";
        }
        if(stageInt==5){
            return "#1FA055";
        }
        else {
            return null;
        }
    }


    }

