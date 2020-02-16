package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "surfers")
public class Surfer extends Person {

    private String customerPlan;
}
