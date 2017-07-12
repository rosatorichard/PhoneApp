package com.batchmates.android.phoneapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowPerson extends AppCompatActivity {

    private static final String TAG = "Send SMS";
    private TextView name, phone;
    private ImageView image;
    private String imageToBeUsed;
    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        image = (ImageView) findViewById(R.id.ivPhotoImage);
        name = (TextView) findViewById(R.id.tvPersonName);
        phone = (TextView) findViewById(R.id.tvPhonePerson);
        editText = (TextInputEditText) findViewById(R.id.editTextSMS);

        name.setText("Name: " + getIntent().getStringExtra("NAME"));
        phone.setText("Phone Number: " + getIntent().getStringExtra("NUMBER"));
        imageToBeUsed = getIntent().getStringExtra("IMAGE");


        if (imageToBeUsed != null) {
            image.setImageURI(Uri.parse(imageToBeUsed));
        } else {
            image.setBackgroundResource(R.mipmap.ic_launcher);
        }

    }

    public void sendText(View view) {

        SmsManager sms = SmsManager.getDefault();

        String theNewNumber = getIntent().getStringExtra("NUMBER");
        String key = " ";
        String Key2 = "(";
        String Key3 = ")";
        String Key4 = "-";
        String Key5 = "+";
        String Key6 = "1";
        String newString = "";
        for (int i = 0; i < theNewNumber.length(); i++) {
            if (theNewNumber.charAt(i) == key.charAt(0) || theNewNumber.charAt(i) == Key2.charAt(0) || theNewNumber.charAt(i) == Key3.charAt(0) || theNewNumber.charAt(i) == Key4.charAt(0) || theNewNumber.charAt(i) == Key5.charAt(0)) {

            } else {
                if (i == 0 && theNewNumber.charAt(0) == Key6.charAt(0)) {

                } else {
                    newString += theNewNumber.charAt(i);
                }
            }

        }
        Log.d(TAG, "sendText: " + getIntent().getStringExtra("NAME") + " " + getIntent().getStringExtra("NUMBER") + " " + newString);
        sms.sendTextMessage(newString, null, editText.getText().toString(), null, null);
        editText.setText("");

    }

    public void callPerson(View view) {

        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + getIntent().getStringExtra("NUMBER")));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.startActivity(intent);
    }
}
