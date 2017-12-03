package com.example.takunaka.amocrmconnector.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);

    public static String getDateFromUts(int timeStamp){
        return sdf.format(new Date(timeStamp * 1000L));
    }
}
