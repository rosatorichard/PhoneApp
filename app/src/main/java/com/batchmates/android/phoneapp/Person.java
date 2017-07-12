package com.batchmates.android.phoneapp;

import android.graphics.Bitmap;

/**
 * Created by Android on 7/11/2017.
 */

public class Person {

    String name;
    String phoneNumber;
    String bitmap;

    public Person(String name, String phoneNumber, String bitmap) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String  getBitmap() {
        return bitmap;
    }
}
