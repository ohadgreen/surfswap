package com.acme.surfswap.utils;

import com.acme.surfswap.model.BaseEntity;

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
}
