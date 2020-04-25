package com.example.schoolbox;

public class coleg {

    private String name;
    private String Label;
    private String email;
    int number;

    public coleg(String name,String Label, String email, int number){
        this.name = name;
        this.Label = Label;
        this.number = number;
        this.email = email;

    }

    public String getName(){ return name;}
    public void setName(String name) {this.name = name;}

    public String getLabel(){return Label;}
    public void setLabel(String Label){this.Label=Label;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public int getNumber(){return number;}
    public void setNumber(int number){this.number = number;}
}
