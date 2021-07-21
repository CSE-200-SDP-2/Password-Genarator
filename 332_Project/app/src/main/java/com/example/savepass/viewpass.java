package com.example.savepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class viewpass extends AppCompatActivity {
    private Toolbar toolbar;
    DBconnection DB;
    private EditText title,url,username,pass;
    private Button edit,save;
    private String pt;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpass);

        Toolbar toolbar= findViewById(R.id.viewpass_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        title = findViewById(R.id.item_title);
        url = findViewById(R.id.pass_url);
        username = findViewById(R.id.editTextTextPersonName5);
        pass = findViewById(R.id.editTextTextPassword);
        edit = findViewById(R.id.button4);
        save= findViewById(R.id.save);
        save.setVisibility(View.INVISIBLE);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
            }
        });

        DB = new DBconnection(this);

        Intent Ri = getIntent();
        pt = Ri.getStringExtra("title");

        cPass a = new cPass();
        Cursor dt = DB.getPass(pt);
        if(dt.moveToFirst()){
            do{
                a.setTitle(dt.getString(0));
                a.setUrl(dt.getString(1));
                a.setUsername(dt.getString(2));
                a.setPass(dt.getString(3));
            }while(dt.moveToNext());
            title.setText(a.getTitle());
            disableEditText(title);
            url.setText(a.getUrl());
            disableEditText(url);
            disableEditText(username);
            disableEditText(pass);
            username.setText(a.getUsername());
            disableEditText(url);
            pass.setText(a.getPass());

        }else {
            toastMessage("Sorry no data has been found");
        }

    }

    public void showPopup(View view)
    {
        PopupMenu popup=new PopupMenu(this,view);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu,popup.getMenu());
        popup.show();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        //editText.setBackgroundColor(Color.TRANSPARENT);
    }
}