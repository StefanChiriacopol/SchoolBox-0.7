package com.example.schoolbox;

public class ClassObject {
    private String name;
    private String teacher;
    private String color;
    private String letters;

    public ClassObject(String name,String letters, String teacher, String color){
        this.name = name;
        this.teacher = teacher;
        this.color = color;
        this.letters = letters;

    }

    public String getClassName(){ return name;}
    public void setClassName(String name) {this.name = name;}

    public String getClassTeacher(){return teacher;}
    public void setClassTeacher(String teacher){this.teacher=teacher;}

    public String getClassColor(){return color;}
    public void setClassColor(String color){this.color = color;}

    public String getClassLetters(){return letters;}
    public void setClassLetters(String letters){this.letters = letters;}

}