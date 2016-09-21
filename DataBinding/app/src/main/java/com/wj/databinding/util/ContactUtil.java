package com.wj.databinding.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Author：王江 on 2016/9/13 15:06
 * Description:
 */
public final class ContactUtil {
    private ContactUtil() {
    }

    public static String getContactName(Context context, String number) {
        String name = null;
        if (context != null && number != null && !"".equals(number.trim())) {
            Uri uri = Uri.withAppendedPath(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI,
                    number);
            Cursor nameCursor = context.getContentResolver().query(uri,
                    new String[]{"display_name"}, null, null, null);
            if (nameCursor != null) {
                if (nameCursor.moveToFirst()) {
                    name = nameCursor.getString(0);
                }
                nameCursor.close();
                nameCursor = null;
            }
        }
        return name;
    }
}
