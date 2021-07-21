package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class notes extends AppCompatActivity {

    DBconnection DB;
    private ListView lv_note1;
    private Cursor dt;
    private Toolbar toolbar;
    FloatingActionButton f_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Toolbar toolbar= findViewById(R.id.notes_bar);
        setSupportActionBar(toolbar);
        lv_note1 = findViewById(R.id.lv_note);
        DB = new DBconnection(this);

        dt = DB.getDBNote();
        if(dt.moveToFirst()){
            Fill(dt);
        }

        lv_note1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String I = String.valueOf(i);
                int L = I.length();
                L+=2;
                String title = name.substring(L);

                Intent editScreenIntent = new Intent(notes.this, viewnotes.class);
                editScreenIntent.putExtra("title",title);
                startActivity(editScreenIntent);
            }
        });

        f_btn2=(FloatingActionButton) findViewById(R.id.fab2);

        f_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(notes.this, add_notes.class);
                startActivity(l);
            }
        });
    }
    public void Fill(Cursor dtb){

        ArrayList<String> listData = new ArrayList<>();
        Integer i = 1;
        do{
            //get the value from the database in column 1
            //then add it to the ArrayList
            String s = dtb.getString(0);
            String a = Integer.toString(i);
            String b = a+": "+s;
            //toastMessage(b);
            listData.add(b);
            i++;
        }while(dtb.moveToNext());
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv_note1.setAdapter(adapter);
        dtb.close();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}