package com.acme.surfswap.model;

import com.acme.surfswap.enums.CustomerPlan;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "surfers")
public class Surfer extends Person {

    public Surfer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    @Enumerated(EnumType.STRING)
    private CustomerPlan customerPlan;
}
