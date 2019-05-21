package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Entity
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Person(Long id, String firstName, String lastName, String phoneNumber) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
