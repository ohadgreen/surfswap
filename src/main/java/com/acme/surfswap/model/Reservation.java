package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Entity
//@Table(name = "reservations")
public class Reservation extends BaseEntity {
    private Surfer surfer;
    private Surfboard surfboard;
    private TimeSlot startTime;
    private TimeSlot returnTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualReturnTime;

}
