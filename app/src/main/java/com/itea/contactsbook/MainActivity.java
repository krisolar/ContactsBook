package com.itea.contactsbook;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabKeyboard;
    ListView lvContacts;
    String[] contacts = {"1","2","3","4","5","6","7","8","9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initListeners();
        initList();


    }

    private void initList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        lvContacts.setAdapter(adapter);
    }

    private void initListeners() {

        fabKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT);
            }
        });

    }

    private void initControls() {
        fabKeyboard = (FloatingActionButton) findViewById(R.id.fabKeyboard);
        lvContacts = (ListView) findViewById(R.id.lvContacts);
    }


}
