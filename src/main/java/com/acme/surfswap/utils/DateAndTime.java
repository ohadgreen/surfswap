package com.acme.surfswap.utils;

import java.time.Instant;
import java.util.Date;

public class DateAndTime {
    public static Date dateFromTimestamp(long timestamp) {
        Date date = new Date();
        try {
        date.setTime(timestamp * 1000);
        }
        catch (Exception e) {
            e.printStackTrace();
            date.setTime(0L);
        }
        return date;
    }

    public static Instant instantFromTimestamp(long timestamp) {
        return Instant.ofEpochSecond(timestamp);
    }
}
