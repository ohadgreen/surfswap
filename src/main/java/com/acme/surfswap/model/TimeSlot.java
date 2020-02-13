package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "time_slots")
public class TimeSlot extends BaseEntity {
    @Basic
    private LocalDateTime localDateTime;
    @Basic
    private LocalTime timeSlotStart;
    @Basic
    private LocalTime timeSlotEnd;

    private int year;
    private int month;
    @Column(name = "day_of_month")
    private int dayOfMonth;
    @Column(name = "day_of_week_num")
    private int dayOfWeekNum;
    @Column(name = "day_of_week_string")
    private String dayOfWeekString;
    private int hour;
}
