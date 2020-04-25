package com.example.schoolbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "Student.db";

    //Student.db
    public static final String TableName = "StudentTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Label";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Number";

    //Subject.db

    public static final String SubjectsTableName = "SubjectsTable";
    public static final String Subjects_COL_1 = "ID";
    public static final String Subjects_COL_2 = "Subject_Name";
    public static final String Subjects_COL_3 = "Letters";
    public static final String Subjects_COL_4 = "Teacher";
    public static final String Subjects_COL_5 = "Color";

    //Marks.db
    public static final String MarkTableName = "MarksTable";
    public static final String Mark_COL_1 = "ID";
    public static final String Mark_COL_2 = "Subject_Name";
    public static final String Mark_COL_3 = "State";
    public static final String Mark_COL_4 = "State_Int";
    public static final String Mark_COL_5 = "Medie";

    //MarksList.db
    public static final String Mark_List_TableName = "MarksListTable";
    public static final String Mark_List_COL_1 = "ID";
    public static final String Mark_List_COL_2 = "Subject_Name";
    public static final String Mark_List_COL_3 = "Grade";

    public DatabaseHelper(Context context) {
        super(context,DataBaseName, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TableName + " ( "+COL_1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+ " TEXT, "+COL_3+ " TEXT, "+COL_4+" TEXT, "+COL_5+ " INTEGER)";
        String createSubjectsTable = "CREATE TABLE "+SubjectsTableName+" ( "+Subjects_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Subjects_COL_2+" TEXT, "+Subjects_COL_3+" TEXT, "+Subjects_COL_4+" TEXT, "+Subjects_COL_5+" TEXT)";
        String createMarkTable = "CREATE TABLE "+MarkTableName+" ( "+Mark_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Mark_COL_2+" TEXT, "+Mark_COL_3+" TEXT, "+Mark_COL_4+" INTEGER, "+Mark_COL_5 +" DOUBLE)";
        String createMarkListTable="CREATE TABLE "+Mark_List_TableName+" ( "+Mark_List_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Mark_List_COL_2+" TEXT, "+Mark_List_COL_3+" DOUBLE)";
        db.execSQL(createTable);
        db.execSQL(createSubjectsTable);
        db.execSQL(createMarkTable);
        db.execSQL(createMarkListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        db.execSQL("DROP TABLE IF EXISTS "+SubjectsTableName);
        db.execSQL("DROP TABLE IF EXISTS "+MarkTableName);
        db.execSQL("DROP TABLE IF EXISTS "+Mark_List_TableName);
        onCreate(db);
    }

    public boolean addData (String name, String label, String email, int number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, label);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, number);
        long result = db.insert(TableName, null, contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addSubjectData (String name, String letters, String teacher, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Subjects_COL_2,name);
        contentValues.put(Subjects_COL_3,letters);
        contentValues.put(Subjects_COL_4,teacher);
        contentValues.put(Subjects_COL_5,color);
        long result = db.insert(SubjectsTableName, null, contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addMarkData (String subject, String state, int StateInt, double grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Mark_COL_2,subject);
        contentValues.put(Mark_COL_3,state);
        contentValues.put(Mark_COL_4,StateInt);
        contentValues.put(Mark_COL_5,grade);
        long result = db.insert(MarkTableName, null, contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addMarkListData (String subject, double grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Mark_List_COL_2,subject);
        contentValues.put(Mark_List_COL_3,grade);
        long result = db.insert(Mark_List_TableName, null, contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TableName, null);
        return data;
    }

    public Cursor getSubjectsData(){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ SubjectsTableName, null);
        return data;
    }

    public Cursor getMarkData(){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ MarkTableName, null);
        return data;
    }

    public Cursor getMarkListData(){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ Mark_List_TableName, null);
        return data;
    }


    public Cursor getSpecificData(int id){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TableName + " WHERE ID ="+id, null);
        return data;
    }

    public Cursor getSpecificSubjectData(int id){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ SubjectsTableName + " WHERE ID ="+id, null);
        return data;
    }

    public boolean checkSubject(String name){
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor data = db.rawQuery("SELECT Letters FROM "+ SubjectsTableName + " WHERE Subject_Name = '"+name+"'", null);
        String name_aux = null;
        while (data.moveToNext()){
            name_aux = data.getString(0);
        }
        if(name_aux==null){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getItemID (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_1+" FROM "+TableName+" WHERE "+COL_2+"= '"+name+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemMarkID (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+Mark_COL_1+" FROM "+MarkTableName+" WHERE "+Mark_COL_2+"= '"+name+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemSubjectID (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+Subjects_COL_1+" FROM "+SubjectsTableName+" WHERE "+Subjects_COL_2+"= '"+name+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getSubjectLetters(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+Subjects_COL_3+" FROM "+SubjectsTableName+" WHERE "+Subjects_COL_2+"= '"+name+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TableName);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TableName + "'");
    }

    public void deleteMarkListData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Mark_List_TableName);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + Mark_List_TableName + "'");
    }

    public void deleteSubjectData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ SubjectsTableName);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + SubjectsTableName + "'");
    }

    public void deleteMarkData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ MarkTableName);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MarkTableName + "'");
    }
    //ATTENTION AT DELETING SPECIFIC DATA: DELETE SUBJECT --> DELETE ALSO MARK

    public void deleteDataFromRawColID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TableName + " WHERE ID ="+id);
    }

    public boolean UpdateData (String id, String name, String label, String email, int number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, label);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, number);
        db.update(TableName,contentValues,"ID = ?", new String[]{id});
        return true;
    }


    public boolean UpdateMarkData (String id, String name, String letters, int StateInt, double grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, letters);
        contentValues.put(COL_4, StateInt);
        contentValues.put(COL_5, grade);
        db.update(MarkTableName,contentValues,"ID = ?", new String[]{id});
        return true;
    }



    public double getGrade (String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT Grade FROM "+ Mark_List_TableName + " WHERE Subject_Name = '"+subject+"'", null);
        int aux=0;
        double grade, gradeFinal=0, medie=10;
        while (data.moveToNext()){
            grade = data.getDouble(0);
            gradeFinal=gradeFinal+grade;
            aux++;
            medie = gradeFinal/aux;
        }
        return medie;
    }

    public int getMarkStageIntValue(double grade){
        if(grade>=9.5){
            return 5;
        }
        if(grade>=8&&grade<9.5){
            return 4;
        }
        if(grade>=6.5&&grade<8){
            return 3;
        }
        if(grade>=4.5&&grade<6.5){
            return 2;
        }
        if(grade<4.5){
            return 1;
        } else {
            return 0;
        }
    }

    public Cursor getMarkWithMinState1(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+MarkTableName+" WHERE "+Mark_COL_4+"= 1";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getMarkWithMinState2(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+MarkTableName+" WHERE "+Mark_COL_4+"= 1 OR "+Mark_COL_4+"= 2";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getMarkWithMinState3(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+MarkTableName+" WHERE "+Mark_COL_4+"= 1 OR "+Mark_COL_4+"= 2 OR "+Mark_COL_4+"= 3";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getMarkWithMinState4(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+MarkTableName+" WHERE "+Mark_COL_4+"= 1 OR "+Mark_COL_4+"= 2 OR "+Mark_COL_4+"= 3 OR "+Mark_COL_4+"= 4";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}


