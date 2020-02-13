package com.acme.surfswap.utils;

import com.acme.surfswap.model.TimeSlot;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateAndTimeTest {

    @Test
    void dateFromTimestamp() {
        Date date = DateAndTime.dateFromTimestamp(1366902000);
        assertNotNull(date);
    }

    @Test
    void hourFromTimestamp() {
        Date date = DateAndTime.dateFromTimestamp(1366902000);
//        System.out.println("date = " + date);
        assertNotNull(date);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDate.format(date);
//        System.out.println("formattedDate = " + formattedDate);

        SimpleDateFormat simpleHour = new SimpleDateFormat("HH");
        String formattedHour = simpleHour.format(date);
//        System.out.println("formattedHour = " + formattedHour);

        assertEquals("18", formattedHour);
    }

    @Test
    void currentDateFromTimestamp() {
        Date date2020 = DateAndTime.dateFromTimestamp(1581499964);
//        System.out.println("date2020 = " + date2020);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = jdf.format(date2020);
//        System.out.println("formattedDate = " + formattedDate);

        assertNotNull(date2020);
    }

    @Test
    void parseEmptyTimestamp() {
        Date zeroDate = DateAndTime.dateFromTimestamp(0L);
        System.out.println("zeroDate = " + zeroDate);

        Date date = new Date();
        date.setTime(0L);
        assertEquals(date, zeroDate);
    }

    @Test
    void localDateAccumulate() {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 2, 12, 0, 0, 0);
        System.out.println("startDateTime = " + startDateTime);

        for (int i = 0; i < 2; i++) {
//            System.out.println(startDateTime.plusDays(i));
            for (int h = 0; h < 24; h++) {
                LocalDateTime accumulateHourDateTime = startDateTime.plusDays(i).plusHours(h);
                System.out.println(accumulateHourDateTime);
                TimeSlot timeSlot = TimeSlot.builder()
                        .localDateTime(accumulateHourDateTime)
                        .timeSlotStart(accumulateHourDateTime.toLocalTime())
                        .timeSlotEnd(accumulateHourDateTime.plusHours(1).toLocalTime())
                        .year(accumulateHourDateTime.getYear())
                        .month(accumulateHourDateTime.getMonthValue())
                        .dayOfMonth(accumulateHourDateTime.getDayOfMonth())
                        .dayOfWeekNum(accumulateHourDateTime.getDayOfWeek().getValue())
                        .hour(accumulateHourDateTime.getHour())
                        .build();
//                System.out.println(timeSlot.toString());

            }
        }
    }
}