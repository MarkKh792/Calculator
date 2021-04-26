package com.example.lectcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    ArrayList<String> answers = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText text1;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        add = findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.listView1);

    }
    public void addToHistory (String expression) {

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, answers);

        listView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
            adapter.add(expression);
            //adapter.notifyDataSetChanged();
    }

    /*public void addToHistory(View view) {

        adapter.setNotifyOnChange(true);
        adapter.add("1 "+ text1.getText().toString());
        //adapter.notifyDataSetChanged();

    }*/
}