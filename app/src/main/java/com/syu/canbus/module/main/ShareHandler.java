package com.syu.canbus.module.main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class ShareHandler {
    public static final Uri URI = Uri.parse("content://com.syu.ms.provider");

    public static int getInt(ContentResolver resolver, int key, int valueIfNotFound) {
        int result = valueIfNotFound;
        if (resolver == null) {
            return result;
        }
        Cursor cursor = resolver.query(URI, null, Integer.toString(key), null, null);
        if (cursor == null) {
            return result;
        }
        try {
            if (cursor.moveToFirst()) {
                result = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cursor.close();
        return result;
    }
}
