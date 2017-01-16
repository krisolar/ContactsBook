package com.itea.contactsbook;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabKeyboard;
    private ListView lvContacts;
    private String[] contacts = {"1","2","3","4","5","6","7","8","9"};

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

                String number = "";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);

//                Toast toast = Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT);
//                toast.show();
            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(),"on item click",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

//        lvContacts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    private void initControls() {
        fabKeyboard = (FloatingActionButton) findViewById(R.id.fabKeyboard);
        lvContacts = (ListView) findViewById(R.id.lvContacts);
    }


}
