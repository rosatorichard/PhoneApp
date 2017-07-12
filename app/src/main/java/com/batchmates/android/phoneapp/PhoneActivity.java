package com.batchmates.android.phoneapp;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends AppCompatActivity {

    private String phoneNumber;
    private String name;
    private String personImage;

    private RecyclerViewAdapter recyclerAdapter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItem= new DefaultItemAnimator();
    private static final String TAG = "Main Activity";

    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        recycler=(RecyclerView)findViewById(R.id.myRecycler);
        layoutManager=new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false);




        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;

        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;


        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");


        Uri PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        String IMAGE = ContactsContract.CommonDataKinds.Phone.PHOTO_URI;

        int hasPhone = 0;

        while (cursor.moveToNext()) {

            hasPhone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));


            String contactName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            String[] projections = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?";
            String[] selectionArgs = new String[]{contactName};

            name =cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            if (hasPhone > 0) {
                Cursor phoneCursor = getContentResolver().query(PHONE_URI, projections, selection, selectionArgs, null);
//                while (phoneCursor.moveToNext()) {
//                    personList.add(new Person(phoneCursor.getString(phoneCursor.getColumnIndex(DISPLAY_NAME)),
//                            phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)), phoneCursor.getBlob(phoneCursor.getColumnIndex(IMAGE))));
                phoneCursor.moveToNext();
                    Log.d(TAG, "onCreate: "+name);

                phoneNumber=phoneCursor.getString(phoneCursor.getColumnIndex(number));
                Log.d(TAG, "onCreate: "+phoneNumber);
                personImage=cursor.getString(cursor.getColumnIndex(IMAGE));
                Log.d(TAG, "onCreate: "+personImage);


                personList.add(new Person(name,phoneNumber,personImage));


                    //for later
//                if (image_uri != null) {
//                    image.setImageURI(Uri.parse(image_uri));
//                }
//                    Log.d(TAG, "onCreate: " + phoneCursor.getBlob(phoneCursor.getColumnIndex(IMAGE)));
//                }
//                Log.d(TAG, "onCreate: "+phoneCursor.getString(phoneCursor.getColumnIndex(IMAGE)));
            }



        }
        recyclerAdapter=new RecyclerViewAdapter(personList);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(defaultItem);
        recycler.setAdapter(recyclerAdapter);

    }
}
