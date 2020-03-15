package com.acme.surfswap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends Person {

    @Builder
    public Customer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }
}
