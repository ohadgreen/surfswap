package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surfboard_id")
    private Surfboard surfboard;

//    private TimeSlot startTime;
//    private TimeSlot returnTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualReturnTime;

}
