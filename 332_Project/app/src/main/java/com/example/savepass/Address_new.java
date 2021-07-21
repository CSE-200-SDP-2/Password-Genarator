package com.example.savepass;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Address_new extends Fragment {
    DBconnection DB;
    private ListView lv_add1;
    private android.widget.Toolbar toolbar;
    private Cursor dt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_address_new,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_add1 =(ListView) view.findViewById(R.id.lv_add);
        DB = new DBconnection(getActivity());

        Cursor dt = DB.getDBAdd();
        if(dt.moveToFirst()){
            Fill(dt);
        }

        lv_add1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String I = String.valueOf(i);
                int L = I.length();
                L+=2;
                String title = name.substring(L);

                Intent editScreenIntent = new Intent(getActivity(), viewaddress.class);
                editScreenIntent.putExtra("title",title);
                startActivity(editScreenIntent);
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
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listData);
        lv_add1.setAdapter(adapter);
        dtb.close();
    }
}