package com.github.rappi.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Roger Patiño on 06/09/2016.
 */

public class Functions {

    public static String getDate(String utc) {
        Date date = new Date(Long.parseLong(utc.replace(".0", "")) * 1000);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return format.format(date);
    }

}