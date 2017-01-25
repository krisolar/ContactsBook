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


    // The Cursor that contains the Contact row
    public Cursor mCursor;
    // The index of the lookup key column in the cursor
    public int mLookupKeyIndex;
    // The index of the contact's _ID value
    public int mIdIndex;
    // The lookup key from the Cursor
    public String mCurrentLookupKey;
    // The _ID value from the Cursor
    public long mCurrentId;
    // A content URI pointing to the contact
    Uri mSelectedContactUri;
    private Cursor phones;
    private ContactsAdapter adapter;


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
        adapter = new ContactsAdapter(this, R.layout.contact_layout, contacts);
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

                    /*
     * Once the user has selected a contact to edit,
     * this gets the contact's lookup key and _ID values from the
     * cursor and creates the necessary URI.
     */
                mCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                mCursor.move(position+1);

                // Gets the lookup key column index
                mLookupKeyIndex = mCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY);
                // Gets the lookup key value
                mCurrentLookupKey = mCursor.getString(mLookupKeyIndex);
                // Gets the _ID column index
                mIdIndex = mCursor.getColumnIndex(ContactsContract.Contacts._ID);

                mCurrentId = mCursor.getLong(mIdIndex);
                mCursor.close();
                mSelectedContactUri =
                        ContactsContract.Contacts.getLookupUri(mCurrentId, mCurrentLookupKey);

                // Creates a new Intent to edit a contact
                Intent editIntent = new Intent(Intent.ACTION_EDIT);
                editIntent.setDataAndType(mSelectedContactUri, ContactsContract.Contacts.CONTENT_ITEM_TYPE);
                editIntent.putExtra("finishActivityOnSaveCompleted", true);
                startActivity(editIntent);


    /*
     * Sets the contact URI to edit, and the data type that the
     * Intent must match
     */
            }
        });


    }

    private void initControls() {
        fabKeyboard = (FloatingActionButton) findViewById(R.id.fabKeyboard);
        lvContacts = (ListView) findViewById(R.id.lvContacts);
    }


    private void getContacts() {

        phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (phones.moveToFirst()) {
            do {
                String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                ContactEnitity contactEnitity = new ContactEnitity();
                contactEnitity.setPnoneNumber1(id);
                contactEnitity.setPnoneNumber1(phoneNumber);
                contactEnitity.setName(name);

                contacts.add(contactEnitity);

            } while (phones.moveToNext());

            phones.close();        }

    }

}
