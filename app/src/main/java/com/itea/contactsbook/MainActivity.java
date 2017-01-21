package com.itea.contactsbook;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabKeyboard;
    private ListView lvContacts;
    private List<ContactEnitity> contacts;
    private Toolbar toolbarMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);

        initControls();
        initListeners();

        contacts = new ArrayList<ContactEnitity>();

        getContacts();
        initList();



    }

    private void initList() {
        ContactsAdapter adapter = new ContactsAdapter(this,R.layout.contact_layout,contacts);
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

            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

                Intent editContact = new Intent(Intent.ACTION_EDIT);
                startActivity(editContact);

//                Toast toast = Toast.makeText(getApplicationContext(),"on item click",Toast.LENGTH_SHORT);
//                toast.show();
            }
        });


    }

    private void initControls() {
        fabKeyboard = (FloatingActionButton) findViewById(R.id.fabKeyboard);
        lvContacts = (ListView) findViewById(R.id.lvContacts);
    }


    private void getContacts() {

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (phones.moveToFirst()){
            do {
                String id = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));

                ContactEnitity contactEnitity = new ContactEnitity();
                contactEnitity.setPnoneNumber1(id);
                contactEnitity.setPnoneNumber1(phoneNumber);
                contactEnitity.setName(name);

                contacts.add(contactEnitity);

            } while(phones.moveToNext());
        }

    }

}
