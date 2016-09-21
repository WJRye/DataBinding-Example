package com.wj.databinding.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author：王江 on 2016/9/13 14:13
 * Description:
 */
public final class DateUtil {
    private DateUtil() {
    }

    public static String dateToString(long milliseconds) {
        return new SimpleDateFormat("yy-MM-dd hh:mm").format(new Date(milliseconds));
    }
}
