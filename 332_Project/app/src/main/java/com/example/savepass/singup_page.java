package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class singup_page extends AppCompatActivity {

    private static final String TAG = "signup_page";

    DBconnection DB;
    private Button s_done;
    private EditText smail,spass,scpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_page);

        s_done=(Button) findViewById(R.id.signup_done);
        smail = (EditText) findViewById(R.id.editTextTextPersonName);
        spass = (EditText) findViewById(R.id.editTextTextPersonName2);
        scpass = (EditText) findViewById(R.id.editTextTextPassword3);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        DB = new DBconnection(this);

        s_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();

                String Smail,Spass,Scpass;
                Smail = smail.getText().toString();
                Spass = spass.getText().toString();
                Scpass = scpass.getText().toString();

                if (smail.length() != 0 && spass.length()!=0 && scpass.length()!=0){
                    if(Spass.equals(Scpass)){
                            AddData(Smail,Spass);
                            smail.setText("");
                            spass.setText("");
                            scpass.setText("");
                    }else{
                        toastMessage("Password didn't match!!!");
                    }
                    //AddData(newEntry);
                    //editText.setText("");
                } else {
                    toastMessage("Empty fields aren't allowed!");
                }

                /*Intent l=new Intent(singup_page.this, login_page.class);
                startActivity(l);*/
            }
        });
    }

    private void signup()
    {
        String username=smail.getText().toString();
        if(username!=""){
            editor.putBoolean("saveusername",true);
            editor.putString("username",username);
            editor.commit();}
    }


    public void AddData(String e,String p) {
        boolean insertData = DB.addData(e,p);

        if (insertData) {
            toastMessage("Signup Successfull.");
            Intent l=new Intent(singup_page.this, login_page.class);
            l.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            l.putExtra("id",1);
            startActivity(l);
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}