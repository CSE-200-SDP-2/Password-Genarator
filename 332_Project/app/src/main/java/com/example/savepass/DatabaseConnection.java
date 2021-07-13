package com.example.savepass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String SafePass = "SafePass.db";
    public static final String login_Table = "login";
    public static final String lemail = "Email";
    public static final String lpass = "Master_Pass";

    public static final String Pass_Table = "Password";
    public static final String ptitle = "Title";
    public static final String purl = "URL";
    public static final String pusername = "Username";
    public static final String ppass = "Password";

    public static final String Notes_Table = "Secure_Notes";
    public static final String ntitle = "Title";
    public static final String nnote = "Note";

    public static final String Add_Table = "Address";
    public static final String atitle = "Title";
    public static final String aname = "Name";
    public static final String aphone = "Phone_no.";
    public static final String aemail = "Email";
    public static final String apre = "Present_Address";
    public static final String aper = "Permanent_Address";

    /*private static final String create_login_table;

    static {
        create_login_table = "create table " + login_Table + "(Email TEXT PRIMARY KEY NOT NULL, Master_Pass TEXT NOT NULL)";
    }*/

    private static final String create_login_table = "CREATE TABLE "
            + login_Table + "(" + lemail + " TEXT PRIMARY KEY," + lpass
            + " TEXT"+ ")";

    //private static final String create_login_table;
    //private static final String create_Pass_table;
    //private static final String create_Notes_table;
    //private static final String create_Add_table;

    //static {
        //create_login_table = "create table " + login_Table + "(" + lemail + " text primary key not null," + lpass + " text not null" + ")";
        //create_Pass_table = "CREATE TABLE " + Pass_Table + "(" + ptitle + " text not null," + purl + " text not null," + pusername + " text not null," + ppass + " text not null" + ")";
        //create_Notes_table = "CREATE TABLE " + Notes_Table + "(" + ntitle + " text not null," + nnote + " text not null" + ")";
       // create_Add_table = "CREATE TABLE " + Add_Table + "(" + atitle + " text not null," + aname + " text not null," + aphone + " text not null," + aemail + " text not null," + apre + " text not null," + aper + " text not null" + ")";
   // }


    //private static final String create_Pass_Table= "create table " + PILL_TABLE + "(" + KEY_ROWID + " text primary key not null," + KEY_PILLNAME + " text not null" + ")";


    DatabaseConnection(@Nullable Context context) {
        super(context, SafePass, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(create_login_table);
    //db.execSQL(create_Pass_table);
    //db.execSQL(create_Notes_table);
   // db.execSQL(create_Add_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+login_Table);
    //db.execSQL("DROP TABLE IF EXISTS "+Pass_Table);
    //db.execSQL("DROP TABLE IF EXISTS "+Notes_Table);
    //db.execSQL("DROP TABLE IF EXISTS "+Add_Table);
    onCreate(db);
    }

    public Boolean create_login(String mail,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Email",lemail);
        values.put("Pass",lpass);
        long result=db.insert(login_Table,null, values);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }
}
